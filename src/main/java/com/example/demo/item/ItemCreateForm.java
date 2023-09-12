package com.example.demo.item;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemCreateForm {
    @NotEmpty(message = "종목명은 필수항목입니다.")
    private String Name;
    @NotNull
    private Integer StartPrice;
    @NotNull
    private Integer Howmany;
}
