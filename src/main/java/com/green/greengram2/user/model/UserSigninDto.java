package com.green.greengram2.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSigninDto { // 요청
    private String uid;
    private String upw;
}
