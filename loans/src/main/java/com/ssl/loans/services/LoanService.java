package com.ssl.loans.services;

import com.ssl.loans.dto.LoanDTO;

public interface LoanService {
    void createLoan(String mobileNumber);
    LoanDTO fetchLoan(String mobileNumber);
    boolean updateLoan(LoanDTO loanDTO);
    boolean deleteLoan(String mobileNumber);
}
