package com.green.greengram2.user.model;

import lombok.Data;

@Data
public class UserSignupDto {
    private int iuser;
    private String uid;
    private String upw;
    private String nm;
    private String pic;
}
