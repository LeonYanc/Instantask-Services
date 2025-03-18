package com.instantask.service.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for user list response:
 * totalUsers + list of UserListItemDto
 */
public class UserListResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int totalUsers;
    private List<UserListItemDto> userList;

    public UserListResponseDto() {
    }

    public UserListResponseDto(int totalUsers, List<UserListItemDto> userList) {
        this.totalUsers = totalUsers;
        this.userList = userList;
    }

    public int getTotalUsers() {
        return totalUsers;
    }
    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }
    public List<UserListItemDto> getUserList() {
        return userList;
    }
    public void setUserList(List<UserListItemDto> userList) {
        this.userList = userList;
    }
}
