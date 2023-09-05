package com.poly.dao;

import com.poly.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.entity.OrderDetail;

import java.util.List;

@Repository
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
    @Query("SELECT o FROM OrderDetail o WHERE o.order.id=?1")
    List<OrderDetail> findByOrderId(Long orderId);
}
