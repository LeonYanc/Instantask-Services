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

/**
 * Implementation of UserService interface, using MongoDB for data storage.
 * This is a basic demo; real production code should handle exceptions, password hashing, etc.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Register a new user based on RegisterRequestDto.
     *  - Checks if the email already exists
     *  - Creates a new User object with 'Active' status
     *  - Saves to MongoDB
     *  - Returns a UserDetailDto (id, name, email)
     */
    @Override
    public UserDetailDto register(RegisterRequestDto dto) {
        // 1) Check if email is in use
        Optional<User> existing = userRepository.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email already in use: " + dto.getEmail());
        }

        // 2) Create a new User entity
        User newUser = new User();
        newUser.setName(dto.getName());
        newUser.setEmail(dto.getEmail());
        newUser.setTel(dto.getTel());
        // For demo only: storing password in plain text
        newUser.setPassword(dto.getPassword());
        newUser.setStatus("Active"); // or "Registered"
        newUser.setLastActiveTime(LocalDateTime.now());
        // If you need a default role, set it here:
        // newUser.setRole("viewer");

        // 3) Save to DB
        User savedUser = userRepository.save(newUser);

        // 4) Convert to DTO
        return new UserDetailDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    /**
     * Login with email and password.
     *  - Finds user by email
     *  - Checks password (plain text example)
     *  - Updates lastActiveTime
     *  - Returns UserDetailDto
     */
    @Override
    public UserDetailDto login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + dto.getEmail()));

        // Here just compare plain text, real case: match hashed password
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Incorrect password.");
        }

        user.setLastActiveTime(LocalDateTime.now());
        userRepository.save(user);

        return new UserDetailDto(user.getId(), user.getName(), user.getEmail());
    }

    /**
     * Get a user's detail (for user detail page),
     * returns only name & email in the DTO.
     */
    @Override
    public UserDetailDto getUserDetail(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        return new UserDetailDto(user.getId(), user.getName(), user.getEmail());
    }

    /**
     * Fetch all users and build a UserListResponseDto.
     *  - totalUsers: number of all users
     *  - totalAdmins: number of users with role = "admin" (example)
     *  - userList: each item includes id, name, email, lastActiveTime, status
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
     *  - If email exists, throw exception or handle it
     *  - Otherwise create a new user with "Invited" status
     *  - Return a basic UserDetailDto
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
        // no password at this time or set a random placeholder
        invitedUser.setPassword(null);
        // name might be unknown at the invite stage, or you can set from request
        invitedUser.setName("");
        invitedUser.setLastActiveTime(null);

        User saved = userRepository.save(invitedUser);
        return new UserDetailDto(saved.getId(), saved.getName(), saved.getEmail());
    }
}

