package com.example.demo.tradelog;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity@Getter@Setter
public class TradeLog {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "이름을 입력해주세요")
    private String username;
    @NotEmpty(message="종목명을 입력해주세요")
    private String itemname;
    private Integer buy;
    private Integer sell;
    @NotNull(message = "개수를 입력해주세요")
    @Positive
    private Integer howmany;
    @NotNull(message = "가격을 입력해주세요")
    @Positive
    private Integer allsum;
    private Integer checked;
}
