package com.bootcamp.businesspaymentservice.repositories;

import com.bootcamp.businesspaymentservice.entities.Transaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Integer> {
}
