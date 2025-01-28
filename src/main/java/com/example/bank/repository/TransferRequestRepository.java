package com.example.bank.repository;


import com.example.bank.entity.TransferRequest;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TransferRequestRepository extends JpaRepository<TransferRequest, Long> {

}