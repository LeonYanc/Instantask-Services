package com.instantask.service.dto;

import java.util.List;


public class UserListResponseDto {

    private int totalUsers;
    private int totalAdmins;
    private List<UserListItemDto> userList;

    public UserListResponseDto() {
    }

    public UserListResponseDto(int totalUsers, int totalAdmins, List<UserListItemDto> userList) {
        this.totalUsers = totalUsers;
        this.totalAdmins = totalAdmins;
        this.userList = userList;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public int getTotalAdmins() {
        return totalAdmins;
    }

    public void setTotalAdmins(int totalAdmins) {
        this.totalAdmins = totalAdmins;
    }

    public List<UserListItemDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserListItemDto> userList) {
        this.userList = userList;
    }
}
