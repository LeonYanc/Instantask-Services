package com.instantask.service.service;

import com.instantask.service.dto.*;
import com.instantask.service.model.User;
import com.instantask.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"userCache"}) // Default cache namespace
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Register a new user.
     * After saving, we put the newly created user into the cache (using @CachePut).
     * Also, we evict (clear) the user list cache to keep data consistent.
     */
    @Override
    @CachePut(key = "#result.id") // After registration, add the user detail to the cache
    @CacheEvict(value = "userListCache", allEntries = true) // Clear the user list cache
    public UserDetailDto register(RegisterRequestDto dto) {
        // 1) Check if email already exists
        Optional<User> existing = userRepository.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email already in use: " + dto.getEmail());
        }

        // 2) Create a new user
        User newUser = new User();
        newUser.setName(dto.getName());
        newUser.setEmail(dto.getEmail());
        newUser.setTel(dto.getTel());
        // For demonstration, store the password in plain text
        newUser.setPassword(dto.getPassword());
        newUser.setStatus("Active");
        newUser.setLastActiveTime(LocalDateTime.now());

        // 3) Save the user
        User saved = userRepository.save(newUser);

        // 4) Convert to DTO and return
        return new UserDetailDto(saved.getId(), saved.getName(), saved.getEmail());
    }

    /**
     * Login method.
     * After login, we update the cached user detail (using @CachePut),
     * and clear the user list cache to keep data consistent.
     */
    @Override
    @CachePut(key = "#result.id") // After login, update the user detail in the cache
    @CacheEvict(value = "userListCache", allEntries = true) // Clear the user list cache
    public UserDetailDto login(LoginRequestDto dto) {
        // 1) Find user by email
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found: " + dto.getEmail()));

        // 2) Check password (for demo purposes only)
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Incorrect password.");
        }

        // 3) Update lastActiveTime
        user.setLastActiveTime(LocalDateTime.now());
        userRepository.save(user);

        return new UserDetailDto(user.getId(), user.getName(), user.getEmail());
    }

    /**
     * Retrieve user detail by userId.
     * If the cache has an entry, it will return from the cache; otherwise, it will query the database.
     */
    @Override
    @Cacheable(key = "#userId") // Cache user detail; if present, return from cache
    public UserDetailDto getUserDetail(String userId) {
        // This log will only appear if the cache is not hit
        System.out.println("Loading user detail from DB for userId=" + userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        return new UserDetailDto(user.getId(), user.getName(), user.getEmail());
    }

    /**
     * Retrieve a list of all users.
     * This method caches the result in "userListCache".
     */
    @Override
    @Cacheable(value = "userListCache") // Cache the user list
    public UserListResponseDto getUserList() {
        List<User> allUsers = userRepository.findAll();
        int totalUsers = allUsers.size();

        // Convert each User to UserListItemDto
        List<UserListItemDto> userItems = allUsers.stream().map(u -> {
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

        return new UserListResponseDto(totalUsers, userItems);
    }

    /**
     * Check if a user exists by email. No caching annotation here,
     * so it will always query the database directly.
     */
    @Override
    public UserDetailDto checkUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
        return new UserDetailDto(user.getId(), user.getName(), user.getEmail());
    }

    /**
     * Update user. We evict the cache entry for the specific user (by userId)
     * and also clear the user list cache.
     */
    @Override
    @Caching(evict = {
            @CacheEvict(key = "#userId"),
            @CacheEvict(value = "userListCache", allEntries = true)
    })
    public UserDetailDto updateUser(String userId, UpdateUserRequestDto dto) {
        // Find the existing user
        User existing = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        // Partial update
        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getEmail() != null) existing.setEmail(dto.getEmail());
        if (dto.getPassword() != null) existing.setPassword(dto.getPassword());
        if (dto.getTel() != null) existing.setTel(dto.getTel());
        if (dto.getStatus() != null) existing.setStatus(dto.getStatus());
        if (dto.getLastActiveTime() != null) {
            existing.setLastActiveTime(dto.getLastActiveTime());
        }

        User saved = userRepository.save(existing);
        return new UserDetailDto(saved.getId(), saved.getName(), saved.getEmail());
    }

    /**
     * Delete user by userId.
     * Evict the specific user from the cache and clear the user list cache.
     */
    @Override
    @Caching(evict = {
            @CacheEvict(key = "#userId"),
            @CacheEvict(value = "userListCache", allEntries = true)
    })
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Forgot password functionality.
     * Just a demo implementation that prints a message.
     */
    @Override
    public void forgotPassword(String email) {
        // 1) Find user by email
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("No user with email: " + email);
        }
        // 2) Simulate sending reset password email or generating a token
        System.out.println("Reset password link sent to " + email);
    }
}
