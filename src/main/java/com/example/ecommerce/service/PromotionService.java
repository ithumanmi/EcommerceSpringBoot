package com.example.ecommerce.service;

import com.example.ecommerce.dto.PromotionDTO;
import com.example.ecommerce.model.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PromotionService {
    List<Promotion> getAllPromotions();
    Promotion getPromotionById(Long id);
    Promotion createPromotion(PromotionDTO promotionDTO);
    Promotion updatePromotion(Long id, PromotionDTO promotionDTO);
    void deletePromotion(Long id);
    
    List<Promotion> getActivePromotions();
    List<Promotion> getPromotionsByType(String promotionType);
    Page<Promotion> getAllPromotionsPaginated(Pageable pageable);
    
    void activatePromotion(Long id);
    void deactivatePromotion(Long id);
}

