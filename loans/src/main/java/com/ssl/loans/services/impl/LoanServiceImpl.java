package com.ssl.loans.services.impl;

import com.ssl.loans.dto.LoanDTO;
import com.ssl.loans.entities.Loan;
import com.ssl.loans.exceptions.LoanAlreadyExistsException;
import com.ssl.loans.exceptions.ResourceNotFoundException;
import com.ssl.loans.mapper.LoanMapper;
import com.ssl.loans.repositories.LoanRepository;
import com.ssl.loans.services.LoanService;
import com.ssl.loans.utils.LoanConstants;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> loan = loanRepository.findByMobileNumber(mobileNumber);

        if(loan.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber!");
        }

        loanRepository.save(createNewLoan(mobileNumber));
    }

    public Loan createNewLoan(String mobileNumber) {
        Loan loan = new Loan();
        loan.setMobileNumber(mobileNumber);
        long loanNumber = 100_000_000_000L + new Random().nextInt(900_000_000);
        loan.setLoanNumber(Long.toString(loanNumber));
        loan.setLoanType(LoanConstants.HOME_LOAN);
        loan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        loan.setAmountPaid(0D);
        loan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        loan.setCreatedBy("salami");
        loan.setCreatedAt(LocalDateTime.now());

        return loan;
    }

    @Override
    public LoanDTO fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );

        return LoanMapper.toLoanDTO(loan, new LoanDTO());
    }

    @Override
    public boolean updateLoan(LoanDTO loanDTO) {
        Loan loan = loanRepository.findByLoanNumber(loanDTO.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "loanNumber", loanDTO.getLoanNumber())
        );

        loanRepository.save(LoanMapper.toLoan(loanDTO, loan));

        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );

        loanRepository.delete(loan);

        return true;
    }
}
