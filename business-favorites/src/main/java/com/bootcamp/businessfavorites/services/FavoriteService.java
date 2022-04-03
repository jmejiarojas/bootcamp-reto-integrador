package com.bootcamp.businessfavorites.services;


import com.bootcamp.businessfavorites.dtos.FavoriteRequest;
import com.bootcamp.businessfavorites.entities.Favorite;
import reactor.core.publisher.Mono;

public interface FavoriteService {
    Mono<Favorite> save(FavoriteRequest request);
}
