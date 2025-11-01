package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.PromotionDTO;
import com.example.ecommerce.exception.PromotionNotFoundException;
import com.example.ecommerce.model.Promotion;
import com.example.ecommerce.repository.PromotionRepository;
import com.example.ecommerce.service.PromotionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public @NonNull Promotion getPromotionById(@NonNull Long id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new PromotionNotFoundException(id));
    }

    @Override
    public Promotion createPromotion(PromotionDTO promotionDTO) {
        Promotion promotion = new Promotion();
        promotion.setName(promotionDTO.getName());
        promotion.setDescription(promotionDTO.getDescription());
        promotion.setPromotionType(promotionDTO.getPromotionType());
        promotion.setDiscountValue(promotionDTO.getDiscountValue());
        promotion.setMinPurchaseAmount(promotionDTO.getMinPurchaseAmount());
        promotion.setStartDate(promotionDTO.getStartDate());
        promotion.setEndDate(promotionDTO.getEndDate());
        promotion.setIsActive(promotionDTO.getIsActive() != null ? promotionDTO.getIsActive() : true);
        promotion.setPriority(promotionDTO.getPriority() != null ? promotionDTO.getPriority() : 0);
        promotion.setApplicableTo(promotionDTO.getApplicableTo());
        promotion.setBannerImage(promotionDTO.getBannerImage());
        
        return promotionRepository.save(promotion);
    }

    @Override
    public @NonNull Promotion updatePromotion(@NonNull Long id, PromotionDTO promotionDTO) {
        Promotion promotion = getPromotionById(id);
        
        if (promotionDTO.getName() != null) promotion.setName(promotionDTO.getName());
        if (promotionDTO.getDescription() != null) promotion.setDescription(promotionDTO.getDescription());
        if (promotionDTO.getPromotionType() != null) promotion.setPromotionType(promotionDTO.getPromotionType());
        if (promotionDTO.getDiscountValue() != null) promotion.setDiscountValue(promotionDTO.getDiscountValue());
        if (promotionDTO.getMinPurchaseAmount() != null) promotion.setMinPurchaseAmount(promotionDTO.getMinPurchaseAmount());
        if (promotionDTO.getStartDate() != null) promotion.setStartDate(promotionDTO.getStartDate());
        if (promotionDTO.getEndDate() != null) promotion.setEndDate(promotionDTO.getEndDate());
        if (promotionDTO.getIsActive() != null) promotion.setIsActive(promotionDTO.getIsActive());
        if (promotionDTO.getPriority() != null) promotion.setPriority(promotionDTO.getPriority());
        if (promotionDTO.getApplicableTo() != null) promotion.setApplicableTo(promotionDTO.getApplicableTo());
        if (promotionDTO.getBannerImage() != null) promotion.setBannerImage(promotionDTO.getBannerImage());
        
        return promotionRepository.save(promotion);
    }

    @Override
    public void deletePromotion(@NonNull Long id) {
        promotionRepository.delete(getPromotionById(id));
    }

    @Override
    public List<Promotion> getActivePromotions() {
        return promotionRepository.findActivePromotions(LocalDateTime.now());
    }

    @Override
    public List<Promotion> getPromotionsByType(String promotionType) {
        return promotionRepository.findByPromotionType(promotionType);
    }

    @Override
    public Page<Promotion> getAllPromotionsPaginated(@NonNull Pageable pageable) {
        return promotionRepository.findAll(pageable);
    }

    @Override
    public void activatePromotion(@NonNull Long id) {
        Promotion promotion = getPromotionById(id);
        promotion.setIsActive(true);
        promotionRepository.save(promotion);
    }

    @Override
    public void deactivatePromotion(@NonNull Long id) {
        Promotion promotion = getPromotionById(id);
        promotion.setIsActive(false);
        promotionRepository.save(promotion);
    }
}

