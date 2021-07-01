package com.hisoft.news.service.impl;

import com.hisoft.news.dao.NewsUserDao;
import com.hisoft.news.dao.impl.NewsUserDaoImpl;
import com.hisoft.news.javabean.NewsUser;
import com.hisoft.news.service.NewsUserService;
import com.hisoft.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-18 17:11:09
 **/
public class NewsUserServiceImpl implements NewsUserService {
    private Connection conn;
    @Override
    public int saveUser(String uname, String upwd) {
        conn = JdbcUtil.getConnection();
        int count = 0;
        try {
            conn.setAutoCommit(false);
            NewsUserDao userDao = new NewsUserDaoImpl(conn);
            count = userDao.insert(uname,upwd);
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
    public NewsUser login(String uname, String upwd) {
        conn = JdbcUtil.getConnection();
        NewsUserDao userDao = new NewsUserDaoImpl(conn);
        NewsUser user = null;
        try {
           user  = userDao.findUser(uname, upwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeAll(null,null,conn);
        }
        return user;
    }
}
