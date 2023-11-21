package com.green.greengram2.feed.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FeedInsProcDto { // requestbody로 받은 dto를 t_feed에 insert할 때 필요한 것
    private int ifeed;
    private int iuser;
    private String contents;
    private String location;
}
