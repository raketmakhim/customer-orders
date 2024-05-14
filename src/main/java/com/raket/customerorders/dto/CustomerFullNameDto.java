package com.raket.customerorders.dto;

import com.raket.customerorders.entity.Customer;
import com.raket.customerorders.entity.Order;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerFullNameDto {

    public CustomerFullNameDto(CustomerOrdersDto customer) {
        this.customerId = customer.getCustomerId();
        this.name = customer.getFirstname() + ' ' + customer.getSurname();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
        this.zipCode = customer.getZipCode();
        this.region = customer.getRegion();
        this.status = customer.getStatus();
        this.orders = customer.getOrders();
    }

    private Long customerId;

    private String name;

    private String email;

    private String address;

    private String zipCode;

    private String region;

    @Enumerated(EnumType.STRING)
    private Customer.CustomerStatus status;

    private List<Order> orders;

}