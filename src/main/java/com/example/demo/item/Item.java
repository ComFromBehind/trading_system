package com.example.demo.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@Setter
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    @Column(unique = true)
    private String name;
    @Positive
    private Integer price;
    @Positive
    private Integer howmany;




}
