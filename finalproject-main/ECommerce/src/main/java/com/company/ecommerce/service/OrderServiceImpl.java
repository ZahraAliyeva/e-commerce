package com.company.ecommerce.service;

import com.company.ecommerce.entity.Order;
import com.company.ecommerce.entity.OrderStatus;
import com.company.ecommerce.entity.Product;
import com.company.ecommerce.repo.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl {
    @Autowired
    OrderRepository orderRepository;
    public OrderStatus getOrderStatus(Long orderId){
        Optional<Order> order = orderRepository.findById(orderId);
        return order.get().getStatus();
    }
    public Order changeOrderStatus(Order order, Long orderId){
        Order updatedOrder = new Order();
        Optional<Order> savedOrder = orderRepository.findById(orderId);
        updatedOrder = savedOrder.get();
        if(savedOrder.isPresent()){
            BeanUtils.copyProperties(order, updatedOrder);
        }
        updatedOrder=orderRepository.save(updatedOrder);
        return updatedOrder;
    }

    public List<Order> getOrders(){
       return orderRepository.findAll();
    }
}
