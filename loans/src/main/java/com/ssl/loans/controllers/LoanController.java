package com.ssl.loans.controllers;

import com.ssl.loans.dto.AccountsContactInfoDTO;
import com.ssl.loans.dto.LoanDTO;
import com.ssl.loans.dto.ResponseDTO;
import com.ssl.loans.services.LoanService;
import com.ssl.loans.utils.LoanConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
@EnableConfigurationProperties(AccountsContactInfoDTO.class)
public class LoanController {
    private final LoanService loanService;

    @Autowired
    private AccountsContactInfoDTO accountsContactInfoDTO;

    @Value("${build.version}")
    private String buildVersion;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam @Pattern(regexp="\\d{10}", message = "Mobile Number invalid!") String mobileNumber) {
        loanService.createLoan(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201)
        );
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoanDTO> fetchLoan(@RequestParam @Pattern(regexp="\\d{10}", message = "Mobile Number invalid!") String mobileNumber) {
        LoanDTO loanDTO = loanService.fetchLoan(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(loanDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateLoan(@Valid @RequestBody LoanDTO loanDTO) {
        boolean isUpdated = loanService.updateLoan(loanDTO);

        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(
              new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200)
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseDTO(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE)
            );
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLoan(@RequestParam @Pattern(regexp="\\d{10}", message = "Mobile Number invalid!") String mobileNumber) {
        boolean isDeleted = loanService.deleteLoan(mobileNumber);

        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200)
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseDTO(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE)
            );
        }
    }

    @GetMapping("/build-version")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @GetMapping("/accounts-contact")
    public ResponseEntity<AccountsContactInfoDTO> getAccountsContact() {
        return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfoDTO);
    }

}
