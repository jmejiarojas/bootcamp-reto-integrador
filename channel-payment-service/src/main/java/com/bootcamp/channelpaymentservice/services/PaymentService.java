package com.bootcamp.channelpaymentservice.services;

import com.bootcamp.channelpaymentservice.dtos.requests.TransactionRequest;
import com.bootcamp.channelpaymentservice.dtos.responses.ServicioResponse;
import com.bootcamp.channelpaymentservice.dtos.responses.TransactionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentService {
    Flux<ServicioResponse> findByChannelCode(String channelCode);
    Mono<TransactionResponse> payService(TransactionRequest transactionRequest);
}
