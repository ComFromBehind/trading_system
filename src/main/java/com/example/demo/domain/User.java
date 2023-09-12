package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name="sexy")
public class User {
    @Id
    private String loginID;
    private String password;
    private Integer budget;
}
