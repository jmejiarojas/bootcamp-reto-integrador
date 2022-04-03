package com.bootcamp.businesspaymentservice.handlers;

import com.bootcamp.businesspaymentservice.dtos.TransactionRequest;
import com.bootcamp.businesspaymentservice.entities.Servicio;
import com.bootcamp.businesspaymentservice.entities.Transaction;
import com.bootcamp.businesspaymentservice.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TransactionHanlder {

    private final TransactionService transactionService;

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        var transactionRequest = serverRequest.bodyToMono(TransactionRequest.class);

        return transactionRequest
                .flatMap(this.transactionService::save)
                .flatMap(transaction -> ServerResponse.ok().body(Mono.just(transaction), Transaction.class));
    }
}
