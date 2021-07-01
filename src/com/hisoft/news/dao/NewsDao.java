package com.hisoft.news.dao;

import com.hisoft.news.javabean.News;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-17 17:32:06
 **/
public interface NewsDao {
    /**
     * 根据主题id查询新闻列表
     * @param tid
     * @return
     */
    public List<News> getNewsByTid(Integer tid) throws SQLException;

    /**
     * 根据主题名查询新闻列表
     * @param tname
     * @return
     */
    public List<News> getNewsByTname(String tname) throws SQLException;

    /**
     * 查询所有新闻
     * @return
     */
    public List<News> getPageNews(int pageIndex,int pageSize ) throws SQLException;

    /**
     * 查询某新闻详情
     * @param nid
     * @return
     */
    News getNewsById(int nid);

    /**
     * 删除指定新闻
     * @param nid
     */
    void delete(int nid) throws SQLException;

    /**
     * 查询所有新闻的条数
     * @return
     */
    int getNewsCount() throws SQLException;

    /**
     * 添加新闻
     * @param news
     * @throws SQLException
     */
    public void insert(News news) throws SQLException;



}
