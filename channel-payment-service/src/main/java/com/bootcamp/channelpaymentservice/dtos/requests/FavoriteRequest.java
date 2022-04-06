package com.bootcamp.channelpaymentservice.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FavoriteRequest {
    private String codigoServicio;
    private String nombre;
    private String tipo;
}
