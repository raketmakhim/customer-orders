package com.raket.customerorders.service;

import com.raket.customerorders.dto.CustomerOrdersDto;
import com.raket.customerorders.entity.Customer;
import com.raket.customerorders.entity.Order;
import com.raket.customerorders.repository.CustomerRepository;
import com.raket.customerorders.repository.OrderRepository;
import com.raket.customerorders.utils.Helper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerOrdersServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private CustomerOrdersService customerOrdersService;

    private Customer.CustomerStatus status;
    private List<Customer> customers;
    private List<Order> orders;

    @BeforeEach
    public void setUp() {
        status = Customer.CustomerStatus.active;

        customers = Helper.createCustomers(1L, "fname", "lname", "emial", "address", "zipCode", "reegion", status);

        orders = Helper.createOrder(1L, null, 1L, 20.00);
    }

    @Test
    public void getAllCustomersByStatus_ShouldReturnCustomerOrdersDtoList() {
        // Mocking repository methods
        when(customerRepository.findByStatus(status)).thenReturn(customers);
        when(orderRepository.findAll()).thenReturn(orders);

        // Act
        List<CustomerOrdersDto> result = customerOrdersService.getAllCustomersByStatus(status);

        // Assert
        assertEquals(customers.size(), result.size());
        assertEquals(customers.get(0).getFirstname(), result.get(0).getFirstname());
        assertEquals(orders, result.get(0).getOrders());
    }

    @Test
    public void getAllCustomers_ShouldReturnCustomerOrdersDtoList() {
        // Mocking repository methods
        when(customerRepository.findAll()).thenReturn(customers);
        when(orderRepository.findAll()).thenReturn(orders);

        // Act
        List<CustomerOrdersDto> result = customerOrdersService.getAllCustomers();

        // Assert
        assertEquals(customers.size(), result.size());
        assertEquals(customers.get(0).getFirstname(), result.get(0).getFirstname());
        assertEquals(orders, result.get(0).getOrders());
    }

    @Test
    public void getCustomerById_WithValidId_ShouldReturnCustomerOrdersDto() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setFirstname("John");
        customer.setSurname("Doe");
        // Mocking the repository method to return a customer with the given ID
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Mocking the repository method to return orders associated with the customer
        when(orderRepository.findAllByCustomerId(customerId)).thenReturn(orders);

        // Act
        CustomerOrdersDto result = customerOrdersService.getCustomerById(customerId);

        // Assert
        assertNotNull(result);
        assertEquals(customerId, result.getCustomerId());
        assertEquals(orders, result.getOrders());
    }

    @Test
    public void getCustomerById_WithInvalidId_ShouldThrowException() {
        // Arrange
        Long invalidCustomerId = 999L;
        // Mocking the repository method to return an empty Optional, simulating a non-existent customer
        when(customerRepository.findById(invalidCustomerId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> customerOrdersService.getCustomerById(invalidCustomerId));
    }

}