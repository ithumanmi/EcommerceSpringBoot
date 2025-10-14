package com.example.ecommerce.service;

import com.example.ecommerce.dto.SystemSettingDTO;
import com.example.ecommerce.model.SystemSetting;

import java.util.List;

public interface SystemSettingService {
    List<SystemSetting> getAllSettings();
    SystemSetting getSettingById(Long id);
    SystemSetting getSettingByKey(String key);
    String getSettingValue(String key, String defaultValue);
    SystemSetting createSetting(SystemSettingDTO settingDTO);
    SystemSetting updateSetting(Long id, SystemSettingDTO settingDTO);
    SystemSetting updateSettingByKey(String key, String value);
    void deleteSetting(Long id);
    List<SystemSetting> getSettingsByType(String settingType);
    List<SystemSetting> getEditableSettings();
}

