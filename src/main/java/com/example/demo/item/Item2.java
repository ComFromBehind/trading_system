package com.example.demo.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.List;


@Entity
    @Setter
    @Getter
    public class Item2 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(unique = true)
        private String itemname;
        private Integer sell;
        private Integer buy;
        @Positive
        private Integer howmany;
        private String username;
        @Positive
        private Integer allsum;




}
