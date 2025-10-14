package com.example.ecommerce.service;

import com.example.ecommerce.dto.ChangePasswordDTO;
import com.example.ecommerce.dto.UpdateUserDTO;
import com.example.ecommerce.dto.UserProfileDTO;
import com.example.ecommerce.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    // Basic CRUD operations
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, UpdateUserDTO updateUserDTO);
    void deleteUser(Long id);
    
    // User search and query
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> searchUsers(String keyword);
    Page<User> getAllUsersPaginated(Pageable pageable);
    
    // User profile management
    UserProfileDTO getUserProfile(Long id);
    UserProfileDTO updateUserProfile(Long id, UpdateUserDTO updateUserDTO);
    
    // Password management
    void changePassword(Long userId, ChangePasswordDTO changePasswordDTO);
    
    // User activation/deactivation
    void activateUser(Long id);
    void deactivateUser(Long id);
    
    // Role management
    void updateUserRole(Long id, String role);
    List<User> getUsersByRole(String role);
    
    // Statistics
    long getTotalUsersCount();
    long getActiveUsersCount();
} 
