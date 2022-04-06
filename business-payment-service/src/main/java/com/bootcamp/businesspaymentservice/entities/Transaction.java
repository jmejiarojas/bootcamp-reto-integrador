package com.bootcamp.businesspaymentservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("transactions")
public class Transaction {

    @Id
    private Integer id; // codigo de la transaccion

    @Column("channelCode")
    private String channelCode;

    @Column("serviceCode")
    private String serviceCode;

    @Column("supplyNumber")
    private String supplyNumber;

    private double amount;

    @Column("dateTime")
    private LocalDate dateTime;
}
