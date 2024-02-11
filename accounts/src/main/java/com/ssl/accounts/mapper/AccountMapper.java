package com.ssl.accounts.mapper;

import com.ssl.accounts.dto.AccountDTO;
import com.ssl.accounts.entities.Account;

public class AccountMapper {

    private AccountMapper() {}

    public static AccountDTO toAccountDTO(Account account, AccountDTO accountDTO) {
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setBranchAddress(account.getBranchAddress());

        return accountDTO;
    }

    public static Account toAccount(AccountDTO accountDTO, Account account) {
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setAccountType(accountDTO.getAccountType());
        account.setBranchAddress(accountDTO.getBranchAddress());

        return account;
    }
}
