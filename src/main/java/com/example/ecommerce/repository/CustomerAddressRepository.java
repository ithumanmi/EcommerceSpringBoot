package com.example.ecommerce.repository;

import com.example.ecommerce.model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
    List<CustomerAddress> findByUserId(Long userId);
    Optional<CustomerAddress> findByUserIdAndIsDefault(Long userId, Boolean isDefault);
    
    @Modifying
    @Query("UPDATE CustomerAddress a SET a.isDefault = false WHERE a.userId = :userId")
    void clearDefaultAddresses(@Param("userId") Long userId);
    
    long countByUserId(Long userId);
}

