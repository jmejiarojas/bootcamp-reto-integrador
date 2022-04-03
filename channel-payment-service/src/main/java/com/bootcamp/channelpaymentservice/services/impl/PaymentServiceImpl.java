package com.bootcamp.channelpaymentservice.services.impl;

import com.bootcamp.channelpaymentservice.dtos.requests.TransactionRequest;
import com.bootcamp.channelpaymentservice.dtos.responses.ServicioResponse;
import com.bootcamp.channelpaymentservice.dtos.responses.TransactionResponse;
import com.bootcamp.channelpaymentservice.repositories.PaymentRepository;
import com.bootcamp.channelpaymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Flux<ServicioResponse> findByChannelCode(String channelCode) {
        return this.paymentRepository.findByChannelCode(channelCode);
    }

    @Override
    public Mono<TransactionResponse> payService(TransactionRequest transactionRequest) {
        return this.paymentRepository.payService(transactionRequest);
    }
}
