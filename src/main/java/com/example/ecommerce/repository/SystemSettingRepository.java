package com.example.ecommerce.repository;

import com.example.ecommerce.model.SystemSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemSettingRepository extends JpaRepository<SystemSetting, Long> {
    Optional<SystemSetting> findBySettingKey(String settingKey);
    List<SystemSetting> findBySettingType(String settingType);
    List<SystemSetting> findByIsEditable(Boolean isEditable);
}

