package com.instantask.service.dto;

import com.instantask.service.model.AccessRole;
import java.io.Serializable;

/**
 * DTO for inviting an existing user to a board, with a specific role.
 */
public class UserAccessInviteRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

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
