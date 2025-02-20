package com.instantask.service.service;

import com.instantask.service.dto.*;
import com.instantask.service.model.User;

public interface UserService {

    /**
     * Register a new user based on RegisterRequestDto.
     */
    UserDetailDto register(RegisterRequestDto dto);

    /**
     * Login with email and password.
     */
    UserDetailDto login(LoginRequestDto dto);

    /**
     * Get details of a specific user.
     */
    UserDetailDto getUserDetail(String userId);

    /**
     * Get a list of all users, as well as total user count and admin count.
     */
    UserListResponseDto getUserList();

    /**
     * Invite a user by email with a specified role.
     */
    UserDetailDto inviteUser(InviteRequestDto dto);


    /**
     * Update user information based on a custom DTO.
     */
    UserDetailDto updateUser(String userId, UpdateUserRequestDto dto);

    /**
     * Delete a user by ID.
     */
    void deleteUser(String userId);

    /**
     * Forgot password logic, for example by email.
     */
    void forgotPassword(String email);

}
