package com.ssl.accounts.services;

import com.ssl.accounts.dto.CustomerDetailsDTO;

public interface CustomerService {
    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber);
}
