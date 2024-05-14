package com.raket.customerorders.dto;


import com.raket.customerorders.entity.Customer;
import com.raket.customerorders.entity.Order;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerOrdersDto {

    public CustomerOrdersDto(Customer customer, List<Order> orders) {
        this.customerId = customer.getCustomerId();
        this.firstname = customer.getFirstname();
        this.surname = customer.getSurname();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
        this.zipCode = customer.getZipCode();
        this.region = customer.getRegion();
        this.status = customer.getStatus();
        this.orders = orders;
    }

    private Long customerId;

    private String firstname;

    private String surname;

    private String email;

    private String address;

    private String zipCode;

    private String region;

    @Enumerated(EnumType.STRING)
    private Customer.CustomerStatus status;

    private List<Order> orders;

}
