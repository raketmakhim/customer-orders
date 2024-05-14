package com.raket.customerorders.controller;

import com.raket.customerorders.entity.Customer;
import com.raket.customerorders.service.CustomerOrdersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerOrdersControllerTest {

    @Mock
    private CustomerOrdersService customerOrdersService;

    @InjectMocks
    private CustomerOrdersController customerOrdersController;

    @Test
    void getAllCustomers_WithStatus_ShouldCallGetAllCustomersByStatus() {
        // Arrange
        Customer.CustomerStatus status = Customer.CustomerStatus.active;

        // Act
        customerOrdersController.getAllCustomers(status);

        // Assert
        verify(customerOrdersService).getAllCustomersByStatus(status);
    }

    @Test
    void getAllCustomers_WithoutStatus_ShouldCallGetAllCustomers() {
        // Act
        customerOrdersController.getAllCustomers(null);

        // Assert
        verify(customerOrdersService).getAllCustomers();
    }

    @Test
    void getCustomerById_ShouldCallGetCustomerById() {
        // Arrange
        Long customerId = 123L;

        // Act
        customerOrdersController.getCustomerById(customerId);

        // Assert
        verify(customerOrdersService).getCustomerById(customerId);
    }
}
