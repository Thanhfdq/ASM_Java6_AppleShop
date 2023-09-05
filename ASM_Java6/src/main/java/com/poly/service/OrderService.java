package com.poly.service;

import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;

public interface OrderService {
	public Order create(JsonNode orderData) ;

	public Order findById(Long id) ;
	public List<OrderDetail> findDetailByOrderId(Long OrderID);
	public List<Order> findAll() ;

	public List<Order> findByUsername(String username) ;
}
