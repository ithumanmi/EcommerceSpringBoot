package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.UserDTO;
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
