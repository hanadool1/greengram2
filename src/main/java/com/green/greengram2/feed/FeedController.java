package com.green.greengram2.feed;


import com.green.greengram2.ResVo;
import com.green.greengram2.feed.model.FeedInsDto;
import com.green.greengram2.feed.model.FeedSelDto;
import com.green.greengram2.feed.model.FeedSelVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
}

