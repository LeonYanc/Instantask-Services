package com.instantask.service.dto;

import com.instantask.service.model.AccessRole;

public class BoardUserItemDto {
    private String id;
    private String name;
    private String email;
    private String lastActiveTime;
    private String status;
    private AccessRole role;

    public BoardUserItemDto() {
    }

    public BoardUserItemDto(String id, String name, String email,
                            String lastActiveTime, String status,
                            AccessRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastActiveTime = lastActiveTime;
        this.status = status;
        this.role = role;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastActiveTime() {
        return lastActiveTime;
    }
    public void setLastActiveTime(String lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public AccessRole getRole() {
        return role;
    }
    public void setRole(AccessRole role) {
        this.role = role;
    }
}
