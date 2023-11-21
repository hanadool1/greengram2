package com.green.greengram2.feed.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeedSelVo {
    private int ifeed;
    private String contents;
    private String location;
    private int writerIuser;
    private String writerNm;
    private String writerPic;
    private String createdAt;
    private int isFav; // 좋아요 : 1, 아님 : 0
    private List<String> pics;

}
