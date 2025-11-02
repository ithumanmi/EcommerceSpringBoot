package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ApiResponse;
import com.example.ecommerce.dto.DashboardStatsDTO;
import com.example.ecommerce.dto.SystemSettingDTO;
import com.example.ecommerce.model.ActivityLog;
import com.example.ecommerce.model.SystemSetting;
import com.example.ecommerce.service.ActivityLogService;
import com.example.ecommerce.service.AdminDashboardService;
import com.example.ecommerce.service.SystemSettingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final AdminDashboardService dashboardService;
    private final SystemSettingService systemSettingService;
    private final ActivityLogService activityLogService;

    public AdminController(AdminDashboardService dashboardService, SystemSettingService systemSettingService, ActivityLogService activityLogService) {
        this.dashboardService = dashboardService;
        this.systemSettingService = systemSettingService;
        this.activityLogService = activityLogService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
        DashboardStatsDTO stats = dashboardService.getDashboardStats();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/settings")
    public ResponseEntity<List<SystemSetting>> getAllSettings() {
        List<SystemSetting> settings = systemSettingService.getAllSettings();
        return ResponseEntity.ok(settings);
    }

    @GetMapping("/settings/{id}")
    public ResponseEntity<SystemSetting> getSettingById(@PathVariable @NonNull Long id) {
        SystemSetting setting = systemSettingService.getSettingById(id);
        return ResponseEntity.ok(setting);
    }

    @GetMapping("/settings/key/{key}")
    public ResponseEntity<SystemSetting> getSettingByKey(@PathVariable String key) {
        SystemSetting setting = systemSettingService.getSettingByKey(key);
        return ResponseEntity.ok(setting);
    }

    @GetMapping("/settings/type/{settingType}")
    public ResponseEntity<List<SystemSetting>> getSettingsByType(@PathVariable String settingType) {
        List<SystemSetting> settings = systemSettingService.getSettingsByType(settingType);
        return ResponseEntity.ok(settings);
    }

    @GetMapping("/settings/editable")
    public ResponseEntity<List<SystemSetting>> getEditableSettings() {
        List<SystemSetting> settings = systemSettingService.getEditableSettings();
        return ResponseEntity.ok(settings);
    }

    @PostMapping("/settings")
    public ResponseEntity<SystemSetting> createSetting(@Valid @RequestBody SystemSettingDTO settingDTO) {
        SystemSetting setting = systemSettingService.createSetting(settingDTO);
        return ResponseEntity.ok(setting);
    }

    @PutMapping("/settings/{id}")
    public ResponseEntity<SystemSetting> updateSetting(
            @PathVariable @NonNull Long id,
            @Valid @RequestBody SystemSettingDTO settingDTO
    ) {
        SystemSetting setting = systemSettingService.updateSetting(id, settingDTO);
        return ResponseEntity.ok(setting);
    }

    @PutMapping("/settings/key/{key}")
    public ResponseEntity<SystemSetting> updateSettingByKey(
            @PathVariable String key,
            @RequestParam String value
    ) {
        SystemSetting setting = systemSettingService.updateSettingByKey(key, value);
        return ResponseEntity.ok(setting);
    }

    @DeleteMapping("/settings/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSetting(@PathVariable @NonNull Long id) {
        systemSettingService.deleteSetting(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Setting deleted successfully"));
    }

    @GetMapping("/activity-logs")
    public ResponseEntity<List<ActivityLog>> getAllActivityLogs() {
        List<ActivityLog> logs = activityLogService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/activity-logs/paginated")
    public ResponseEntity<Page<ActivityLog>> getActivityLogsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<ActivityLog> logs = activityLogService.getAllLogsPaginated(pageable);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/activity-logs/{id}")
    public ResponseEntity<ActivityLog> getActivityLogById(@PathVariable @NonNull Long id) {
        ActivityLog log = activityLogService.getLogById(id);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/activity-logs/user/{userId}")
    public ResponseEntity<List<ActivityLog>> getActivityLogsByUser(@PathVariable @NonNull Long userId) {
        List<ActivityLog> logs = activityLogService.getLogsByUser(userId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/activity-logs/action/{action}")
    public ResponseEntity<List<ActivityLog>> getActivityLogsByAction(@PathVariable String action) {
        List<ActivityLog> logs = activityLogService.getLogsByAction(action);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/activity-logs/entity/{entityType}")
    public ResponseEntity<List<ActivityLog>> getActivityLogsByEntityType(@PathVariable String entityType) {
        List<ActivityLog> logs = activityLogService.getLogsByEntityType(entityType);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/activity-logs/date-range")
    public ResponseEntity<List<ActivityLog>> getActivityLogsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<ActivityLog> logs = activityLogService.getLogsByDateRange(startDate, endDate);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/activity-logs/recent")
    public ResponseEntity<List<ActivityLog>> getRecentActivities() {
        List<ActivityLog> logs = activityLogService.getRecentActivities();
        return ResponseEntity.ok(logs);
    }

    @DeleteMapping("/activity-logs/cleanup")
    public ResponseEntity<ApiResponse<Void>> cleanupOldLogs(@RequestParam(defaultValue = "90") int daysToKeep) {
        activityLogService.clearOldLogs(daysToKeep);
        return ResponseEntity.ok(new ApiResponse<>(true, "Old activity logs cleared successfully"));
    }
}

