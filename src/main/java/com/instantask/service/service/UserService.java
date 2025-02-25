package com.instantask.service.service;

import com.instantask.service.dto.*;

public interface UserService {

    UserDetailDto register(RegisterRequestDto dto);

    UserDetailDto login(LoginRequestDto dto);

    UserDetailDto getUserDetail(String userId);

    UserListResponseDto getUserList();

    UserDetailDto checkUserByEmail(String email);

    UserDetailDto updateUser(String userId, UpdateUserRequestDto dto);

    void deleteUser(String userId);

    void forgotPassword(String email);
}
