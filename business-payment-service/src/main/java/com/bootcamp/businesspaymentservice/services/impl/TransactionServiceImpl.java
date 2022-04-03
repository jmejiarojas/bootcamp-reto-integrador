package com.bootcamp.businesspaymentservice.services.impl;

import com.bootcamp.businesspaymentservice.dtos.TransactionRequest;
import com.bootcamp.businesspaymentservice.entities.Transaction;
import com.bootcamp.businesspaymentservice.repositories.TransactionRepository;
import com.bootcamp.businesspaymentservice.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Mono<Transaction> save(TransactionRequest transactionRequest) {
        return this.transactionRepository.save(this.transactionRequestToTransaction(transactionRequest));
    }

    private Transaction transactionRequestToTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setServiceCode(transactionRequest.getServiceCode());
        transaction.setSupplyNumber(transactionRequest.getSupplyNumber());
        transaction.setDateTime(LocalDate.now());
        return transaction;
    }
}
