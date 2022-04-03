package com.bootcamp.businesspaymentservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("services")
public class Servicio {
    @Id
    private Integer id;
    @Column("serviceCode")
    private String serviceCode;
    @Column("channelCode")
    private String channelCode;
    private String name;
}
