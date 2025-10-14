package com.example.ecommerce.service;

import com.example.ecommerce.dto.CustomerAddressDTO;
import com.example.ecommerce.dto.CustomerPreferenceDTO;
import com.example.ecommerce.dto.CustomerStatsDTO;
import com.example.ecommerce.model.CustomerAddress;
import com.example.ecommerce.model.CustomerPreference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<CustomerAddress> getCustomerAddresses(Long userId);
    CustomerAddress getCustomerAddressById(Long addressId);
    CustomerAddress addCustomerAddress(Long userId, CustomerAddressDTO addressDTO);
    CustomerAddress updateCustomerAddress(Long userId, Long addressId, CustomerAddressDTO addressDTO);
    void deleteCustomerAddress(Long userId, Long addressId);
    void setDefaultAddress(Long userId, Long addressId);
    CustomerAddress getDefaultAddress(Long userId);
    
    CustomerPreference getCustomerPreferences(Long userId);
    CustomerPreference updateCustomerPreferences(Long userId, CustomerPreferenceDTO preferenceDTO);
    
    CustomerStatsDTO getCustomerStats(Long userId);
    List<CustomerStatsDTO> getAllCustomerStats();
    Page<CustomerStatsDTO> getAllCustomerStatsPaginated(Pageable pageable);
    
    List<CustomerStatsDTO> getTopCustomersBySpending(int limit);
    List<CustomerStatsDTO> getTopCustomersByOrders(int limit);
}

