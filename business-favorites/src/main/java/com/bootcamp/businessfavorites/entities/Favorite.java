package com.bootcamp.businessfavorites.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(value="favorites")
public class Favorite {

    @Id
    private String id;
    private String nombre;
    private String tipo;
    private String codigoServicio;
}
