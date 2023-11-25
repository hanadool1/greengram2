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
    private int writerIuser; // 댓글작성자 pk
    private String writerNm; // 댓글작성자 이름
    private String writerPic; // 댓글장석자 프로필사진
    private String createdAt; // 댓글 작성 시간
    private int isFav; // 좋아요 : 1, 아님 : 0
    private List<String> pics;
    private List<FeedCommentSelVo> comments;
    private int isMoreComment; // 없음 : 0, 더 있음 : 1

}
