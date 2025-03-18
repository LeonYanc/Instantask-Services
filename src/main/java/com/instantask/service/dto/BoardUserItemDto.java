package com.instantask.service.dto;

import java.io.Serializable;

public class BoardUserItemDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;
    private String lastActive;
    private String status;
    private Object role;

    public BoardUserItemDto() {
    }

    public BoardUserItemDto(String id, String name, String email, String lastActive, String status, Object role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastActive = lastActive;
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

    public String getLastActive() {
        return lastActive;
    }

    public void setLastActive(String lastActive) {
        this.lastActive = lastActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }
}
