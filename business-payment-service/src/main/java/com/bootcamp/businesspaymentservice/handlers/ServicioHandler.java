package com.bootcamp.businesspaymentservice.handlers;

import com.bootcamp.businesspaymentservice.entities.Servicio;
import com.bootcamp.businesspaymentservice.services.ServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class ServicioHandler {

    private final ServicioService servicioService;

    public Mono<ServerResponse> findByChannelCode(ServerRequest serverRequest) {
        var channelCode = serverRequest.pathVariable("channelCode");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.servicioService.findChannelCode(channelCode), Servicio.class);
    }

}
