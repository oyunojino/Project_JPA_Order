package com.example.jpaorder.repository;

import com.example.jpaorder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
