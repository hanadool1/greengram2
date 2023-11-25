package com.green.greengram2.feed.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FeedCommentSelDto {
    private int ifeed;
    private int startIdx;
    private int rowCount;
}