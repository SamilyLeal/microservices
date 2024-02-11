package com.ssl.accounts.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDTO {
    @NotEmpty(message = "Name can not be a null or empty!")
    @Size(min = 2, max = 40, message = "The length of the customer name should be between 3 and 40 characters!")
    private String name;
    @NotEmpty(message = "Email can not be a null or empty!")
    @Email(message = "Email invalid!")
    private String email;
    @NotEmpty(message = "Mobile number can not be a null or empty!")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must have 10 digits!")
    private String mobileNumber;

    private AccountDTO accountDTO;

    public CustomerDTO() {}

    public CustomerDTO(String name, String email, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
