package com.ssl.accounts.dto;

public class CustomerDetailsDTO {
    private String name;
    private String mobileNumber;
    private String email;
    private AccountDTO accountDTO;
    private CardDTO cardDTO;
    private LoanDTO loanDTO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public CardDTO getCardDTO() {
        return cardDTO;
    }

    public void setCardDTO(CardDTO cardDTO) {
        this.cardDTO = cardDTO;
    }

    public LoanDTO getLoanDTO() {
        return loanDTO;
    }

    public void setLoanDTO(LoanDTO loanDTO) {
        this.loanDTO = loanDTO;
    }
}
