package com.instantask.service.service;

import com.instantask.service.dto.*;
import com.instantask.service.model.User;
import com.instantask.service.repository.UserRepository;
import com.instantask.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailDto register(RegisterRequestDto dto) {
        // 1) Check if email already in use
        Optional<User> existing = userRepository.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email already in use: " + dto.getEmail());
        }

        // 2) Create new user
        User newUser = new User();
        newUser.setName(dto.getName());
        newUser.setEmail(dto.getEmail());
        newUser.setTel(dto.getTel());
        // For demo, storing password in plain text
        newUser.setPassword(dto.getPassword());
        newUser.setStatus("Active");
        newUser.setLastActiveTime(LocalDateTime.now());

        // 3) Save
        User saved = userRepository.save(newUser);

        // 4) Convert to DTO
        return new UserDetailDto(saved.getId(), saved.getName(), saved.getEmail());
    }

    @Override
    public UserDetailDto login(LoginRequestDto dto) {
        // 1) Find user by email
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found: " + dto.getEmail()));

        // 2) Check password (demo only)
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Incorrect password.");
        }

        // 3) Update lastActiveTime
        user.setLastActiveTime(LocalDateTime.now());
        userRepository.save(user);

        return new UserDetailDto(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public UserDetailDto getUserDetail(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        return new UserDetailDto(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public UserListResponseDto getUserList() {
        List<User> allUsers = userRepository.findAll();
        int totalUsers = allUsers.size();

        // Convert each user to UserListItemDto
        List<UserListItemDto> userItems = allUsers.stream().map(u -> {
            String lastActiveStr = (u.getLastActiveTime() == null)
                    ? "N/A"
                    : u.getLastActiveTime().toString();
            return new UserListItemDto(
                    u.getId(),
                    u.getName(),
                    u.getEmail(),
                    lastActiveStr,
                    u.getStatus()
            );
        }).collect(Collectors.toList());

        return new UserListResponseDto(totalUsers, userItems);
    }

    @Override
    public UserDetailDto checkUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
        return new UserDetailDto(user.getId(), user.getName(), user.getEmail());
    }


    @Override
    public UserDetailDto updateUser(String userId, UpdateUserRequestDto dto) {
        // Find existing user
        User existing = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        // Partial update
        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getEmail() != null) existing.setEmail(dto.getEmail());
        if (dto.getPassword() != null) existing.setPassword(dto.getPassword());
        if (dto.getTel() != null) existing.setTel(dto.getTel());
        if (dto.getStatus() != null) existing.setStatus(dto.getStatus());
        if (dto.getLastActiveTime() != null) {
            existing.setLastActiveTime(dto.getLastActiveTime());
        }

        User saved = userRepository.save(existing);
        return new UserDetailDto(saved.getId(), saved.getName(), saved.getEmail());
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void forgotPassword(String email) {
        // 1) find user
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("No user with email: " + email);
        }
        // 2) do something like send email or generate token
        System.out.println("Reset password link sent to " + email);
    }
}
