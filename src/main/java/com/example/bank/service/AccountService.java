package com.example.bank.service;

import com.example.bank.dto.AccountDto;
import com.example.bank.dto.TransferRequestDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
    public void transferAmount(TransferRequestDto transferRequestDto);
}
