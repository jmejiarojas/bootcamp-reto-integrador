package com.bootcamp.businesspaymentservice.routes;

import com.bootcamp.businesspaymentservice.handlers.ServicioHandler;
import com.bootcamp.businesspaymentservice.handlers.TransactionHanlder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfiguration {

    @Bean
    public RouterFunction<ServerResponse> servicioRoutes(ServicioHandler servicioHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/servicios"),
                RouterFunctions
                        .route(GET("/channelCode/{channelCode}"), servicioHandler::findByChannelCode)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> transactionRoutes(TransactionHanlder transactionHanlder) {
        return RouterFunctions.nest(RequestPredicates.path("/transactions"),
                RouterFunctions
                        .route(POST(""), transactionHanlder::save)
        );
    }
}
