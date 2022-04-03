package com.bootcamp.businesspaymentservice.repositories;

import com.bootcamp.businesspaymentservice.entities.Servicio;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ServicioRepository extends ReactiveCrudRepository<Servicio, Integer> {
    Flux<Servicio> findByChannelCode(String channelCode);
}
