package com.instantask.service.dto;

import java.time.LocalDateTime;

/**
 * DTO for updating user info.
 * If partial update is desired, check for null in service.
 */
public class UpdateUserRequestDto {

    private String name;
    private String email;
    private String password;
    private String tel;
    private String status;
    private LocalDateTime lastActiveTime;

    public UpdateUserRequestDto() {
    }

    public UpdateUserRequestDto(String name, String email, String password,
                                String tel, String status, LocalDateTime lastActiveTime) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.status = status;
        this.lastActiveTime = lastActiveTime;
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
}


