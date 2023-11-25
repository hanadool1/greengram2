package com.green.greengram2.feed.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedCommentInsProcDto {
    private int iuser;
    private int ifeed;
    private String comment;
    private int ifeedComment;
}
