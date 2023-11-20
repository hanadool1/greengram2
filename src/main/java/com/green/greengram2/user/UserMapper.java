package com.green.greengram2.user;

import com.green.greengram2.user.model.UserSignupDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void postUserSignup(UserSignupDto dto);
}
