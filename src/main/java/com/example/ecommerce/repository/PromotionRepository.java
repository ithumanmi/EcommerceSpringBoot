package com.example.ecommerce.repository;

import com.example.ecommerce.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByIsActive(Boolean isActive);
    
    @Query("SELECT p FROM Promotion p WHERE p.isActive = true AND " +
           "(p.startDate IS NULL OR p.startDate <= :now) AND " +
           "(p.endDate IS NULL OR p.endDate >= :now) " +
           "ORDER BY p.priority DESC")
    List<Promotion> findActivePromotions(@Param("now") LocalDateTime now);
    
    List<Promotion> findByPromotionType(String promotionType);
}

