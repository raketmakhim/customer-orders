package com.raket.customerorders.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "ORDERS")
public class Order {

    @Id
    private Long orderId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Long customerId;

    private Double amount;
}
