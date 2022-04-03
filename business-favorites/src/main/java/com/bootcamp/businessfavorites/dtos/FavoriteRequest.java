package com.bootcamp.businessfavorites.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class FavoriteRequest {
    private String nombre;
    private String tipo;
    private String codigoServicio;
}
