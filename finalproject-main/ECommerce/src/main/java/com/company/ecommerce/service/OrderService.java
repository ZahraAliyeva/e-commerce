package com.company.ecommerce.service;

import com.company.ecommerce.entity.OrderStatus;

public interface OrderService {
    OrderStatus getOrderStatus(Long orderId);
    void changeOrderStatus(Long orderId);
}
