package com.green.greengram2.feed;


import com.green.greengram2.ResVo;
import com.green.greengram2.feed.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
@Tag(name = "피드 API", description = "피드 관련 처리")
public class FeedController {
    private final FeedService service;

    @PostMapping
    @Operation(summary = "피드 작성", description = "피드 작성 처리")
    @Parameters(value = {
            @Parameter(name = "iuser", description = "유저pk"),
            @Parameter(name = "contents", description = "글 내용"),
            @Parameter(name = "location", description = "장소"),
            @Parameter(name = "pics", description = "사진들")
    })
    public ResVo insFeed(@RequestBody FeedInsDto dto) {
        return service.postFeed(dto);
    }

    @Operation(summary = "피드 리스트", description = "전체 피드 리스트, 특정 사용자 프로필 화면에서 사용할 피드 리스트, 한 페이지 30개 피드 가져옴")
    @Parameters(value = {
            @Parameter(name="page", description = "page값")
            , @Parameter(name="loginedIuser", description = "로그인 유저 pk")
            , @Parameter(name="targetIuser", description = "(생략가능) 특정 사용자 프로필 화면의 주인 유저 pk")
    })
    @GetMapping
    public List<FeedSelVo> getFeedAll(int page, int loginedIuser,
                                      @RequestParam(defaultValue = "0", required = false) int targetIuser) {
        log.info("targetIuser : {}", targetIuser);
        final int ROW_COUNT = 30;

        FeedSelDto dto = FeedSelDto.builder()
                .loginedIuser(loginedIuser)
                .targetIuser(targetIuser)
                .startIdx((page - 1) * ROW_COUNT)
                .rowCount(ROW_COUNT)
                .build();
        return service.getFeedAll(dto);
    }

    @Operation(summary = "좋아요 처리", description = "Toggle로 처리함")
    @Parameters(value = {
            @Parameter(name = "ifeed", description = "feed pk"),
            @Parameter(name = "iuser", description = "로그인한 유저 pk")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "좋아요 처리 : result(1), 좋아요 취소 : result(2)")
    })
    @GetMapping("/fav")
    public ResVo toggleFav(FeedFavDto dto) {
        log.info("dto : {}",dto);
        return service.procFav(dto);
    }

    @PostMapping("/comment")
    public ResVo insFeedComment(@RequestBody FeedCommentInsDto dto) {
        log.info("dto : {}",dto);
        return service.insComment(dto);
    }

    @GetMapping("/comment")
    public List<FeedCommentSelVo> getComment(int ifeed) {
        return service.getCommentAll(ifeed);
    }

    @DeleteMapping("/comment")
    public ResVo delComment(@RequestParam("ifeed_comment") int ifeedComment, @RequestParam("logined_iuser") int loginedIuser) {
        log.info("ifeedComment: {}, loginedIuser: {}", ifeedComment,loginedIuser);
        return service.delComment(ifeedComment,loginedIuser);
    }

    @DeleteMapping
    public ResVo delFeed(FeedDelDto dto) {
        log.info("dtd : {}",dto);
        return service.delFeed(dto);
    }



}

