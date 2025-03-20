package com.instantask.service.dto;

import java.io.Serializable;

/**
 * DTO representing user details in response (id, name, email).
 */
public class UserDetailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;

    public UserDetailDto() {
    }

    public UserDetailDto(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // ========== Getters / Setters ==========

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
}
