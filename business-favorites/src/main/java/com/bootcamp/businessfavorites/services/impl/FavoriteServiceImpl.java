package com.bootcamp.businessfavorites.services.impl;

import com.bootcamp.businessfavorites.dtos.FavoriteRequest;
import com.bootcamp.businessfavorites.entities.Favorite;
import com.bootcamp.businessfavorites.repositories.FavoriteRepository;
import com.bootcamp.businessfavorites.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Override
    public Mono<Favorite> save(FavoriteRequest request) {
        return this.favoriteRepository.save(this.favoriteRequestToFavoriteDto(request));
    }

    private Favorite favoriteRequestToFavoriteDto(FavoriteRequest request) {
        Favorite fav = new Favorite();
        fav.setNombre(request.getNombre());
        fav.setCodigoServicio(request.getCodigoServicio());
        fav.setTipo(request.getTipo());
        return fav;
    }
}
