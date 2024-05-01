package com.ssl.accounts.controllers;

import com.ssl.accounts.dto.CustomerDetailsDTO;
import com.ssl.accounts.services.CustomerService;
import jakarta.validation.constraints.Pattern;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@RequestParam
                                                                   @Pattern(regexp="(^$|[0-9]{10})",
                                                                           message = "Mobile number must be 10 digits")
                                                                   String mobileNumber){
        CustomerDetailsDTO customerDetailsDTO = customerService.fetchCustomerDetails(mobileNumber);

        return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetailsDTO);
    }
}
