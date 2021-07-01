package com.hisoft.news.service.impl;

import com.hisoft.news.dao.NewsDao;
import com.hisoft.news.dao.TopicDao;
import com.hisoft.news.dao.impl.NewsDaoImpl;
import com.hisoft.news.dao.impl.TopicDaoImpl;
import com.hisoft.news.javabean.Topic;
import com.hisoft.news.service.TopicService;
import com.hisoft.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-18 16:02:57
 **/
public class TopicServiceImpl implements TopicService {
    private Connection conn;

    @Override
    public int deleteTopic(Integer tid) {
        //删除之前先判断该主题下是否有新闻
        conn = JdbcUtil.getConnection();
        int count = 0;
        try {
            conn.setAutoCommit(false);//开启事务
            NewsDao newsDao = new NewsDaoImpl(conn);
            if (newsDao.getNewsByTid(tid).isEmpty()) {
                //可以执行删除了
                TopicDao topicDao = new TopicDaoImpl(conn);
                count = topicDao.delete(tid);
            } else {//不能删除
                count = -1;
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();//回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            count = 0;
        } finally {
            JdbcUtil.closeAll(null, null, conn);
        }
        return count;
    }

    @Override
    public List<Topic> findAllTopics() {
        conn = JdbcUtil.getConnection();
        TopicDao topicDao = new TopicDaoImpl(conn);
        List<Topic> topicList = null;
        try {
            topicList = topicDao.getAllTopics();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(null, null, conn);
        }
        return topicList;
    }


    @Override
    public int saveTopic(String tname) {
        conn = JdbcUtil.getConnection();
        TopicDao topicDao = new TopicDaoImpl(conn);
        int count = 0;
        try {
            conn.setAutoCommit(false);
            if (topicDao.findTopicByTname(tname)) {//已存在
                count = -1;
            }else{
                count = topicDao.insert(tname);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JdbcUtil.closeAll(null, null, conn);
        }
        return count;
    }

    @Override
    public int modifyTopic(int tid, String tname) {
        conn = JdbcUtil.getConnection();
        TopicDao topicDao = new TopicDaoImpl(conn);
        int count = 0;
        try {
            conn.setAutoCommit(false);
            if (topicDao.findTopicByTname(tname)) {//已存在
                count = -1;
            }else{
                count = topicDao.update(tid,tname);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JdbcUtil.closeAll(null, null, conn);
        }
        return count;
    }
}
