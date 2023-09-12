package com.example.demo.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    @Column(unique=true)
    private String loginID;
    private Integer budget;
    private String password;

}
