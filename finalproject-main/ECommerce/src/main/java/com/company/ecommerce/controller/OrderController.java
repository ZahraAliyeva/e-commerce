package com.company.ecommerce.controller;

import com.company.ecommerce.entity.Order;
import com.company.ecommerce.service.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order",method = RequestMethod.GET)
//@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<?> getOrderStatus(@PathVariable Long orderId){
        return new ResponseEntity<>(orderService.getOrderStatus(orderId), HttpStatus.OK);
    }

    @PutMapping(value = "/update-status/{orderId}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @RequestBody Order order){
        return new ResponseEntity<>(orderService.changeOrderStatus(order, orderId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getOrders(){
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }
}
