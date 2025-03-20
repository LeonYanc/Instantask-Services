package com.instantask.service.controller;

import com.instantask.service.dto.*;
import com.instantask.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for user management (no role).
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    // 1) Register
    @PostMapping("/register")
    public ResponseEntity<UserDetailDto> register(@RequestBody RegisterRequestDto dto) {
        UserDetailDto result = userService.register(dto);
        return ResponseEntity.ok(result);
    }

    // 2) Login
    @PostMapping("/login")
    public ResponseEntity<UserDetailDto> login(@RequestBody LoginRequestDto dto) {
        UserDetailDto result = userService.login(dto);
        return ResponseEntity.ok(result);
    }

    // 3) Get user detail
    @GetMapping("/{id}/detail")
    public ResponseEntity<UserDetailDto> getUserDetail(@PathVariable String id) {
        UserDetailDto result = userService.getUserDetail(id);
        return ResponseEntity.ok(result);
    }

    // 4) Get user list
    @GetMapping
    public ResponseEntity<UserListResponseDto> getAllUsers() {
        UserListResponseDto result = userService.getUserList();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkUserByEmail(@RequestBody CheckUserByEmailRequestDto dto) {
        try {
            // 调用 Service：如果用户不存在就抛异常或返回特定提示
            UserDetailDto userDetail = userService.checkUserByEmail(dto.getEmail());
            return ResponseEntity.ok(userDetail);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // 6) Update user
    @PutMapping("/{id}")
    public ResponseEntity<UserDetailDto> updateUser(@PathVariable String id,
                                                    @RequestBody UpdateUserRequestDto dto) {
        UserDetailDto updated = userService.updateUser(id, dto);
        return ResponseEntity.ok(updated);
    }

    // 7) Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 8) Forgot password
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        try {
            userService.forgotPassword(email);
            return ResponseEntity.ok("Reset link sent to " + email);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
