package com.bootcamp.businessfavorites.repositories;

import com.bootcamp.businessfavorites.entities.Favorite;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends ReactiveMongoRepository<Favorite, String> {

}
