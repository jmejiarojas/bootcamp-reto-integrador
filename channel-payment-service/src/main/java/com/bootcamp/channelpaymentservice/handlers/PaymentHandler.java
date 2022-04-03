package com.bootcamp.channelpaymentservice.handlers;

import com.bootcamp.channelpaymentservice.core.exceptions.PaymentBaseException;
import com.bootcamp.channelpaymentservice.dtos.requests.TransactionRequest;
import com.bootcamp.channelpaymentservice.dtos.responses.ServicioResponse;
import com.bootcamp.channelpaymentservice.dtos.responses.TransactionResponse;
import com.bootcamp.channelpaymentservice.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PaymentHandler {

    private final PaymentService paymentService;

    public Mono<ServerResponse> findByChannelCode(ServerRequest serverRequest) {

        String channelCode = serverRequest.pathVariable("channelCode");
        return this.paymentService.findByChannelCode(channelCode)
                .switchIfEmpty(Mono.error(new PaymentBaseException("No se encontro elementos")))
                .collectList()
                .flatMap(list -> ServerResponse.ok().body(Mono.just(list), ServicioResponse.class));
    }

    public Mono<ServerResponse> payService(ServerRequest serverRequest) {
        var transactionRequest = serverRequest.bodyToMono(TransactionRequest.class);

        return transactionRequest.flatMap(this.paymentService::payService)
                .flatMap(transactionResponse -> ServerResponse.ok().body(Mono.just(transactionResponse), TransactionResponse.class));
    }
}
