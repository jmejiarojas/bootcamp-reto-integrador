package com.bootcamp.channelpaymentservice.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TransactionResponse {

    private Integer id; // codigo de la transaccion
    private String serviceCode;
    private String supplyNumber;
    private double amount;
    private LocalDate dateTime;
}
