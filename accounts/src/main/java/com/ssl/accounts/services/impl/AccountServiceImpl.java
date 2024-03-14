package com.ssl.accounts.services.impl;

import com.ssl.accounts.dto.AccountDTO;
import com.ssl.accounts.dto.CustomerDTO;
import com.ssl.accounts.entities.Account;
import com.ssl.accounts.entities.Customer;
import com.ssl.accounts.exceptions.CustomerAlreadyExistsException;
import com.ssl.accounts.exceptions.ResourceNotFound;
import com.ssl.accounts.mapper.AccountMapper;
import com.ssl.accounts.mapper.CustomerMapper;
import com.ssl.accounts.repositories.AccountRepository;
import com.ssl.accounts.repositories.CustomerRepository;
import com.ssl.accounts.services.AccountService;
import com.ssl.accounts.utils.AccountConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());

        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number: " + customerDTO.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);

        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 1_000_000_000 + new Random().nextInt(900_000_000);

        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountConstants.SAVING);
        newAccount.setBranchAddress("Street uwu");
        newAccount.setCreatedBy("salami");
        newAccount.setCreatedAt(LocalDateTime.now());

        return newAccount;
    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("Customer", "mobile number", mobileNumber)
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFound("Account", "costumer id", customer.getCustomerId().toString())
        );

        CustomerDTO customerDTO = CustomerMapper.toCustomerDto(customer, new CustomerDTO());
        customerDTO.setAccountDTO(AccountMapper.toAccountDTO(account, new AccountDTO()));

        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountDTO accountDTO = customerDTO.getAccountDTO();

        if(accountDTO != null) {
            Account account = accountRepository.findById(accountDTO.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFound("Account", "account number", accountDTO.getAccountNumber().toString())
            );

            AccountMapper.toAccount(accountDTO, account);
            account = accountRepository.save(account);

            Long costumerId = account.getCustomerId();
            Customer customer = customerRepository.findById(costumerId).orElseThrow(
                    () -> new ResourceNotFound("Customer", "costumer id", costumerId.toString())
            );

            CustomerMapper.toCustomer(customerDTO, customer);
            customer.setCustomerId(costumerId);

            customerRepository.save(customer);

            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("Customer", "mobile number", mobileNumber)
        );

        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.delete(customer);

        return true;
    }

}
