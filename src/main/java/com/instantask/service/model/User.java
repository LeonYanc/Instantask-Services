package com.instantask.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String tel;
    private String status;
    private LocalDateTime lastActiveTime;

    private String role;               // Role: "admin", "viewer", etc.

    // -------------- Constructors --------------

    public User() {
    }

    public User(String name, String email, String password, String tel,
                String status, LocalDateTime lastActiveTime, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.status = status;
        this.lastActiveTime = lastActiveTime;
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


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +  // pasword
                ", tel='" + tel + '\'' +
                ", status='" + status + '\'' +
                ", lastActiveTime=" + lastActiveTime +
                ", role='" + role + '\'' +
                '}';
    }
}

