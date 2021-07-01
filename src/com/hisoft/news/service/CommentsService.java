package com.hisoft.news.service;

import com.hisoft.news.javabean.Comments;

/**
 * @program: news
 * @description:
 * @author: syh
 * @create: 2021-06-29 17:27:01
 **/
public interface CommentsService {
    /**
     * 发布评论
     * @param comments
     * @return
     */
     int publlishComments(Comments comments);
}