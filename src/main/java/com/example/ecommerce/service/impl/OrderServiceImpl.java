package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.CreateOrderDTO;
import com.example.ecommerce.dto.CreateOrderItemDTO;
import com.example.ecommerce.dto.OrderDTO;
import com.example.ecommerce.dto.OrderItemDTO;
import com.example.ecommerce.exception.InsufficientStockException;
import com.example.ecommerce.exception.OrderNotFoundException;
import com.example.ecommerce.exception.ProductNotFoundException;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public OrderDTO getOrderDTOById(Long id) {
        Order order = getOrderById(id);
        return convertToDTO(order);
    }

    @Override
    public Order createOrder(CreateOrderDTO createOrderDTO, Long userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderNumber(generateOrderNumber());
        order.setStatus("PENDING");
        order.setPaymentStatus("PENDING");
        order.setPaymentMethod(createOrderDTO.getPaymentMethod());
        order.setShippingAddress(createOrderDTO.getShippingAddress());
        order.setShippingCity(createOrderDTO.getShippingCity());
        order.setShippingCountry(createOrderDTO.getShippingCountry());
        order.setShippingPostalCode(createOrderDTO.getShippingPostalCode());
        order.setNotes(createOrderDTO.getNotes());
        order.setCouponCode(createOrderDTO.getCouponCode());
        
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;
        
        for (CreateOrderItemDTO itemDTO : createOrderDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(itemDTO.getProductId()));
            
            if (product.getStock() < itemDTO.getQuantity()) {
                throw new InsufficientStockException(product.getName(), itemDTO.getQuantity(), product.getStock());
            }
            
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getId());
            orderItem.setProductName(product.getName());
            orderItem.setProductSku(product.getSku());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setDiscountPrice(product.getDiscountPrice());
            
            BigDecimal itemPrice = product.getDiscountPrice() != null ? product.getDiscountPrice() : product.getPrice();
            BigDecimal itemSubtotal = itemPrice.multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            orderItem.setSubtotal(itemSubtotal);
            
            subtotal = subtotal.add(itemSubtotal);
            orderItems.add(orderItem);
            
            productService.decreaseStock(product.getId(), itemDTO.getQuantity());
            productService.incrementSoldCount(product.getId(), itemDTO.getQuantity());
        }
        
        order.setSubtotal(subtotal);
        order.setTaxAmount(createOrderDTO.getTaxAmount() != null ? createOrderDTO.getTaxAmount() : BigDecimal.ZERO);
        order.setShippingCost(createOrderDTO.getShippingCost() != null ? createOrderDTO.getShippingCost() : BigDecimal.ZERO);
        order.setDiscountAmount(BigDecimal.ZERO);
        
        BigDecimal total = subtotal
                .add(order.getTaxAmount())
                .add(order.getShippingCost())
                .subtract(order.getDiscountAmount());
        order.setTotal(total);
        
        Order savedOrder = orderRepository.save(order);
        
        for (OrderItem item : orderItems) {
            item.setOrderId(savedOrder.getId());
        }
        savedOrder.setItems(orderItems);
        
        return orderRepository.save(savedOrder);
    }

    @Override
    public void cancelOrder(Long id) {
        Order order = getOrderById(id);
        if ("CANCELLED".equals(order.getStatus())) {
            throw new RuntimeException("Order is already cancelled");
        }
        if ("COMPLETED".equals(order.getStatus()) || "DELIVERED".equals(order.getStatus())) {
            throw new RuntimeException("Cannot cancel completed or delivered order");
        }
        
        for (OrderItem item : order.getItems()) {
            productService.increaseStock(item.getProductId(), item.getQuantity());
            productService.incrementSoldCount(item.getProductId(), -item.getQuantity());
        }
        
        order.setStatus("CANCELLED");
        order.setCancelledAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    @Override
    public void updateOrderStatus(Long id, String status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        
        if ("COMPLETED".equals(status)) {
            order.setCompletedAt(LocalDateTime.now());
        }
        
        orderRepository.save(order);
    }

    @Override
    public void updatePaymentStatus(Long id, String paymentStatus) {
        Order order = getOrderById(id);
        order.setPaymentStatus(paymentStatus);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> getOrdersByPaymentStatus(String paymentStatus) {
        return orderRepository.findByPaymentStatus(paymentStatus);
    }

    @Override
    public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findOrdersByDateRange(startDate, endDate);
    }

    @Override
    public List<Order> getUserOrdersByStatus(Long userId, String status) {
        return orderRepository.findUserOrdersByStatus(userId, status);
    }

    @Override
    public List<Order> getRecentOrders() {
        return orderRepository.findRecentOrders();
    }

    @Override
    public Page<Order> getAllOrdersPaginated(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> getUserOrdersPaginated(Long userId, Pageable pageable) {
        List<Order> userOrders = orderRepository.findByUserId(userId);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), userOrders.size());
        List<Order> pageContent = userOrders.subList(start, end);
        return new PageImpl<>(pageContent, pageable, userOrders.size());
    }

    @Override
    public Order findByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with order number: " + orderNumber));
    }

    @Override
    public long getTotalOrdersCount() {
        return orderRepository.count();
    }

    @Override
    public long getOrderCountByStatus(String status) {
        return orderRepository.countByStatus(status);
    }

    @Override
    public long getOrdersCountFromDate(LocalDateTime startDate) {
        return orderRepository.countOrdersFromDate(startDate);
    }

    private String generateOrderNumber() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        long count = orderRepository.count() + 1;
        return "ORD" + timestamp + String.format("%04d", count);
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setSubtotal(order.getSubtotal());
        dto.setTaxAmount(order.getTaxAmount());
        dto.setShippingCost(order.getShippingCost());
        dto.setDiscountAmount(order.getDiscountAmount());
        dto.setTotal(order.getTotal());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setPaymentStatus(order.getPaymentStatus());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setShippingCity(order.getShippingCity());
        dto.setShippingCountry(order.getShippingCountry());
        dto.setShippingPostalCode(order.getShippingPostalCode());
        dto.setNotes(order.getNotes());
        dto.setCouponCode(order.getCouponCode());
        dto.setUpdatedAt(order.getUpdatedAt());
        dto.setCancelledAt(order.getCancelledAt());
        dto.setCompletedAt(order.getCompletedAt());
        
        if (order.getItems() != null) {
            dto.setItems(order.getItems().stream().map(this::convertItemToDTO).collect(Collectors.toList()));
        }
        
        return dto;
    }

    private OrderItemDTO convertItemToDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setOrderId(item.getOrderId());
        dto.setProductId(item.getProductId());
        dto.setProductName(item.getProductName());
        dto.setProductSku(item.getProductSku());
        dto.setPrice(item.getPrice());
        dto.setDiscountPrice(item.getDiscountPrice());
        dto.setQuantity(item.getQuantity());
        dto.setSubtotal(item.getSubtotal());
        return dto;
    }
} 
