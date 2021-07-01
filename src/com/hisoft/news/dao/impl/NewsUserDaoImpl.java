package com.hisoft.news.dao.impl;

import com.hisoft.news.dao.NewsUserDao;
import com.hisoft.news.javabean.NewsUser;
import com.hisoft.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-16 09:15:00
 **/
public class NewsUserDaoImpl implements NewsUserDao {
    private Connection conn;

    public NewsUserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int insert(String uname, String upwd) throws SQLException {
        String sql = "insert into news_users(uname,upwd) values(?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, uname);
        pstmt.setString(2, upwd);
        int count = pstmt.executeUpdate();
        JdbcUtil.closeAll(null,pstmt,null);
        return count;

    }

    @Override
    public NewsUser findUser(String uname, String upwd) {
//        Connection conn = JdbcUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        NewsUser user = null;
        try {
            pstmt = conn.prepareStatement("select uid from news_users where uname = ? and upwd = ?");
            pstmt.setString(1, uname);
            pstmt.setString(2, upwd);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int uid = rs.getInt("uid");
//               String uname2 = rs.getString("uname");
//               String upwd2 = rs.getString("upwd");
                user = new NewsUser(uid, uname, upwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(rs, pstmt, null);
        }
        return user;
    }
}
