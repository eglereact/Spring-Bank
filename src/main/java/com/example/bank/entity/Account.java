package com.example.bank.entity;

import com.example.bank.utils.AccountNumberGenerator;
import jakarta.persistence.*;


@Table(name = "accounts")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_holder_name")
    private String accountHolderName;
    private double balance;
    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    public Account() {
    }

    public Account(Long id, String accountHolderName, double balance, String accountNumber) {
        this.id = id;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountNumber = accountNumber != null ? accountNumber : AccountNumberGenerator.generateAccountNumber();
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
}
