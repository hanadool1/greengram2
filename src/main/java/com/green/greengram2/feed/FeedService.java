package com.green.greengram2.feed;

import com.green.greengram2.ResVo;
import com.green.greengram2.feed.model.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;
    private final FeedPicsMapper picsMapper;

    public ResVo postFeed(FeedInsDto dto) {
        FeedInsProcDto pDto = FeedInsProcDto.builder()
                .iuser(dto.getIuser())
                .contents(dto.getContents())
                .location(dto.getLocation())
                .build();
        int result = mapper.insFeed(pDto);
        if (result == 0) {return new ResVo(0);}
        FeedPicsInsProcDto p2Dto = new FeedPicsInsProcDto(pDto.getIfeed(),dto.getPics());
        mapper.insFeedPics(p2Dto);
        return new ResVo(p2Dto.getIfeed()); // ResVo에 ifeed값을 넣는다
    }

    public List<FeedSelVo> getFeedAll(FeedSelDto dto) {
        List<FeedSelVo> list = mapper.selFeedAll(dto);
        for (FeedSelVo vo : list) {
            vo.setPics(picsMapper.selFeedPicsAll(vo.getIfeed()));
        }
        return list;
    }



}
