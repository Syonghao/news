package com.hisoft.news.dao;

import com.hisoft.news.javabean.Comments;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-19 09:50:28
 **/
public interface CommentsDao {
    /**
     * 添加评论信息
     * @param comments
     * @return
     */
     int saveComments(Comments comments) throws SQLException;
    /**
     * 查询某条新闻下的所有评论
     * @param nid
     * @return
     */
    List<Comments> getCommentsByNid(int nid) throws SQLException;

    /**
     * 删除指定新闻下的所有评论
     * @param nid
     */
    void delByNid(int nid) throws SQLException;

    /**
     * 添加评论
     * @param comments
     */
    public void insertcomment(Comments comments) throws SQLException;

}
