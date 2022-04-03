package com.bootcamp.businessfavorites.handlers;

import com.bootcamp.businessfavorites.dtos.FavoriteRequest;
import com.bootcamp.businessfavorites.entities.Favorite;
import com.bootcamp.businessfavorites.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class FavoriteHandler {

    private final FavoriteService favoriteService;

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        var favoriteInput = serverRequest.bodyToMono(FavoriteRequest.class);

        return favoriteInput
                .flatMap(this.favoriteService::save)
                .flatMap(favorite -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(favorite), Favorite.class));
    }

}
