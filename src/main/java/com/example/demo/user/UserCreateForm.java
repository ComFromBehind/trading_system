package com.example.demo.user;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter@Setter
public class UserCreateForm {
    @NotEmpty(message="id는 필수항목입니다.")
    private String LoginID;

    private Integer Budget;
    @NotEmpty(message="비밀번호는 필수항목입니다.")
    private String Password;

}
