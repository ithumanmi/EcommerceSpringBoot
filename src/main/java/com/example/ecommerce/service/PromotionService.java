package com.example.ecommerce.service;

import com.example.ecommerce.dto.PromotionDTO;
import com.example.ecommerce.model.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;

public interface PromotionService {
    List<Promotion> getAllPromotions();
    @NonNull Promotion getPromotionById(@NonNull Long id);
    Promotion createPromotion(PromotionDTO promotionDTO);
    @NonNull Promotion updatePromotion(@NonNull Long id, PromotionDTO promotionDTO);
    void deletePromotion(@NonNull Long id);
    
    List<Promotion> getActivePromotions();
    List<Promotion> getPromotionsByType(String promotionType);
    Page<Promotion> getAllPromotionsPaginated(@NonNull Pageable pageable);
    
    void activatePromotion(@NonNull Long id);
    void deactivatePromotion(@NonNull Long id);
}

