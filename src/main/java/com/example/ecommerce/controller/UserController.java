package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ApiResponse;
import com.example.ecommerce.dto.ChangePasswordDTO;
import com.example.ecommerce.dto.UpdateUserDTO;
import com.example.ecommerce.dto.UserProfileDTO;
import com.example.ecommerce.mapper.UserMapper;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Get all users (Admin only)
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserProfileDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(userMapper.toProfileDTOList(users));
    }

    /**
     * Get users with pagination (Admin only)
     */
    @GetMapping("/paginated")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserProfileDTO>> getUsersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("DESC") 
            ? Sort.by(sortBy).descending() 
            : Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> userPage = userService.getAllUsersPaginated(pageable);
        Page<UserProfileDTO> profilePage = userPage.map(userMapper::toProfileDTO);
        
        return ResponseEntity.ok(profilePage);
    }

    /**
     * Get user by ID (Admin only)
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserProfileDTO> getUserById(@PathVariable Long id) {
        UserProfileDTO userProfile = userService.getUserProfile(id);
        return ResponseEntity.ok(userProfile);
    }

    /**
     * Get current user's profile
     */
    @GetMapping("/me")
    public ResponseEntity<UserProfileDTO> getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        UserProfileDTO profile = userMapper.toProfileDTO(user);
        return ResponseEntity.ok(profile);
    }

    /**
     * Update user by ID (Admin only)
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserProfileDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserDTO updateUserDTO
    ) {
        UserProfileDTO updatedProfile = userService.updateUserProfile(id, updateUserDTO);
        return ResponseEntity.ok(updatedProfile);
    }

    /**
     * Update current user's profile
     */
    @PutMapping("/me")
    public ResponseEntity<UserProfileDTO> updateCurrentUser(
            Authentication authentication,
            @Valid @RequestBody UpdateUserDTO updateUserDTO
    ) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        UserProfileDTO updatedProfile = userService.updateUserProfile(user.getId(), updateUserDTO);
        return ResponseEntity.ok(updatedProfile);
    }

    /**
     * Delete user (Admin only)
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User deleted successfully"));
    }

    /**
     * Search users (Admin only)
     */
    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserProfileDTO>> searchUsers(@RequestParam String keyword) {
        List<User> users = userService.searchUsers(keyword);
        return ResponseEntity.ok(userMapper.toProfileDTOList(users));
    }

    /**
     * Get users by role (Admin only)
     */
    @GetMapping("/role/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserProfileDTO>> getUsersByRole(@PathVariable String role) {
        List<User> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(userMapper.toProfileDTOList(users));
    }

    /**
     * Update user role (Admin only)
     */
    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> updateUserRole(
            @PathVariable Long id,
            @RequestParam String role
    ) {
        userService.updateUserRole(id, role);
        return ResponseEntity.ok(new ApiResponse<>(true, "User role updated successfully"));
    }

    /**
     * Activate user (Admin only)
     */
    @PutMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> activateUser(@PathVariable Long id) {
        userService.activateUser(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User activated successfully"));
    }

    /**
     * Deactivate user (Admin only)
     */
    @PutMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User deactivated successfully"));
    }

    /**
     * Change password for current user
     */
    @PutMapping("/me/change-password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            Authentication authentication,
            @Valid @RequestBody ChangePasswordDTO changePasswordDTO
    ) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        userService.changePassword(user.getId(), changePasswordDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Password changed successfully"));
    }

    /**
     * Get user statistics (Admin only)
     */
    @GetMapping("/statistics")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getUserStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userService.getTotalUsersCount());
        stats.put("activeUsers", userService.getActiveUsersCount());
        stats.put("inactiveUsers", userService.getTotalUsersCount() - userService.getActiveUsersCount());
        return ResponseEntity.ok(stats);
    }
} 
