package com.example.ecommerce.service;

import com.example.ecommerce.dto.SystemSettingDTO;
import com.example.ecommerce.model.SystemSetting;
import org.springframework.lang.NonNull;

import java.util.List;

public interface SystemSettingService {
    List<SystemSetting> getAllSettings();
    @NonNull SystemSetting getSettingById(@NonNull Long id);
    SystemSetting getSettingByKey(String key);
    String getSettingValue(String key, String defaultValue);
    SystemSetting createSetting(SystemSettingDTO settingDTO);
    @NonNull SystemSetting updateSetting(@NonNull Long id, SystemSettingDTO settingDTO);
    SystemSetting updateSettingByKey(String key, String value);
    void deleteSetting(@NonNull Long id);
    List<SystemSetting> getSettingsByType(String settingType);
    List<SystemSetting> getEditableSettings();
}

