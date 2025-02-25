package com.instantask.service.dto;

/**
 * DTO for an item in the user list:
 * id, name, email, lastActiveTime (string), status
 */
public class UserListItemDto {

    private String id;
    private String name;
    private String email;
    private String lastActiveTime;
    private String status;

    public UserListItemDto() {
    }

    public UserListItemDto(String id, String name, String email,
                           String lastActiveTime, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastActiveTime = lastActiveTime;
        this.status = status;
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
}
