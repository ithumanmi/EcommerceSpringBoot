package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.UpdateUserDTO;
import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.dto.UserProfileDTO;
import com.example.ecommerce.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for User entity and DTO conversion
 */
@Component
public class UserMapper {

    /**
     * Convert User entity to UserDTO (without password)
     */
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setRole(user.getRole());
        dto.setCreatedAt(user.getCreatedAt());
        
        return dto;
    }

    /**
     * Convert User entity to UserProfileDTO (complete profile without password)
     */
    public UserProfileDTO toProfileDTO(User user) {
        if (user == null) {
            return null;
        }

        UserProfileDTO dto = new UserProfileDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setRole(user.getRole());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAddress(user.getAddress());
        dto.setCity(user.getCity());
        dto.setCountry(user.getCountry());
        dto.setPostalCode(user.getPostalCode());
        dto.setIsActive(user.getIsActive());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setLastLogin(user.getLastLogin());
        
        return dto;
    }

    /**
     * Convert UserDTO to User entity
     */
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setRole(dto.getRole());
        user.setPassword(dto.getPassword()); // Include password for registration
        
        return user;
    }

    /**
     * Update User entity from UpdateUserDTO
     */
    public void updateUserFromDTO(UpdateUserDTO dto, User user) {
        if (dto == null || user == null) {
            return;
        }

        if (dto.getFullName() != null) {
            user.setFullName(dto.getFullName());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPhoneNumber() != null) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getAddress() != null) {
            user.setAddress(dto.getAddress());
        }
        if (dto.getCity() != null) {
            user.setCity(dto.getCity());
        }
        if (dto.getCountry() != null) {
            user.setCountry(dto.getCountry());
        }
        if (dto.getPostalCode() != null) {
            user.setPostalCode(dto.getPostalCode());
        }
    }

    /**
     * Convert User entity to UserDTO for registration response (without password)
     */
    public UserDTO toRegistrationDTO(User user) {
        UserDTO dto = toDTO(user);
        // Ensure password is not included in response
        if (dto != null) {
            dto.setPassword(null);
        }
        return dto;
    }

    /**
     * Convert list of User entities to list of UserDTOs
     */
    public List<UserDTO> toDTOList(List<User> users) {
        if (users == null) {
            return null;
        }
        
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convert list of User entities to list of UserProfileDTOs
     */
    public List<UserProfileDTO> toProfileDTOList(List<User> users) {
        if (users == null) {
            return null;
        }
        
        return users.stream()
                .map(this::toProfileDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convert list of UserDTOs to list of User entities
     */
    public List<User> toEntityList(List<UserDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
