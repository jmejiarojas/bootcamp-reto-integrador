package com.bootcamp.channelpaymentservice.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TransactionRequest {
    private String serviceCode;
    private String supplyNumber;
    private double amount;
}
