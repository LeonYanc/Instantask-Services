package com.instantask.service.service;

import com.instantask.service.dto.UserAccessInviteRequestDto;
import com.instantask.service.dto.BoardUserItemDto;
import com.instantask.service.dto.BoardUserListResponseDto;
import com.instantask.service.model.AccessRole;
import com.instantask.service.model.User;
import com.instantask.service.model.UserAccess;
import com.instantask.service.repository.UserAccessRepository;
import com.instantask.service.repository.UserRepository;
import com.instantask.service.service.UserAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class UserAccessServiceImpl implements UserAccessService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAccessRepository userAccessRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "boardMemberCache", key = "#dto.boardId"),
            @CacheEvict(value = "boardUserListCache", key = "#dto.boardId")
    })
    public UserAccess inviteUserToBoard(UserAccessInviteRequestDto dto) {
        //Check user existence by email
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not registered: " + dto.getEmail()));

        //Check if userAccess already exists for (userId, boardId)
        UserAccess existing = userAccessRepository.findByUserIdAndBoardId(user.getId(), dto.getBoardId());
        if (existing != null) {
            // maybe throw an exception or update existing
            throw new RuntimeException("User is already in this board or invited");
        }

        //Create new userAccess
        UserAccess userAccess = new UserAccess();
        userAccess.setUserId(user.getId());
        userAccess.setBoardId(dto.getBoardId());
        userAccess.setRole(dto.getRole());
        userAccess.setStatus("Invited"); // or "Pending"
        userAccess.setIssuedTime(LocalDateTime.now());
        userAccess.setDueTime(null);

        return userAccessRepository.save(userAccess);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "boardMemberCache", key = "#boardId"),
            @CacheEvict(value = "boardUserListCache", key = "#boardId"),
            @CacheEvict(value = "userAccessCache", key = "#userId + ':' + #boardId")
    })
    public void removeUserFromBoard(String userId, String boardId) {
        UserAccess existing = userAccessRepository.findByUserIdAndBoardId(userId, boardId);
        if (existing != null) {
            userAccessRepository.deleteById(existing.getId());
        } else {
            throw new RuntimeException("UserAccess record not found for user:" + userId
                    + " board:" + boardId);
        }
    }

    @Override
    @Cacheable(value = "boardMemberCache", key = "#boardId")
    public List<UserAccess> getBoardMembers(String boardId) {
        return userAccessRepository.findByBoardId(boardId);
    }

    @Override
    @Cacheable(value = "userAccessCache", key = "#userId + ':' + #boardId")
    public UserAccess getUserAccess(String userId, String boardId) {
        return userAccessRepository.findByUserIdAndBoardId(userId, boardId);
    }

    @Override
    @Cacheable(value = "boardUserListCache", key = "#boardId")
    public BoardUserListResponseDto getBoardUserList(String boardId) {
        List<UserAccess> accessList = userAccessRepository.findByBoardId(boardId);

        List<BoardUserItemDto> userList = new ArrayList<>();
        int adminCount = 0;

        for (UserAccess ua : accessList) {
            if (ua.getRole() == AccessRole.ADMIN) {
                adminCount++;
            }

            Optional<User> userOpt = userRepository.findById(ua.getUserId());
            if (!userOpt.isPresent()) {
                continue;
            }
            User user = userOpt.get();

            String lastActiveStr = (user.getLastActiveTime() == null)
                    ? "N/A"
                    : user.getLastActiveTime().toString();

            BoardUserItemDto item = new BoardUserItemDto(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    lastActiveStr,
                    user.getStatus(),
                    ua.getRole()
            );

            userList.add(item);
        }

        int totalUsers = userList.size();

        return new BoardUserListResponseDto(totalUsers, adminCount, userList);
    }
}
