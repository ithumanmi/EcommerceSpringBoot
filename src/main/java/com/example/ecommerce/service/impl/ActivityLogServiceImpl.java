package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.ActivityLogDTO;
import com.example.ecommerce.model.ActivityLog;
import com.example.ecommerce.repository.ActivityLogRepository;
import com.example.ecommerce.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ActivityLogServiceImpl implements ActivityLogService {
    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Override
    public ActivityLog logActivity(Long userId, String username, String action, String entityType, Long entityId, String description, String ipAddress, String userAgent) {
        ActivityLog log = new ActivityLog();
        log.setUserId(userId);
        log.setUsername(username);
        log.setAction(action);
        log.setEntityType(entityType);
        log.setEntityId(entityId);
        log.setDescription(description);
        log.setIpAddress(ipAddress);
        log.setUserAgent(userAgent);
        return activityLogRepository.save(log);
    }

    @Override
    public List<ActivityLog> getAllLogs() {
        return activityLogRepository.findAll();
    }

    @Override
    public ActivityLog getLogById(Long id) {
        return activityLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity log not found with ID: " + id));
    }

    @Override
    public List<ActivityLog> getLogsByUser(Long userId) {
        return activityLogRepository.findByUserId(userId);
    }

    @Override
    public List<ActivityLog> getLogsByAction(String action) {
        return activityLogRepository.findByAction(action);
    }

    @Override
    public List<ActivityLog> getLogsByEntityType(String entityType) {
        return activityLogRepository.findByEntityType(entityType);
    }

    @Override
    public List<ActivityLog> getLogsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return activityLogRepository.findByDateRange(startDate, endDate);
    }

    @Override
    public List<ActivityLog> getUserLogsByDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return activityLogRepository.findByUserIdAndDateRange(userId, startDate, endDate);
    }

    @Override
    public List<ActivityLog> getRecentActivities() {
        return activityLogRepository.findRecentActivities();
    }

    @Override
    public Page<ActivityLog> getAllLogsPaginated(Pageable pageable) {
        return activityLogRepository.findAll(pageable);
    }

    @Override
    public void clearOldLogs(int daysToKeep) {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(daysToKeep);
        List<ActivityLog> oldLogs = activityLogRepository.findByDateRange(LocalDateTime.MIN, cutoffDate);
        activityLogRepository.deleteAll(oldLogs);
    }
}

