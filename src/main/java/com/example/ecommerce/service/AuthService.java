package com.example.ecommerce.service;

import com.example.ecommerce.dto.AuthResponse;
import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.RegisterRequest;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse login(LoginRequest loginRequest) {
        // Validate input
        if (loginRequest == null || !StringUtils.hasText(loginRequest.getUsername()) || !StringUtils.hasText(loginRequest.getPassword())) {
            throw new IllegalArgumentException("Username and password are required");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            User user = userRepository.findByUsername(loginRequest.getUsername());
            if (user == null) {
                throw new RuntimeException("User not found after authentication");
            }

            return new AuthResponse(token, user.getUsername(), user.getRole());
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        // Validate input
        if (registerRequest == null) {
            throw new IllegalArgumentException("Registration data cannot be null");
        }
        
        if (!StringUtils.hasText(registerRequest.getUsername())) {
            throw new IllegalArgumentException("Username is required");
        }
        
        if (!StringUtils.hasText(registerRequest.getPassword())) {
            throw new IllegalArgumentException("Password is required");
        }
        
        if (!StringUtils.hasText(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email is required");
        }

        // Check if user already exists
        if (userRepository.findByUsername(registerRequest.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Check if email already exists
        if (userRepository.findByEmail(registerRequest.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setFullName(registerRequest.getFullName());
        user.setRole("USER"); // Default role

        userRepository.save(user);
        return new AuthResponse("User registered successfully. Please login.");
    }
}