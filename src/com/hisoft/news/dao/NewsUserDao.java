package com.hisoft.news.dao;

import com.hisoft.news.javabean.NewsUser;

import java.sql.SQLException;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-16 09:13:10
 **/
public interface NewsUserDao {
    /**
     * 添加用户
     * @return
     */
    public int insert(String uname,String upwd) throws SQLException;

    /**
     * 根据用户名和密码查找用户
     * @param uname
     * @param upwd
     * @return
     */
    public NewsUser findUser(String uname, String upwd)  throws SQLException;
}
