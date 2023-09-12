package com.example.demo.tradelog;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
public class TradeLogCreateForm {


    private String username;
    private String itemname;
    private Integer buy;
    private Integer sell;
    private Integer howmany;
    private Integer allsum;
}
