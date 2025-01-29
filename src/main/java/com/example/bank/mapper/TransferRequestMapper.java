package com.example.bank.mapper;

import com.example.bank.dto.TransferRequestDto;
import com.example.bank.entity.TransferRequest;

public class TransferRequestMapper {

    // Map DTO to Entity
    public static TransferRequest toEntity(TransferRequestDto dto) {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setSenderAccountNumber(dto.getSenderAccountNumber());
        transferRequest.setRecipientAccountNumber(dto.getRecipientAccountNumber());
        transferRequest.setAmount(dto.getAmount());
        transferRequest.setId(dto.getId());
        transferRequest.setTimestamp(dto.getTimestamp());
        return transferRequest;
    }

    // Map Entity to DTO (if needed)
    public static TransferRequestDto toDto(TransferRequest transferRequest) {
        TransferRequestDto dto = new TransferRequestDto();
        dto.setSenderAccountNumber(transferRequest.getSenderAccountNumber());
        dto.setRecipientAccountNumber(transferRequest.getRecipientAccountNumber());
        dto.setAmount(transferRequest.getAmount());
        dto.setId(transferRequest.getId());
        dto.setTimestamp(transferRequest.getTimestamp());
        return dto;
    }

}
