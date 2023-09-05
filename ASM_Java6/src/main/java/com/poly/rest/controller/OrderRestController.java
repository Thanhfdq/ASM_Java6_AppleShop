package com.poly.rest.controller;

import com.poly.entity.OrderDetail;
import com.poly.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Order;
import com.poly.service.OrderService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.findAll();
    }

    @GetMapping("/{orderId}")
    public List<OrderDetail> getOrderDetail(@PathVariable("orderId") Long orderId){
        return orderService.findDetailByOrderId(orderId);
    }

    @PostMapping
    public ResponseEntity<Order> purchase(@RequestBody JsonNode orderData) {
        return new ResponseEntity<Order>(orderService.create(orderData), HttpStatus.OK);
    }
}
