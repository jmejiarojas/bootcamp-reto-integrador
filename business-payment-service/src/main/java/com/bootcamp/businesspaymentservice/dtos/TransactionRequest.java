package com.bootcamp.businesspaymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionRequest {
    private String channelCode;
    private String serviceCode;
    private String supplyNumber;
    private double amount;
}
