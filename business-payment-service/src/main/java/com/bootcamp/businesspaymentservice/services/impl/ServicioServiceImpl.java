package com.bootcamp.businesspaymentservice.services.impl;

import com.bootcamp.businesspaymentservice.entities.Servicio;
import com.bootcamp.businesspaymentservice.repositories.ServicioRepository;
import com.bootcamp.businesspaymentservice.services.ServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    @Override
    public Flux<Servicio> findChannelCode(String channelCode) {
        return this.servicioRepository.findByChannelCode(channelCode);
    }
}
