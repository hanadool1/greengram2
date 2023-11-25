package com.green.greengram2.feed;

import com.green.greengram2.ResVo;
import com.green.greengram2.feed.model.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;
    private final FeedPicsMapper picsMapper;
    private final FeedFavMapper favMapper;
    private final FeedCommentMapper commMapper;

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
            List<FeedCommentSelVo> comments = commMapper.selCommentAll(FeedCommentSelDto.builder()
                            .ifeed(vo.getIfeed())
                            .startIdx(0)
                            .rowCount(4)
                            .build());
            if (comments.size() == 4) {
                vo.setIsMoreComment(1);
                comments.remove(comments.size()-1);
            }
            vo.setComments(comments);
        }
        return list;
    }

    public ResVo procFav(FeedFavDto dto) {
        int result = favMapper.delFeedFav(dto);
        if (result == 0) {
            ResVo rv = new ResVo(favMapper.insFeedFav(dto));
            return rv;
        }
        return new ResVo(0);
    }

    public ResVo insComment(FeedCommentInsDto dto) {
        FeedCommentInsProcDto pDto = FeedCommentInsProcDto.builder()
                .iuser(dto.getIuser())
                .ifeed(dto.getIfeed())
                .comment(dto.getComment())
                .build();
        try {
            int result = commMapper.insComment(pDto);
            return new ResVo(pDto.getIfeedComment());
        } catch (Exception e) {
            return new ResVo(0);
        }
    }

    //-------------------- FeedComment
//    public ResVo postComment(FeedCommentInsDto dto) {
//        int affectedRows = commMapper.insComment(dto);
//        return new ResVo(affectedRows);
//    }

    public List<FeedCommentSelVo> getCommentAll(int ifeed) {
        return commMapper.selCommentAll(FeedCommentSelDto.builder()
                .ifeed(ifeed)
                .startIdx(4)
                .rowCount(9999)
                .build());
    }

    public ResVo delComment(int ifeedComment, int loginedIuser){
        return new ResVo(commMapper.delComment(FeedCommentDelDto.builder()
                .ifeedComment(ifeedComment)
                .loginedIuser(loginedIuser)
                .build()));
    }

    public ResVo delFeed(FeedDelDto dto) {
        int result = mapper.selFeed(dto);
        if (result == 0) {
            return new ResVo(0);
        }
        commMapper.delFeedComment(dto);
        favMapper.delFav(dto);
        picsMapper.delFeedPics(dto);
        mapper.delFeed(dto);
        return new ResVo(1);

    }
}
