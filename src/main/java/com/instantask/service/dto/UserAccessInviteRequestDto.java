package com.instantask.service.dto;

import com.instantask.service.model.AccessRole;

/**
 * DTO for inviting an existing user to a board, with a specific role.
 */
public class UserAccessInviteRequestDto {

    private String email;
    private String boardId;
    private AccessRole role;   // requested role: ADMIN, EDITOR, etc.

    public UserAccessInviteRequestDto() {
    }

    public UserAccessInviteRequestDto(String email, String boardId, AccessRole role) {
        this.email = email;
        this.boardId = boardId;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBoardId() {
        return boardId;
    }
    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
    public AccessRole getRole() {
        return role;
    }
    public void setRole(AccessRole role) {
        this.role = role;
    }
}
