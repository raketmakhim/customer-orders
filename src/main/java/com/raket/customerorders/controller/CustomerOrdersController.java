package com.raket.customerorders.controller;

import com.raket.customerorders.dto.CustomerOrdersDto;
import com.raket.customerorders.entity.Customer;
import com.raket.customerorders.service.CustomerOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerOrdersController {

    @Autowired
    private CustomerOrdersService CustomerOrdersService;

    @GetMapping("/customers")
    public List<CustomerOrdersDto> getAllCustomers(@RequestParam(required = false) Customer.CustomerStatus status) {
        if (status != null) {
            return CustomerOrdersService.getAllCustomersByStatus(status);
        } else {
            return CustomerOrdersService.getAllCustomers();
        }
    }

    @GetMapping("/customers/{customer_id}")
    public CustomerOrdersDto getCustomerById(@PathVariable("customer_id") Long customerId) {
        return CustomerOrdersService.getCustomerById(customerId);
    }
}
