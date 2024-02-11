package com.ssl.accounts.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AccountDTO {
    @NotNull(message = "Account number can not be a null!")
    @Pattern(regexp = "\\d{10}", message = "Account number must be 10 digits!")
    @Min(1)
    private Long accountNumber;
    @NotEmpty(message = "Account type can not be a null or empty!")
    private String accountType;
    @NotEmpty(message = "Branch address can not be a null or empty!")
    private String branchAddress;

    public AccountDTO() {}

    public AccountDTO(Long accountNumber, String accountType, String branch_address) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.branchAddress = branch_address;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
}
