package com.example.bank.dto;

import java.time.LocalDateTime;

public class TransferRequestDto {
    private Long id;
    private String senderAccountNumber;
    private String recipientAccountNumber;
    private Double amount;
    private LocalDateTime timestamp;

    public TransferRequestDto() {
    }

    public TransferRequestDto(String senderAccountNumber, String recipientAccountNumber, Double amount, LocalDateTime timestamp) {
        this.senderAccountNumber = senderAccountNumber;
        this.recipientAccountNumber = recipientAccountNumber;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public String getRecipientAccountNumber() {
        return recipientAccountNumber;
    }

    public void setRecipientAccountNumber(String recipientAccountNumber) {
        this.recipientAccountNumber = recipientAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}