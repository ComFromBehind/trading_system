package com.example.demo.tradelog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Setter @Getter
public class TradeLog2 {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty
    private String product;
    @JsonProperty
    private String buyer;
    @JsonProperty
    private String seller;
    @JsonProperty
    private Integer price;
    private Integer checked;



}
