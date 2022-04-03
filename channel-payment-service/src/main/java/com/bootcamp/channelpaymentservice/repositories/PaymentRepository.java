package com.bootcamp.channelpaymentservice.repositories;

import com.bootcamp.channelpaymentservice.dtos.requests.TransactionRequest;
import com.bootcamp.channelpaymentservice.dtos.responses.TransactionResponse;
import com.bootcamp.channelpaymentservice.dtos.responses.ServicioResponse;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface PaymentRepository {

    Flux<ServicioResponse> findByChannelCode(String channelCode);
    Mono<TransactionResponse> payService(TransactionRequest paymentRequest);

}
