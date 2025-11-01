package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.ChangePasswordDTO;
import com.example.ecommerce.dto.UpdateUserDTO;
import com.example.ecommerce.dto.UserProfileDTO;
import com.example.ecommerce.exception.InvalidPasswordException;
import com.example.ecommerce.exception.UserAlreadyExistsException;
import com.example.ecommerce.exception.UserNotFoundException;
import com.example.ecommerce.mapper.UserMapper;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final String ROLE_PREFIX = "ROLE_";

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Basic CRUD operations
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public @NonNull User getUserById(@NonNull Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User createUser(User user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("username", user.getUsername());
        }
        
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("email", user.getEmail());
        }
        
        // Set default values
        if (user.getIsActive() == null) {
            user.setIsActive(true);
        }
        if (user.getRole() == null) {
            user.setRole("ROLE_USER");
        }
        
        return userRepository.save(user);
    }

    @Override
    public @NonNull User updateUser(@NonNull Long id, UpdateUserDTO updateUserDTO) {
        User existingUser = getUserById(id);
        
        // Check if email is being changed and if new email already exists
        if (updateUserDTO.getEmail() != null && 
            !updateUserDTO.getEmail().equals(existingUser.getEmail())) {
            User userWithEmail = userRepository.findByEmail(updateUserDTO.getEmail());
            if (userWithEmail != null && !userWithEmail.getId().equals(id)) {
                throw new UserAlreadyExistsException("email", updateUserDTO.getEmail());
            }
        }
        
        userMapper.updateUserFromDTO(updateUserDTO, existingUser);
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(@NonNull Long id) {
        userRepository.delete(getUserById(id));
    }

    // User search and query
    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("username", username);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("email", email);
        }
        return user;
    }

    @Override
    public List<User> searchUsers(String keyword) {
        return userRepository.searchUsers(keyword);
    }

    @Override
    public Page<User> getAllUsersPaginated(@NonNull Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    // User profile management
    @Override
    public UserProfileDTO getUserProfile(@NonNull Long id) {
        User user = getUserById(id);
        return userMapper.toProfileDTO(user);
    }

    @Override
    public UserProfileDTO updateUserProfile(@NonNull Long id, UpdateUserDTO updateUserDTO) {
        User updatedUser = updateUser(id, updateUserDTO);
        return userMapper.toProfileDTO(updatedUser);
    }

    // Password management
    @Override
    public void changePassword(@NonNull Long userId, ChangePasswordDTO changePasswordDTO) {
        User user = getUserById(userId);
        
        // Verify current password
        if (!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Current password is incorrect");
        }
        
        // Verify new password and confirm password match
        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
            throw new InvalidPasswordException("New password and confirm password do not match");
        }
        
        // Update password
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    // User activation/deactivation
    @Override
    public void activateUser(@NonNull Long id) {
        User user = getUserById(id);
        user.setIsActive(true);
        userRepository.save(user);
    }

    @Override
    public void deactivateUser(@NonNull Long id) {
        User user = getUserById(id);
        user.setIsActive(false);
        userRepository.save(user);
    }

    // Role management
    @Override
    public void updateUserRole(@NonNull Long id, String role) {
        User user = getUserById(id);
        // Ensure role starts with ROLE_ prefix
        if (!role.startsWith(ROLE_PREFIX)) {
            role = ROLE_PREFIX + role;
        }
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        // Ensure role starts with ROLE_ prefix
        if (!role.startsWith(ROLE_PREFIX)) {
            role = ROLE_PREFIX + role;
        }
        return userRepository.findByRole(role);
    }

    // Statistics
    @Override
    public long getTotalUsersCount() {
        return userRepository.count();
    }

    @Override
    public long getActiveUsersCount() {
        return userRepository.countByIsActive(true);
    }
} 
