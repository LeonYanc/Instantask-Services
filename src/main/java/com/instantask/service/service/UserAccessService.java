package com.instantask.service.service;

import com.instantask.service.model.UserAccess;
import com.instantask.service.dto.UserAccessInviteRequestDto;
import com.instantask.service.dto.BoardUserListResponseDto;



import java.util.List;

public interface UserAccessService {

    /**
     * Invite an existing user to a board (by email, boardId, role).
     * If user doesn't exist => throw error
     * If userAccess already exists => throw or update
     */
    UserAccess inviteUserToBoard(UserAccessInviteRequestDto dto);

    /**
     * Remove user from board
     */
    void removeUserFromBoard(String userId, String boardId);

    /**
     * List all userAccess in a specific board
     */
    List<UserAccess> getBoardMembers(String boardId);

    /**
     * Get a user's role in a board
     */
    UserAccess getUserAccess(String userId, String boardId);

    /**
     * Return a list of users (and total counts) in a specific board.
     */
    BoardUserListResponseDto getBoardUserList(String boardId);
}
