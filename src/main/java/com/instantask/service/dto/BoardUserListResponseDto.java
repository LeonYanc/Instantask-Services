package com.instantask.service.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for returning totalUsers, totalAdmins, and userList
 * when querying a specific board.
 */
public class BoardUserListResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int totalUsers;
    private int totalAdmins;
    private List<BoardUserItemDto> userList;

    public BoardUserListResponseDto() {
    }

    public BoardUserListResponseDto(int totalUsers, int totalAdmins, List<BoardUserItemDto> userList) {
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

    public List<BoardUserItemDto> getUserList() {
        return userList;
    }

    public void setUserList(List<BoardUserItemDto> userList) {
        this.userList = userList;
    }
}
