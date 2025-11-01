package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.SystemSettingDTO;
import com.example.ecommerce.exception.SystemSettingNotEditableException;
import com.example.ecommerce.exception.SystemSettingNotFoundException;
import com.example.ecommerce.model.SystemSetting;
import com.example.ecommerce.repository.SystemSettingRepository;
import com.example.ecommerce.service.SystemSettingService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SystemSettingServiceImpl implements SystemSettingService {
    private final SystemSettingRepository systemSettingRepository;

    public SystemSettingServiceImpl(SystemSettingRepository systemSettingRepository) {
        this.systemSettingRepository = systemSettingRepository;
    }

    @Override
    public List<SystemSetting> getAllSettings() {
        return systemSettingRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public @NonNull SystemSetting getSettingById(@NonNull Long id) {
        return systemSettingRepository.findById(id)
                .orElseThrow(() -> new SystemSettingNotFoundException(id));
    }

    @Override
    public SystemSetting getSettingByKey(String key) {
        return systemSettingRepository.findBySettingKey(key)
                .orElseThrow(() -> new SystemSettingNotFoundException("Setting not found with key: " + key));
    }

    @Override
    public String getSettingValue(String key, String defaultValue) {
        return systemSettingRepository.findBySettingKey(key)
                .map(SystemSetting::getSettingValue)
                .orElse(defaultValue);
    }

    @Override
    public SystemSetting createSetting(SystemSettingDTO settingDTO) {
        SystemSetting setting = new SystemSetting();
        setting.setSettingKey(settingDTO.getSettingKey());
        setting.setSettingValue(settingDTO.getSettingValue());
        setting.setSettingType(settingDTO.getSettingType());
        setting.setDescription(settingDTO.getDescription());
        setting.setIsEditable(settingDTO.getIsEditable() != null ? settingDTO.getIsEditable() : true);
        return systemSettingRepository.save(setting);
    }

    @Override
    public @NonNull SystemSetting updateSetting(@NonNull Long id, SystemSettingDTO settingDTO) {
        SystemSetting setting = getSettingById(id);
        
        if (!Boolean.TRUE.equals(setting.getIsEditable())) {
            throw new SystemSettingNotEditableException("This setting is not editable");
        }
        
        if (settingDTO.getSettingValue() != null) setting.setSettingValue(settingDTO.getSettingValue());
        if (settingDTO.getSettingType() != null) setting.setSettingType(settingDTO.getSettingType());
        if (settingDTO.getDescription() != null) setting.setDescription(settingDTO.getDescription());
        
        return systemSettingRepository.save(setting);
    }

    @Override
    public SystemSetting updateSettingByKey(String key, String value) {
        SystemSetting setting = getSettingByKey(key);
        
        if (!Boolean.TRUE.equals(setting.getIsEditable())) {
            throw new SystemSettingNotEditableException("This setting is not editable");
        }
        
        setting.setSettingValue(value);
        return systemSettingRepository.save(setting);
    }

    @Override
    public void deleteSetting(@NonNull Long id) {
        SystemSetting setting = getSettingById(id);
        
        if (!Boolean.TRUE.equals(setting.getIsEditable())) {
            throw new SystemSettingNotEditableException("This setting cannot be deleted");
        }
        
        systemSettingRepository.delete(setting);
    }

    @Override
    public List<SystemSetting> getSettingsByType(String settingType) {
        return systemSettingRepository.findBySettingType(settingType);
    }

    @Override
    public List<SystemSetting> getEditableSettings() {
        return systemSettingRepository.findByIsEditable(true);
    }
}

