package com.ssl.accounts.mapper;

import com.ssl.accounts.dto.CustomerDTO;
import com.ssl.accounts.dto.CustomerDetailsDTO;
import com.ssl.accounts.entities.Customer;

public class CustomerMapper {

    private CustomerMapper() {}

    public static CustomerDTO toCustomerDto(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());

        return customerDTO;
    }

    public static CustomerDetailsDTO toCustomerDetailsDto(Customer customer, CustomerDetailsDTO customerDetailsDTO) {
        customerDetailsDTO.setName(customer.getName());
        customerDetailsDTO.setEmail(customer.getEmail());
        customerDetailsDTO.setMobileNumber(customer.getMobileNumber());

        return customerDetailsDTO;
    }

    public static Customer toCustomer(CustomerDTO customerDTO, Customer customer) {
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());

        return customer;
    }
}
