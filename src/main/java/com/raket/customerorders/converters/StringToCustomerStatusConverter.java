package com.raket.customerorders.converters;

import com.raket.customerorders.entity.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCustomerStatusConverter implements Converter<String, Customer.CustomerStatus> {

    @Override
    public Customer.CustomerStatus convert(String source) {
        try {
            return Customer.CustomerStatus.valueOf(source.toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid customer status: " + source);
        }
    }
}
