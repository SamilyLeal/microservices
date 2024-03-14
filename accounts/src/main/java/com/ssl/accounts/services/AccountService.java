package com.ssl.accounts.services;

import com.ssl.accounts.dto.CustomerDTO;

public interface AccountService {
    void createAccount(CustomerDTO customerDTO);
    CustomerDTO fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDTO customerDTO);
    boolean deleteAccount(String mobileNumber);
}
