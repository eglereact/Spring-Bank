package com.example.bank.mapper;

import com.example.bank.dto.AccountDto;
import com.example.bank.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(accountDto.getId(), accountDto.getAccountHolderName(), accountDto.getBalance(),accountDto.getAccountNumber());
        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(account.getId(), account.getAccountHolderName(),account.getBalance(),account.getAccountNumber());
        return accountDto;
    }
}
