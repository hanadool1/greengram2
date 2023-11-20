package com.green.greengram2.user;

import com.green.greengram2.ResVo;
import com.green.greengram2.user.model.UserSignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public ResVo postUserSignup(UserSignupDto dto) {
        mapper.postUserSignup(dto);
        ResVo rv = new ResVo();
        rv.setResult(dto.getIuser());
        return rv;
    }
}
