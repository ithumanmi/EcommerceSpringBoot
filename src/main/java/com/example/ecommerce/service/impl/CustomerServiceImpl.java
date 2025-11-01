package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.CustomerAddressDTO;
import com.example.ecommerce.dto.CustomerPreferenceDTO;
import com.example.ecommerce.dto.CustomerStatsDTO;
import com.example.ecommerce.exception.CustomerAddressAccessDeniedException;
import com.example.ecommerce.exception.CustomerAddressNotFoundException;
import com.example.ecommerce.exception.UserNotFoundException;
import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private static final String ORDER_STATUS_CANCELLED = "CANCELLED";
    private static final String ORDER_STATUS_PENDING = "PENDING";
    private static final String ORDER_STATUS_COMPLETED = "COMPLETED";

    private final CustomerAddressRepository addressRepository;
    private final CustomerPreferenceRepository preferenceRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public CustomerServiceImpl(
            CustomerAddressRepository addressRepository,
            CustomerPreferenceRepository preferenceRepository,
            UserRepository userRepository,
            OrderRepository orderRepository
    ) {
        this.addressRepository = addressRepository;
        this.preferenceRepository = preferenceRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<CustomerAddress> getCustomerAddresses(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    @SuppressWarnings("null")
    public @NonNull CustomerAddress getCustomerAddressById(@NonNull Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new CustomerAddressNotFoundException(addressId));
    }

    @Override
    @SuppressWarnings("null")
    public CustomerAddress addCustomerAddress(Long userId, CustomerAddressDTO addressDTO) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        CustomerAddress address = new CustomerAddress();
        address.setUserId(userId);
        address.setAddressLabel(addressDTO.getAddressLabel());
        address.setFullName(addressDTO.getFullName());
        address.setPhoneNumber(addressDTO.getPhoneNumber());
        address.setAddressLine1(addressDTO.getAddressLine1());
        address.setAddressLine2(addressDTO.getAddressLine2());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setIsDefault(Boolean.TRUE.equals(addressDTO.getIsDefault()));

        if (Boolean.TRUE.equals(address.getIsDefault())) {
            addressRepository.clearDefaultAddresses(userId);
        }

        return addressRepository.save(address);
    }

    @Override
    public CustomerAddress updateCustomerAddress(Long userId, @NonNull Long addressId, CustomerAddressDTO addressDTO) {
        CustomerAddress address = getCustomerAddressById(addressId);
        
        if (!address.getUserId().equals(userId)) {
            throw new CustomerAddressAccessDeniedException("Address does not belong to user");
        }

        if (addressDTO.getAddressLabel() != null) address.setAddressLabel(addressDTO.getAddressLabel());
        if (addressDTO.getFullName() != null) address.setFullName(addressDTO.getFullName());
        if (addressDTO.getPhoneNumber() != null) address.setPhoneNumber(addressDTO.getPhoneNumber());
        if (addressDTO.getAddressLine1() != null) address.setAddressLine1(addressDTO.getAddressLine1());
        if (addressDTO.getAddressLine2() != null) address.setAddressLine2(addressDTO.getAddressLine2());
        if (addressDTO.getCity() != null) address.setCity(addressDTO.getCity());
        if (addressDTO.getState() != null) address.setState(addressDTO.getState());
        if (addressDTO.getCountry() != null) address.setCountry(addressDTO.getCountry());
        if (addressDTO.getPostalCode() != null) address.setPostalCode(addressDTO.getPostalCode());
        
        if (addressDTO.getIsDefault() != null && addressDTO.getIsDefault()) {
            addressRepository.clearDefaultAddresses(userId);
            address.setIsDefault(true);
        }

        return addressRepository.save(address);
    }

    @Override
    public void deleteCustomerAddress(Long userId, @NonNull Long addressId) {
        CustomerAddress address = getCustomerAddressById(addressId);
        
        if (!address.getUserId().equals(userId)) {
            throw new CustomerAddressAccessDeniedException("Address does not belong to user");
        }

        addressRepository.delete(address);
    }

    @Override
    public void setDefaultAddress(Long userId, @NonNull Long addressId) {
        CustomerAddress address = getCustomerAddressById(addressId);
        
        if (!address.getUserId().equals(userId)) {
            throw new CustomerAddressAccessDeniedException("Address does not belong to user");
        }

        addressRepository.clearDefaultAddresses(userId);
        address.setIsDefault(true);
        addressRepository.save(address);
    }

    @Override
    public CustomerAddress getDefaultAddress(Long userId) {
        return addressRepository.findByUserIdAndIsDefault(userId, true)
                .orElse(null);
    }

    @Override
    @SuppressWarnings("null")
    public CustomerPreference getCustomerPreferences(Long userId) {
        return preferenceRepository.findByUserId(userId)
                .orElseGet(() -> {
                    CustomerPreference pref = new CustomerPreference();
                    pref.setUserId(userId);
                    return preferenceRepository.save(pref);
                });
    }

    @Override
    public CustomerPreference updateCustomerPreferences(Long userId, CustomerPreferenceDTO preferenceDTO) {
        CustomerPreference preference = getCustomerPreferences(userId);

        if (preferenceDTO.getEmailNotifications() != null) {
            preference.setEmailNotifications(preferenceDTO.getEmailNotifications());
        }
        if (preferenceDTO.getSmsNotifications() != null) {
            preference.setSmsNotifications(preferenceDTO.getSmsNotifications());
        }
        if (preferenceDTO.getNewsletterSubscription() != null) {
            preference.setNewsletterSubscription(preferenceDTO.getNewsletterSubscription());
        }
        if (preferenceDTO.getPreferredLanguage() != null) {
            preference.setPreferredLanguage(preferenceDTO.getPreferredLanguage());
        }
        if (preferenceDTO.getPreferredCurrency() != null) {
            preference.setPreferredCurrency(preferenceDTO.getPreferredCurrency());
        }

        return preferenceRepository.save(preference);
    }

    @Override
    public CustomerStatsDTO getCustomerStats(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        List<Order> orders = orderRepository.findByUserId(userId);

        CustomerStatsDTO stats = new CustomerStatsDTO();
        stats.setUserId(user.getId());
        stats.setUsername(user.getUsername());
        stats.setFullName(user.getFullName());
        stats.setEmail(user.getEmail());
        stats.setIsActive(user.getIsActive());
        stats.setJoinDate(user.getCreatedAt());
        stats.setTotalOrders(orders.size());

        BigDecimal totalSpent = orders.stream()
                .filter(o -> !ORDER_STATUS_CANCELLED.equals(o.getStatus()))
                .map(Order::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        stats.setTotalSpent(totalSpent);

        long totalProducts = orders.stream()
                .filter(o -> !ORDER_STATUS_CANCELLED.equals(o.getStatus()))
                .flatMap(o -> o.getItems().stream())
                .mapToInt(OrderItem::getQuantity)
                .sum();
        stats.setTotalProducts((int) totalProducts);

        stats.setPendingOrders((int) orders.stream().filter(o -> ORDER_STATUS_PENDING.equals(o.getStatus())).count());
        stats.setCompletedOrders((int) orders.stream().filter(o -> ORDER_STATUS_COMPLETED.equals(o.getStatus())).count());
        stats.setCancelledOrders((int) orders.stream().filter(o -> ORDER_STATUS_CANCELLED.equals(o.getStatus())).count());

        orders.stream()
                .min(Comparator.comparing(Order::getOrderDate))
                .ifPresent(o -> stats.setFirstOrderDate(o.getOrderDate()));

        orders.stream()
                .max(Comparator.comparing(Order::getOrderDate))
                .ifPresent(o -> stats.setLastOrderDate(o.getOrderDate()));

        return stats;
    }

    @Override
    @SuppressWarnings("null")
    public List<CustomerStatsDTO> getAllCustomerStats() {
        List<User> customers = userRepository.findByRole("ROLE_USER");
        return customers.stream()
                .map(user -> getCustomerStats(user.getId()))
                .toList();
    }

    @Override
    public Page<CustomerStatsDTO> getAllCustomerStatsPaginated(Pageable pageable) {
        List<CustomerStatsDTO> allStats = getAllCustomerStats();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allStats.size());
        List<CustomerStatsDTO> pageContent = allStats.subList(start, end);
        return new PageImpl<>(pageContent, pageable, allStats.size());
    }

    @Override
    public List<CustomerStatsDTO> getTopCustomersBySpending(int limit) {
        return getAllCustomerStats().stream()
                .sorted(Comparator.comparing(CustomerStatsDTO::getTotalSpent).reversed())
                .limit(limit)
                .toList();
    }

    @Override
    public List<CustomerStatsDTO> getTopCustomersByOrders(int limit) {
        return getAllCustomerStats().stream()
                .sorted(Comparator.comparing(CustomerStatsDTO::getTotalOrders).reversed())
                .limit(limit)
                .toList();
    }
}

