package com.bootcamp.channelpaymentservice.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FavoriteResponse {
    private String id;
    private String nombre;
    private String tipo;
    private String codigoServicio;
}
