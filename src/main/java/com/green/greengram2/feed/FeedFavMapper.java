package com.green.greengram2.feed;

import com.green.greengram2.feed.model.FeedDelDto;
import com.green.greengram2.feed.model.FeedFavDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedFavMapper {
    int delFeedFav(FeedFavDto dto); // 좋아요 삭제
    int insFeedFav(FeedFavDto dto);
    int delFav(FeedDelDto dto); // 피드 삭제전 좋아요 삭제
}
