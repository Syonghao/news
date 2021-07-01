package com.hisoft.news.dao;

import com.hisoft.news.javabean.Topic;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-17 10:19:51
 **/
public interface TopicDao {
    /**
     * 查询所有主题
     * @return
     */
    public List<Topic> getAllTopics() throws SQLException;

    /**
     * 查询某主题是否已存在
     * @return
     */
    public boolean findTopicByTname(String tname) throws SQLException;

    /**
     * 添加主题
     * @param tname
     * @return
     */
    public int insert(String tname) throws SQLException;

    /**
     * 修改主题
     * @param tid
     * @param tname
     * @return
     */
    public int update(int tid,String tname) throws SQLException;

    /**
     * 删除主题
     * @param tid
     * @return
     */
    public int delete(int tid) throws SQLException;
}
