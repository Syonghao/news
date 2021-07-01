package com.hisoft.news.service.impl;

import com.hisoft.news.dao.CommentsDao;
import com.hisoft.news.dao.NewsDao;
import com.hisoft.news.dao.impl.CommentsDaoImpl;
import com.hisoft.news.dao.impl.NewsDaoImpl;
import com.hisoft.news.javabean.Comments;
import com.hisoft.news.javabean.News;
import com.hisoft.news.service.NewsService;
import com.hisoft.news.util.JdbcUtil;
import com.hisoft.news.util.page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-18 17:02:33
 **/
public class NewsServiceImpl implements NewsService {
    private Connection conn;
    @Override
    public List<News> findNewsByTid(Integer tid) {
        conn = JdbcUtil.getConnection();
        NewsDao newsDao = new NewsDaoImpl(conn);
        List<News> newsList = null;
        try {
            newsList = newsDao.getNewsByTid(tid);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeAll(null,null,conn);
        }
        return newsList;
    }

    @Override
    public int delNews(int nid) {
        int count = 0;
        conn = JdbcUtil.getConnection();
        NewsDao newsDao = new NewsDaoImpl(conn);
        CommentsDao commentsDao = new CommentsDaoImpl(conn);
        try {
            conn.setAutoCommit(false);
            commentsDao.delByNid(nid);
//            int a = 1/0;
            newsDao.delete(nid);
            count = 1;
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            JdbcUtil.closeAll(null,null,conn);
        }
        return count;
    }

    @Override
    public boolean addNews(News news) {
        conn = JdbcUtil.getConnection();
        NewsDao newsDao = new NewsDaoImpl(conn);
        boolean result = false;
        news.setNcreateDate(new Date());
        try {
            conn.setAutoCommit(false);
            newsDao.insert(news);
            result = true;
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            JdbcUtil.closeAll(null,null,conn);
        }
        return result;
    }

    @Override
    public page queryPageNews(int currentPageNo) {
        conn = JdbcUtil.getConnection();
        NewsDao newsDao = new NewsDaoImpl(conn);
        List<News> newsList = null;
        page page = new page();
        page.setCurrPageNo(currentPageNo);//更新页号
        try {
            page.setTotalCount(newsDao.getNewsCount());//设置总记录数和总页数
            newsList = newsDao.getPageNews(currentPageNo,page.getPageSize());
            page.setNewsList(newsList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(null,null,conn);
        }
        return page;
    }

    @Override
    public News readNews(int nid) {
        conn = JdbcUtil.getConnection();
        NewsDao newsDao = new NewsDaoImpl(conn);
        CommentsDao commentsDao = new CommentsDaoImpl(conn);
        News news = null;
        try {
            news = newsDao.getNewsById(nid);
            List<Comments> commentsList = commentsDao.getCommentsByNid(nid);
            news.setCommentsList(commentsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeAll(null,null,conn);
        }
        return news;
    }

    @Override
    public List<News> findNewsByTname(String tname) {
        conn = JdbcUtil.getConnection();
        NewsDao newsDao = new NewsDaoImpl(conn);
        List<News> newsList = null;
        try {
            newsList = newsDao.getNewsByTname(tname);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeAll(null,null,conn);
        }
        return newsList;
    }
}
