package com.green.greengram2.feed;


import com.green.greengram2.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    int insFeed(FeedInsProcDto dto);
    int insFeedPics(FeedPicsInsProcDto dto);
    List<FeedSelVo> selFeedAll(FeedSelDto dto);
    int selFeed(FeedDelDto dto);
    int delFeed(FeedDelDto dto);
}
