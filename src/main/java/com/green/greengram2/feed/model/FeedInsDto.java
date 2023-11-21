package com.green.greengram2.feed.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@ToString
public class FeedInsDto { // 게시글 올릴 때, requestbody 받는 것
    private int iuser;
    private String contents;
    private String location;
    private List<String> pics; // 사진 여러장
}
