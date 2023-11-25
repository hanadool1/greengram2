package com.green.greengram2.feed;

import com.green.greengram2.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    int insComment(FeedCommentInsProcDto dto);
    List<FeedCommentSelVo> selCommentAll(FeedCommentSelDto dto);
    int delComment(FeedCommentDelDto dto);
    int delFeedComment(FeedDelDto dto);
}
