package com.instantask.service.service;

import com.instantask.service.dto.*;

public interface UserService {

    /**
     * Register a new user based on RegisterRequestDto.
     * @param dto the incoming registration data
     * @return UserDetailDto with basic info of the created user
     */
    UserDetailDto register(RegisterRequestDto dto);

    /**
     * Login with email and password.
     * @param dto the login data
     * @return UserDetailDto containing user's basic info if successful
     */
    UserDetailDto login(LoginRequestDto dto);

    /**
     * Get details of a specific user.
     * @param userId the user ID
     * @return UserDetailDto containing name and email
     */
    UserDetailDto getUserDetail(String userId);

    /**
     * Get a list of all users, as well as total user count and admin count.
     * @return UserListResponseDto containing user list and statistics
     */
    UserListResponseDto getUserList();

    /**
     * Invite a user by email with a specified role.
     * @param dto the invitation data
     * @return UserDetailDto representing the invited user
     */
    UserDetailDto inviteUser(InviteRequestDto dto);

    // Add other methods (update, delete, etc.) as needed.
}
