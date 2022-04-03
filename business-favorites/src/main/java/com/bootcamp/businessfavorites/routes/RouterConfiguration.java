package com.bootcamp.businessfavorites.routes;

import com.bootcamp.businessfavorites.handlers.FavoriteHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouterConfiguration {

    @Bean
    public RouterFunction<ServerResponse> favoriteRoutes(FavoriteHandler favoriteHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/favorites"),
                RouterFunctions
                        .route(POST("").and(contentType(APPLICATION_JSON)), favoriteHandler::save)
        );
    }
}
