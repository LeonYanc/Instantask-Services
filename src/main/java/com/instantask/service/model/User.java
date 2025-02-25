package com.instantask.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * User entity for MongoDB (collection = "users").
 * No role field here, since roles are handled by UserAccess.
 */
@Document(collection = "users")
public class User {

    @Id
    private String id; // MongoDB primary key (ObjectId as String)
    private String name;
    private String email;
    private String password;
    private String tel;
    private String status; // e.g., "Active", "Invited"
    private LocalDateTime lastActiveTime;

    public User() {
    }

    public User(String name, String email, String password, String tel,
                String status, LocalDateTime lastActiveTime) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.status = status;
        this.lastActiveTime = lastActiveTime;
    }

    // ============ Getters & Setters ============

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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]', " + // avoid printing real password
                "tel='" + tel + '\'' +
                ", status='" + status + '\'' +
                ", lastActiveTime=" + lastActiveTime +
                '}';
    }
}

