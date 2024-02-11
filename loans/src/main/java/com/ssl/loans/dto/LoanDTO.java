package com.ssl.loans.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class LoanDTO {
    @NotEmpty(message = "Mobile number can not be a null or empty")
    @Pattern(regexp="\\d{10}",message = "Mobile Number must be 10 digits")
    private String mobileNumber;
    @NotEmpty(message = "Loan number can not be a null or empty")
    @Pattern(regexp="\\d{12}",message = "LoanNumber must be 12 digits")
    private String loanNumber;
    @NotEmpty(message = "Loan type can not be a null or empty")
    private String loanType;
    @Positive(message = "Total loan amount should be greater than zero")
    private Double totalLoan;
    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    private Double amountPaid;
    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    private Double outstandingAmount;

    public LoanDTO() {}

    public LoanDTO(String mobileNumber, String loanNumber, String loanType, Double totalLoan, Double amountPaid,
                   Double outstandingAmount) {
        this.mobileNumber = mobileNumber;
        this.loanNumber = loanNumber;
        this.loanType = loanType;
        this.totalLoan = totalLoan;
        this.amountPaid = amountPaid;
        this.outstandingAmount = outstandingAmount;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Double getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(Double totalLoan) {
        this.totalLoan = totalLoan;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Double getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(Double outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }
}
