package com.ssl.loans.mapper;

import com.ssl.loans.dto.LoanDTO;
import com.ssl.loans.entities.Loan;

public class LoanMapper {
    private LoanMapper() {}

    public static Loan toLoan(LoanDTO loanDTO, Loan loan) {
        loan.setLoanNumber(loanDTO.getLoanNumber());
        loan.setMobileNumber(loanDTO.getMobileNumber());
        loan.setLoanType(loanDTO.getLoanType());
        loan.setAmountPaid(loanDTO.getAmountPaid());
        loan.setTotalLoan(loanDTO.getTotalLoan());
        loan.setOutstandingAmount(loanDTO.getOutstandingAmount());

        return loan;
    }

    public static LoanDTO toLoanDTO(Loan loan, LoanDTO loanDTO) {
        loanDTO.setLoanNumber(loan.getLoanNumber());
        loanDTO.setMobileNumber(loan.getMobileNumber());
        loanDTO.setLoanType(loan.getLoanType());
        loanDTO.setAmountPaid(loan.getAmountPaid());
        loanDTO.setTotalLoan(loan.getTotalLoan());
        loanDTO.setOutstandingAmount(loan.getOutstandingAmount());

        return loanDTO;
    }

}
