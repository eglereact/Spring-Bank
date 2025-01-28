package com.example.bank.service.impl;

import com.example.bank.dto.AccountDto;
import com.example.bank.dto.TransferRequestDto;
import com.example.bank.entity.Account;
import com.example.bank.entity.TransferRequest;
import com.example.bank.mapper.AccountMapper;
import com.example.bank.mapper.TransferRequestMapper;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.TransferRequestRepository;
import com.example.bank.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private TransferRequestRepository transferRequestRepository;

    public AccountServiceImpl(AccountRepository accountRepository, TransferRequestRepository transferRequestRepository) {
        this.accountRepository = accountRepository;
        this.transferRequestRepository = transferRequestRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        double total =  account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        if (account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }

        double total =  account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
       List<Account> accounts = accountRepository.findAll();
      return accounts
              .stream()
              .map(AccountMapper::mapToAccountDto)
              .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        accountRepository.deleteById(id);
    }

    @Override
    public void transferAmount(TransferRequestDto transferRequestDto) {
        // Fetch sender and recipient accounts by account number
        Account sender = accountRepository.findByAccountNumber(transferRequestDto.getSenderAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Sender account not found"));
        Account recipient = accountRepository.findByAccountNumber(transferRequestDto.getRecipientAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Recipient account not found"));

        // Validate sender's balance
        if (sender.getBalance() < transferRequestDto.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance in sender's account");
        }

        // Perform the transfer
        sender.setBalance(sender.getBalance() - transferRequestDto.getAmount());
        recipient.setBalance(recipient.getBalance() + transferRequestDto.getAmount());

        // Save updated accounts
        accountRepository.save(sender);
        accountRepository.save(recipient);

        // Persist the transfer request
        TransferRequest transferRequest = TransferRequestMapper.toEntity(transferRequestDto);
        transferRequestRepository.save(transferRequest);
    }
}
