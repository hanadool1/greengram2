package com.green.greengram2.user;

import com.green.greengram2.ResVo;
import com.green.greengram2.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "유저 API", description = "인증 관련")
public class UserController {
    private final UserService service;

    @Operation(summary = "인증", description = "아이디/비번을 활용한 인증처리")
    @Parameters(value = {
            @Parameter(name = "uid", description = "아이디"),
            @Parameter(name = "upw", description = "비밀번호")
    })
    @PostMapping("/signin")
    public UserSigninVo postUserSignin(@RequestBody UserSigninDto dto) {
        log.info("signin - dto: {}", dto);
        return service.userSignin(dto);
    }


    @Operation(summary = "회원가입", description = "회원가입 처리")
    @PostMapping("/signup")
    @Parameters(value = {
            @Parameter(name = "uid", description = "아이디"),
            @Parameter(name = "upw", description = "비밀번호"),
            @Parameter(name = "nm", description = "이름"),
            @Parameter(name = "pic", description = "프로필 사진")
    })
    public ResVo postUserSignup(@RequestBody UserSignupDto dto) {
        log.info("signup - dto: {}", dto);
        return service.userSignup(dto); //ResVo객체에 insert한 레코드 pk값을 담아서 응답처리
    }

    @GetMapping
    public UserInfoVo UserInfoVo(@RequestParam(value = "target_iuser") int targetIuser) {
        log.info("targetIuser : {}",targetIuser);
        return service.selUserInfo(targetIuser);
    }

    @PatchMapping("/pic")
    public ResVo patchUserPic(@RequestBody UserPatchPicDto dto) {
        return service.updUserpic(dto);
    }

}