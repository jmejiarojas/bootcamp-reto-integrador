package com.bootcamp.channelpaymentservice.routers;

import com.bootcamp.channelpaymentservice.handlers.PaymentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfiguration {

    @Bean
    public RouterFunction<ServerResponse> servicesRoutes(PaymentHandler paymentHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/servicios"),
                RouterFunctions
                        .route(GET("/channel-code/{channelCode}"), paymentHandler::findByChannelCode)

        );
    }

    @Bean
    public RouterFunction<ServerResponse> paymentRoutes(PaymentHandler paymentHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/transactions"),
                RouterFunctions
                        .route(POST("").and(contentType(MediaType.APPLICATION_JSON)), paymentHandler::payService)

        );
    }

}
