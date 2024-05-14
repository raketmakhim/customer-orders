package com.raket.customerorders.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CUSTOMERS")
@Getter
@Setter
public class Customer {

    public enum CustomerStatus {
        active,
        archived;
    }

    @Id
    private Long customerId;

    private String firstname;

    private String surname;

    private String email;

    private String address;

    private String zipCode;

    private String region;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;


}
