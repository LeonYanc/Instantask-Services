package com.instantask.service.service;

import com.instantask.service.dto.*;
import com.instantask.service.model.User;
import com.instantask.service.repository.UserRepository;
import com.instantask.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Register a new user based on RegisterRequestDto.

     */
    @Override
    public UserDetailDto register(RegisterRequestDto dto) {

        Optional<User> existing = userRepository.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email already in use: " + dto.getEmail());
        }


        User newUser = new User();
        newUser.setName(dto.getName());
        newUser.setEmail(dto.getEmail());
        newUser.setTel(dto.getTel());

        newUser.setPassword(dto.getPassword());
        newUser.setStatus("Active");
        newUser.setLastActiveTime(LocalDateTime.now());


        User savedUser = userRepository.save(newUser);


        return new UserDetailDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    /**
     * Login with email and password.
     */
    @Override
    public UserDetailDto login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + dto.getEmail()));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Incorrect password.");
        }

        user.setLastActiveTime(LocalDateTime.now());
        userRepository.save(user);

        return new UserDetailDto(user.getId(), user.getName(), user.getEmail());
    }

    /**
     * Get a user's detail
     */
    @Override
    public UserDetailDto getUserDetail(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        return new UserDetailDto(user.getId(), user.getName(), user.getEmail());
    }

    /**
     * Fetch all users and build a UserListResponseDto.
     */
    @Override
    public UserListResponseDto getUserList() {
        List<User> allUsers = userRepository.findAll();

        // Count total
        int totalUsers = allUsers.size();
        // Count how many admins
        int totalAdmins = (int) allUsers.stream()
                .filter(u -> "admin".equalsIgnoreCase(u.getRole()))
                .count();

        // Convert each User to UserListItemDto
        List<UserListItemDto> listItems = allUsers.stream().map(u -> {
            // Format lastActiveTime as String for front-end (you can refine as needed)
            String lastActiveStr = (u.getLastActiveTime() == null)
                    ? "N/A"
                    : u.getLastActiveTime().toString();

            return new UserListItemDto(
                    u.getId(),
                    u.getName(),
                    u.getEmail(),
                    lastActiveStr,
                    u.getStatus()
            );
        }).collect(Collectors.toList());

        // Build response
        return new UserListResponseDto(totalUsers, totalAdmins, listItems);
    }

    /**
     * Invite a user by email with a given role.
     */
    @Override
    public UserDetailDto inviteUser(InviteRequestDto dto) {
        Optional<User> existing = userRepository.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("User with this email is already in system: " + dto.getEmail());
        }

        User invitedUser = new User();
        invitedUser.setEmail(dto.getEmail());
        invitedUser.setRole(dto.getRole());
        invitedUser.setStatus("Invited");

        invitedUser.setPassword(null);

        invitedUser.setName("");
        invitedUser.setLastActiveTime(null);

        User saved = userRepository.save(invitedUser);
        return new UserDetailDto(saved.getId(), saved.getName(), saved.getEmail());
    }

    @Override
    public UserDetailDto updateUser(String userId, UpdateUserRequestDto dto) {

        User existing = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));


        if (dto.getName() != null) {
            existing.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            existing.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            existing.setPassword(dto.getPassword());
        }
        if (dto.getTel() != null) {
            existing.setTel(dto.getTel());
        }
        if (dto.getStatus() != null) {
            existing.setStatus(dto.getStatus());
        }
        if (dto.getLastActiveTime() != null) {

            existing.setLastActiveTime(dto.getLastActiveTime());
        }
        if (dto.getRole() != null) {
            existing.setRole(dto.getRole());
        }


        User savedUser = userRepository.save(existing);


        return new UserDetailDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void forgotPassword(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found with email: " + email);
        }

        // send an email with reset link, or set a temporary password，not finish
        System.out.println("[ForgotPassword] Reset link sent to: " + email);
    }
}

