package com.green.greengram2.feed.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FeedSelDto { // Vo는 데이터베이스에서 가져와서 응답
    private int loginedIuser;
    private int targetIuser;

    private int startIdx;
    private int rowCount;

}
