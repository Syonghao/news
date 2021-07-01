package com.hisoft.news.dao.impl;

import com.hisoft.news.dao.TopicDao;
import com.hisoft.news.javabean.Topic;
import com.hisoft.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-17 10:20:49
 **/
public class TopicDaoImpl implements TopicDao {

    private Connection conn;

    public TopicDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Topic> getAllTopics() throws SQLException {
//        Connection conn = JdbcUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Topic> topics = new ArrayList<>();
        Topic topic = null;
        try {
            pstmt = conn.prepareStatement("select * from topic");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int tid = rs.getInt("tid");
                String tname = rs.getString("tname");
                topic = new Topic(tid, tname);
                topics.add(topic);
            }
        } finally {
            JdbcUtil.closeAll(rs, pstmt, null);
        }

        return topics;
    }

    @Override
    public boolean findTopicByTname(String tname) throws SQLException{
//        Connection conn = JdbcUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean b = false;
        try {
            pstmt = conn.prepareStatement("select * from topic where tname = ?");
            pstmt.setString(1, tname);
            rs = pstmt.executeQuery();
            if (rs.next()) b = true;
        } finally {
            JdbcUtil.closeAll(rs, pstmt, null);
        }
        return b;
    }

    @Override
    public int insert(String tname) throws SQLException {
        String sql = "insert into topic(tname) values(?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, tname);
        int count = pstmt.executeUpdate();
        JdbcUtil.closeAll(null, pstmt, null);
        return count;
    }

    @Override
    public int update(int tid, String tname) throws SQLException {
        String sql = "update topic set tname = ? where tid = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, tname);
        pstmt.setInt(2, tid);
        int count = pstmt.executeUpdate();
        JdbcUtil.closeAll(null, pstmt, null);
        return count;
    }

    @Override
    public int delete(int tid) throws SQLException {
        int count = 0;
        PreparedStatement pstmt = conn.prepareStatement("delete from topic where tid = ?");
        pstmt.setInt(1, tid);
        count = pstmt.executeUpdate();
        JdbcUtil.closeAll(null, pstmt, null);
        return count;
    }
}
