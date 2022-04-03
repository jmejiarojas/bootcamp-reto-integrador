package com.bootcamp.channelpaymentservice.dtos.responses;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicioResponse implements Serializable {

    private Integer id;
    private String serviceCode;
    private String channelCode;
    private String name;

}
