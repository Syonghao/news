package com.hisoft.news.service;

import com.hisoft.news.javabean.News;
import com.hisoft.news.util.page;

import java.util.List;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-18 17:01:10
 **/
public interface NewsService {
    /**
     * 根据主题id查询新闻列表
     * @param tid
     * @return
     */
    public List<News> findNewsByTid(Integer tid);

    /**
     * 根据主题名查询新闻列表
     * @param tname
     * @return
     */
    public List<News> findNewsByTname(String tname);

    /**
     * 读取某条新闻详细信息
     * @param nid
     * @return
     */
    News readNews(int nid);

    /**
     * 分页查询所有新闻
     * @return
     */
    page queryPageNews(int currentPageNo);

    /**
     * 删除指定新闻
     * @param nid
     * @return
     */
    int delNews(int nid);

    /**
     * 添加新闻
     * @param news
     * @return
     */
    public boolean addNews(News news);

}
