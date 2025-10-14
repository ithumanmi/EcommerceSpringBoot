package com.example.ecommerce.repository;

import com.example.ecommerce.model.CustomerPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerPreferenceRepository extends JpaRepository<CustomerPreference, Long> {
    Optional<CustomerPreference> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}

