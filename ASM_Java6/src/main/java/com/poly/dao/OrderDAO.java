package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.entity.Order;
@Repository
public interface OrderDAO extends JpaRepository<Order, Long>{

}
