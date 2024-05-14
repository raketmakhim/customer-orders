package com.raket.customerorders.repository;

import com.raket.customerorders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomerId(Long customerId);

}
