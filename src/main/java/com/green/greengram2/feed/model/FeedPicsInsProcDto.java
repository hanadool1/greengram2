package com.green.greengram2.feed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class FeedPicsInsProcDto { // t_feed_pics에 insert할 때 필요한 것
    private int ifeed;
    private List<String> pics;
}
