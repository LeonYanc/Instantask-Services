package com.instantask.service.model;

import com.instantask.service.model.AccessRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * UserAccess entity for MongoDB (collection = "userAccess").
 * Represents a user's role in a specific board.
 */
@Document(collection = "userAccess")
public class UserAccess {

    @Id
    private String id;
    private String userId;       // user._id
    private String boardId;      // board._id
    private AccessRole role;     // e.g. ADMIN, EDITOR
    private String status;       // e.g. "Invited", "Active", etc.
    private LocalDateTime issuedTime;
    private LocalDateTime dueTime;

    public UserAccess() {
    }

    public UserAccess(String userId, String boardId, AccessRole role,
                      String status, LocalDateTime issuedTime, LocalDateTime dueTime) {
        this.userId = userId;
        this.boardId = boardId;
        this.role = role;
        this.status = status;
        this.issuedTime = issuedTime;
        this.dueTime = dueTime;
    }

    // ========== Getters & Setters ==========

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getIssuedTime() {
        return issuedTime;
    }
    public void setIssuedTime(LocalDateTime issuedTime) {
        this.issuedTime = issuedTime;
    }
    public LocalDateTime getDueTime() {
        return dueTime;
    }
    public void setDueTime(LocalDateTime dueTime) {
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        return "UserAccess{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", boardId='" + boardId + '\'' +
                ", role=" + role +
                ", status='" + status + '\'' +
                ", issuedTime=" + issuedTime +
                ", dueTime=" + dueTime +
                '}';
    }
}

