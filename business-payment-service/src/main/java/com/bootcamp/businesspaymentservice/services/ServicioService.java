package com.bootcamp.businesspaymentservice.services;

import com.bootcamp.businesspaymentservice.entities.Servicio;
import reactor.core.publisher.Flux;

public interface ServicioService {
    Flux<Servicio> findChannelCode(String channelCode);
}
