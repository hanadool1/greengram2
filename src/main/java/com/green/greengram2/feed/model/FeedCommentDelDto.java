package com.green.greengram2.feed.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter

@Builder
public class FeedCommentDelDto {
    private int ifeedComment;
    private int loginedIuser;
}
