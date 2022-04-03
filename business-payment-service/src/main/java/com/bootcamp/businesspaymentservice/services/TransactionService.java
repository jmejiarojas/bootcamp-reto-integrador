package com.bootcamp.businesspaymentservice.services;

import com.bootcamp.businesspaymentservice.dtos.TransactionRequest;
import com.bootcamp.businesspaymentservice.entities.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionService {
    Mono<Transaction> save(TransactionRequest transactionRequest);
}
