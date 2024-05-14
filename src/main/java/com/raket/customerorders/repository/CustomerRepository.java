package com.raket.customerorders.repository;

import com.raket.customerorders.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByStatus(Customer.CustomerStatus status);

}
