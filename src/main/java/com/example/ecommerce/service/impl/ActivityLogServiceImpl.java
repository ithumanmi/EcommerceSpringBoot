package com.example.ecommerce.service.impl;
import com.example.ecommerce.dto.ActivityLogDTO;
import com.example.ecommerce.model.ActivityLog;
import com.example.ecommerce.repository.ActivityLogRepository;
import com.example.ecommerce.service.ActivityLogService;
    import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ActivityLogServiceImpl implements ActivityLogService {
    private final ActivityLogRepository activityLogRepository;

    public ActivityLogServiceImpl(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @Override
    public ActivityLog logActivity(ActivityLogDTO logDTO) {
        ActivityLog log = new ActivityLog();
        log.setUserId(logDTO.getUserId());
        log.setUsername(logDTO.getUsername());
        log.setAction(logDTO.getAction());
        log.setEntityType(logDTO.getEntityType());
        log.setEntityId(logDTO.getEntityId());
        log.setDescription(logDTO.getDescription());
        log.setIpAddress(logDTO.getIpAddress());
        log.setUserAgent(logDTO.getUserAgent());
        return activityLogRepository.save(log);
    }

    @Override
    public List<ActivityLog> getAllLogs() {
        return activityLogRepository.findAll();
    }

    @Override
    public ActivityLog getLogById(@NonNull Long id) {
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
    public Page<ActivityLog> getAllLogsPaginated(@NonNull Pageable pageable) {
        return activityLogRepository.findAll(pageable);
    }

    @Override
    public void clearOldLogs(int daysToKeep) {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(daysToKeep);
        List<ActivityLog> oldLogs = activityLogRepository.findByDateRange(LocalDateTime.MIN, cutoffDate);
        if (oldLogs != null && !oldLogs.isEmpty()) {
            activityLogRepository.deleteAll(oldLogs);
        }
    }
}

