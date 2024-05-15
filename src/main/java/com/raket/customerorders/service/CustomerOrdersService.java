package com.raket.customerorders.service;

import com.raket.customerorders.dto.CustomerOrdersDto;
import com.raket.customerorders.entity.Customer;
import com.raket.customerorders.entity.Order;
import com.raket.customerorders.repository.CustomerRepository;
import com.raket.customerorders.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

    @Service
    public class CustomerOrdersService {

        private static final Logger logger = LogManager.getLogger(CustomerOrdersService.class);

        private final CustomerRepository customerRepository;
        private final OrderRepository orderRepository;

        public CustomerOrdersService(CustomerRepository customerRepository, OrderRepository orderRepository) {
            this.customerRepository = customerRepository;
            this.orderRepository = orderRepository;
        }

        public List<CustomerOrdersDto> getAllCustomersByStatus(Customer.CustomerStatus status) {

            logger.info("Fetching all customers with status: {}", status);
            List<Customer> customers = customerRepository.findByStatus(status);

            return mapToCustomersOrdersDto(customers);
        }

        public List<CustomerOrdersDto> getAllCustomers() {

            logger.info("Fetching all customers.");
            List<Customer> customers = customerRepository.findAll();

            return mapToCustomersOrdersDto(customers);
        }

        public CustomerOrdersDto getCustomerById(Long customerId) {

            logger.info("Fetching customer with ID: {}", customerId);
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

            return new CustomerOrdersDto(customer, orderRepository.findAllByCustomerId(customerId));
        }

        public List<CustomerOrdersDto> mapToCustomersOrdersDto(List<Customer> customers){

            logger.info("Mapping customers to DTOs.");
            List<Order> orders = orderRepository.findAll();

            return customers.stream()
                    .map(customer -> mapToCustomerOrdersDto(customer, orders))
                    .collect(Collectors.toList());
        }

        public CustomerOrdersDto mapToCustomerOrdersDto(Customer customer, List<Order> orders) {

            logger.info("Mapping customer to DTO for customerId: {} , firstnmae : {}, surname: {}", customer.getCustomerId(), customer.getFirstname(), customer.getSurname());
            List<Order> customerOrders = orders.stream()
                    .filter(order -> order.getCustomerId().equals(customer.getCustomerId()))
                    .collect(Collectors.toList());

            return new CustomerOrdersDto(customer, customerOrders);
        }
    }
