package com.example.ecommerce.service;

import com.example.ecommerce.dto.ChangePasswordDTO;
import com.example.ecommerce.dto.UpdateUserDTO;
import com.example.ecommerce.dto.UserProfileDTO;
import com.example.ecommerce.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserService {
    // Basic CRUD operations
    List<User> getAllUsers();
    @NonNull User getUserById(@NonNull Long id);
    User createUser(User user);
    @NonNull User updateUser(@NonNull Long id, UpdateUserDTO updateUserDTO);
    void deleteUser(@NonNull Long id);
    
    // User search and query
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> searchUsers(String keyword);
    Page<User> getAllUsersPaginated(@NonNull Pageable pageable);
    
    // User profile management
    UserProfileDTO getUserProfile(@NonNull Long id);
    UserProfileDTO updateUserProfile(@NonNull Long id, UpdateUserDTO updateUserDTO);
    
    // Password management
    void changePassword(@NonNull Long userId, ChangePasswordDTO changePasswordDTO);
    
    // User activation/deactivation
    void activateUser(@NonNull Long id);
    void deactivateUser(@NonNull Long id);
    
    // Role management
    void updateUserRole(@NonNull Long id, String role);
    List<User> getUsersByRole(String role);
    
    // Statistics
    long getTotalUsersCount();
    long getActiveUsersCount();
} 
