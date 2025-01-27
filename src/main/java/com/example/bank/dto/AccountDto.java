package com.example.bank.dto;


public class AccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;
    private String accountNumber;

    public AccountDto() {
    }

    public AccountDto(Long id, String accountHolderName, double balance, String accountNumber) {
        this.id = id;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountNumber = accountNumber;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}


