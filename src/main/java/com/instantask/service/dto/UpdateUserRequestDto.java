package com.instantask.service.dto;

import java.time.LocalDateTime;

/**
 * DTO for updating user information.
 */
public class UpdateUserRequestDto {

    private String name;
    private String email;
    private String password;
    private String tel;
    private String status;
    private LocalDateTime lastActiveTime;  // optional, your call
    private String role;

    public UpdateUserRequestDto() {
    }

    public UpdateUserRequestDto(String name, String email, String password,
                                String tel, String status, LocalDateTime lastActiveTime, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.status = status;
        this.lastActiveTime = lastActiveTime;
        this.role = role;
    }

    // ========== Getters & Setters ==========

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(LocalDateTime lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
