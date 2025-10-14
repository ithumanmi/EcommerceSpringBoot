package com.example.ecommerce.service;

import com.example.ecommerce.dto.ActivityLogDTO;
import com.example.ecommerce.model.ActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityLogService {
    ActivityLog logActivity(Long userId, String username, String action, String entityType, Long entityId, String description, String ipAddress, String userAgent);
    List<ActivityLog> getAllLogs();
    ActivityLog getLogById(Long id);
    List<ActivityLog> getLogsByUser(Long userId);
    List<ActivityLog> getLogsByAction(String action);
    List<ActivityLog> getLogsByEntityType(String entityType);
    List<ActivityLog> getLogsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<ActivityLog> getUserLogsByDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    List<ActivityLog> getRecentActivities();
    Page<ActivityLog> getAllLogsPaginated(Pageable pageable);
    void clearOldLogs(int daysToKeep);
}

