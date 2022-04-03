package com.bootcamp.channelpaymentservice.util;

import com.bootcamp.channelpaymentservice.dtos.responses.ServicioResponse;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicioCache implements Serializable {
    private List<ServicioResponse> servicioResponseList;
}
