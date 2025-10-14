package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ApiResponse;
import com.example.ecommerce.dto.CustomerAddressDTO;
import com.example.ecommerce.dto.CustomerPreferenceDTO;
import com.example.ecommerce.dto.CustomerStatsDTO;
import com.example.ecommerce.model.CustomerAddress;
import com.example.ecommerce.model.CustomerPreference;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.CustomerService;
import com.example.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @GetMapping("/my-addresses")
    public ResponseEntity<List<CustomerAddress>> getMyAddresses(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<CustomerAddress> addresses = customerService.getCustomerAddresses(user.getId());
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/my-addresses/{id}")
    public ResponseEntity<CustomerAddress> getMyAddressById(
            @PathVariable Long id,
            Authentication authentication
    ) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        CustomerAddress address = customerService.getCustomerAddressById(id);
        
        if (!address.getUserId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }
        
        return ResponseEntity.ok(address);
    }

    @PostMapping("/my-addresses")
    public ResponseEntity<CustomerAddress> addMyAddress(
            @Valid @RequestBody CustomerAddressDTO addressDTO,
            Authentication authentication
    ) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        CustomerAddress address = customerService.addCustomerAddress(user.getId(), addressDTO);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/my-addresses/{id}")
    public ResponseEntity<CustomerAddress> updateMyAddress(
            @PathVariable Long id,
            @Valid @RequestBody CustomerAddressDTO addressDTO,
            Authentication authentication
    ) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        CustomerAddress address = customerService.updateCustomerAddress(user.getId(), id, addressDTO);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/my-addresses/{id}")
    public ResponseEntity<ApiResponse> deleteMyAddress(
            @PathVariable Long id,
            Authentication authentication
    ) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        customerService.deleteCustomerAddress(user.getId(), id);
        return ResponseEntity.ok(new ApiResponse(true, "Address deleted successfully"));
    }

    @PutMapping("/my-addresses/{id}/set-default")
    public ResponseEntity<ApiResponse> setDefaultAddress(
            @PathVariable Long id,
            Authentication authentication
    ) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        customerService.setDefaultAddress(user.getId(), id);
        return ResponseEntity.ok(new ApiResponse(true, "Default address set successfully"));
    }

    @GetMapping("/my-addresses/default")
    public ResponseEntity<CustomerAddress> getMyDefaultAddress(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        CustomerAddress address = customerService.getDefaultAddress(user.getId());
        return ResponseEntity.ok(address);
    }

    @GetMapping("/my-preferences")
    public ResponseEntity<CustomerPreference> getMyPreferences(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        CustomerPreference preferences = customerService.getCustomerPreferences(user.getId());
        return ResponseEntity.ok(preferences);
    }

    @PutMapping("/my-preferences")
    public ResponseEntity<CustomerPreference> updateMyPreferences(
            @Valid @RequestBody CustomerPreferenceDTO preferenceDTO,
            Authentication authentication
    ) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        CustomerPreference preferences = customerService.updateCustomerPreferences(user.getId(), preferenceDTO);
        return ResponseEntity.ok(preferences);
    }

    @GetMapping("/my-stats")
    public ResponseEntity<CustomerStatsDTO> getMyStats(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        CustomerStatsDTO stats = customerService.getCustomerStats(user.getId());
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/{userId}/stats")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerStatsDTO> getCustomerStats(@PathVariable Long userId) {
        CustomerStatsDTO stats = customerService.getCustomerStats(userId);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/stats")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CustomerStatsDTO>> getAllCustomerStats() {
        List<CustomerStatsDTO> stats = customerService.getAllCustomerStats();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/stats/paginated")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<CustomerStatsDTO>> getAllCustomerStatsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerStatsDTO> stats = customerService.getAllCustomerStatsPaginated(pageable);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/top-spending")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CustomerStatsDTO>> getTopCustomersBySpending(
            @RequestParam(defaultValue = "10") int limit
    ) {
        List<CustomerStatsDTO> customers = customerService.getTopCustomersBySpending(limit);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/top-orders")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CustomerStatsDTO>> getTopCustomersByOrders(
            @RequestParam(defaultValue = "10") int limit
    ) {
        List<CustomerStatsDTO> customers = customerService.getTopCustomersByOrders(limit);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{userId}/addresses")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CustomerAddress>> getCustomerAddresses(@PathVariable Long userId) {
        List<CustomerAddress> addresses = customerService.getCustomerAddresses(userId);
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{userId}/preferences")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerPreference> getCustomerPreferences(@PathVariable Long userId) {
        CustomerPreference preferences = customerService.getCustomerPreferences(userId);
        return ResponseEntity.ok(preferences);
    }
}

