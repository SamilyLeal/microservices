package com.ssl.accounts.services.impl;

import com.ssl.accounts.dto.AccountDTO;
import com.ssl.accounts.dto.CardDTO;
import com.ssl.accounts.dto.CustomerDetailsDTO;
import com.ssl.accounts.dto.LoanDTO;
import com.ssl.accounts.entities.Account;
import com.ssl.accounts.entities.Customer;
import com.ssl.accounts.exceptions.ResourceNotFound;
import com.ssl.accounts.mapper.AccountMapper;
import com.ssl.accounts.mapper.CustomerMapper;
import com.ssl.accounts.repositories.AccountRepository;
import com.ssl.accounts.repositories.CustomerRepository;
import com.ssl.accounts.services.CustomerService;
import com.ssl.accounts.services.client.CardsFeignClient;
import com.ssl.accounts.services.client.LoansFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    public CustomerServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository,
                               CardsFeignClient cardsFeignClient, LoansFeignClient loansFeignClient) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.cardsFeignClient = cardsFeignClient;
        this.loansFeignClient = loansFeignClient;
    }

    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("Customer", "mobile number", mobileNumber)
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFound("Account", "costumer id", customer.getCustomerId().toString())
        );

        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.toCustomerDetailsDto(customer, new CustomerDetailsDTO());
        customerDetailsDTO.setAccountDTO(AccountMapper.toAccountDTO(account, new AccountDTO()));

        ResponseEntity<CardDTO> cardDTOResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDTO.setCardDTO(cardDTOResponseEntity.getBody());

        ResponseEntity<LoanDTO> loanDTOResponseEntity = loansFeignClient.fetchLoan(mobileNumber);
        customerDetailsDTO.setLoanDTO(loanDTOResponseEntity.getBody());

        return customerDetailsDTO;
    }
}
