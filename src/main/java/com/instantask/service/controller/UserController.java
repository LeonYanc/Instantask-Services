package com.instantask.service.controller;

import com.instantask.service.dto.*;
import com.instantask.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Register a new user.
    @PostMapping("/register")
    public ResponseEntity<UserDetailDto> register(@RequestBody RegisterRequestDto dto) {
        UserDetailDto result = userService.register(dto);
        return ResponseEntity.ok(result);
    }

    //User login.
    @PostMapping("/login")
    public ResponseEntity<UserDetailDto> login(@RequestBody LoginRequestDto dto) {
        UserDetailDto result = userService.login(dto);
        return ResponseEntity.ok(result);
    }

    //Get user details.
    @GetMapping("/{id}/detail")
    public ResponseEntity<UserDetailDto> getUserDetail(@PathVariable String id) {
        UserDetailDto result = userService.getUserDetail(id);
        return ResponseEntity.ok(result);
    }

    //Get user list.
    @GetMapping
    public ResponseEntity<UserListResponseDto> getUserList() {
        UserListResponseDto result = userService.getUserList();
        return ResponseEntity.ok(result);
    }

    //Invite a new user.

    @PostMapping("/invite")
    public ResponseEntity<UserDetailDto> inviteUser(@RequestBody InviteRequestDto dto) {
        UserDetailDto result = userService.inviteUser(dto);
        return ResponseEntity.ok(result);
    }

}
