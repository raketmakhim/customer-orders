package com.raket.customerorders.utils;

import com.raket.customerorders.dto.CustomerFullNameDto;
import com.raket.customerorders.dto.CustomerOrdersDto;
import com.raket.customerorders.entity.Customer;
import com.raket.customerorders.entity.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static List<Customer> createCustomers(Long customerId, String firstname, String surname, String email,
                                                 String address, String zipCode, String region, Customer.CustomerStatus status) {

        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setFirstname(firstname);
        customer.setSurname(surname);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setZipCode(zipCode);
        customer.setRegion(region);
        customer.setStatus(status);
        customers.add(customer);
        return customers;
    }

    public static List<Order> createOrder(Long orderId, LocalDate date, Long customerId, Double amount) {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setOrderId(orderId);
        order.setDate(date);
        order.setCustomerId(customerId);
        order.setAmount(amount);

        orders.add(order);
        return orders;
    }

    public static CustomerOrdersDto createCustomerOrdersDto() {
        CustomerOrdersDto customerOrdersDto = new CustomerOrdersDto();
        customerOrdersDto.setCustomerId(1L);
        customerOrdersDto.setFirstname("fname");
        customerOrdersDto.setSurname("lname");
        customerOrdersDto.setEmail("email");
        customerOrdersDto.setAddress("address");
        customerOrdersDto.setZipCode("zip");
        customerOrdersDto.setRegion("region");
        customerOrdersDto.setStatus(Customer.CustomerStatus.active);
        customerOrdersDto.setOrders(null);
        return customerOrdersDto;
    }

    public static CustomerFullNameDto createCustomerFullNameDto(CustomerOrdersDto customer) {
        CustomerFullNameDto customerFullNameDto = new CustomerFullNameDto();
        customerFullNameDto.setCustomerId(customer.getCustomerId());
        customerFullNameDto.setName(customer.getFirstname() + ' ' + customer.getSurname());
        customerFullNameDto.setEmail(customer.getEmail());
        customerFullNameDto.setAddress(customer.getAddress());
        customerFullNameDto.setZipCode(customer.getZipCode());
        customerFullNameDto.setRegion(customer.getRegion());
        customerFullNameDto.setStatus(customer.getStatus());
        customerFullNameDto.setOrders(customer.getOrders());
        return customerFullNameDto;
    }


}
