package com.hisoft.news.dao.impl;

import com.hisoft.news.dao.NewsDao;
import com.hisoft.news.javabean.News;
import com.hisoft.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-17 17:35:21
 **/
public class NewsDaoImpl implements NewsDao {
    private Connection conn;

    public NewsDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<News> getNewsByTid(Integer tid) throws SQLException {
//        Connection conn = JdbcUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        News news = null;
        List<News> newsList = new ArrayList<>();
        try {
            String sql = "select nid,ntitle,ncreateDate from news";
            if(tid != null){
                sql += " where ntid = ? order by ncreateDate desc";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,tid);
            }else{
                sql+=" order by ncreateDate desc";
                pstmt = conn.prepareStatement(sql);
            }

            rs = pstmt.executeQuery();
            while (rs.next()) {
                int nid = rs.getInt("nid");
                String ntitle = rs.getString("ntitle");
                Date ncreateDate = rs.getTimestamp("ncreateDate");
                news = new News(nid, ntitle, ncreateDate);
                newsList.add(news);
            }
        } finally {
            JdbcUtil.closeAll(rs, pstmt, null);
        }
        return newsList;
    }

    @Override
    public List<News> getNewsByTname(String tname) throws SQLException {
//        Connection conn = JdbcUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        News news = null;
        List<News> newsList = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select nid,ntitle,ncreateDate from news,topic where news.ntid = topic.tid and tname = ? order by ncreateDate desc limit 5");
            pstmt.setString(1, tname);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int nid = rs.getInt("nid");
                String ntitle = rs.getString("ntitle");
                Date ncreateDate = rs.getDate("ncreateDate");
                news = new News(nid, ntitle, ncreateDate);
                newsList.add(news);
            }
        } finally {
            JdbcUtil.closeAll(rs, pstmt, null);
        }
        return newsList;
    }

    @Override
    public void delete(int nid) throws SQLException {
        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement("delete from news where nid = ?");
        pstmt.setInt(1,nid);
        pstmt.executeUpdate();
        JdbcUtil.closeAll(null,pstmt,null);
    }

    @Override
    public int getNewsCount() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try{
            pstmt = conn.prepareStatement("select count(1) from news");
            rs = pstmt.executeQuery();
            rs.next();
            count = rs.getInt(1);
        }finally {
            JdbcUtil.closeAll(rs, pstmt, null);
        }
        return count;
    }

    @Override
    public void insert(News news) throws SQLException {
        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement("insert into news(ntid,ntitle,nauthor,ncreateDate,npicPath,ncontent,nsummary) values(?,?,?,?,?,?,?)");
        pstmt.setInt(1,news.getNtid());
        pstmt.setString(2,news.getNtitle());
        pstmt.setString(3,news.getNauthor());
        pstmt.setObject(4,news.getNcreateDate());
        pstmt.setString(5,news.getNpicPath());
        pstmt.setString(6,news.getNcontent());
        pstmt.setString(7,news.getNsummary());
        pstmt.executeUpdate();
        JdbcUtil.closeAll(null, pstmt, null);
    }

    @Override
    public News getNewsById(int nid) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        News news = null;
        try {
            pstmt = conn.prepareStatement("select ntitle,nauthor,ncreateDate,ncontent from news where nid = ?");
            pstmt.setInt(1,nid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String ntitle = rs.getString("ntitle");
                String nauthor = rs.getString("nauthor");
                String ncontent = rs.getString("ncontent");
                Date ncreateDate = rs.getDate("ncreateDate");
                news = new News(nid,ntitle,nauthor,ncontent,ncreateDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(rs, pstmt, null);
        }
        return news;
    }

    @Override
    public List<News> getPageNews(int pageIndex,int pageSize ) throws SQLException {
//        Connection conn = JdbcUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        News news = null;
        List<News> newsList = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select nid,ntitle,nauthor from news order by ncreateDate desc limit ?,?");
            pstmt.setInt(1,(pageIndex-1)*pageSize);
            pstmt.setInt(2,pageSize);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int nid = rs.getInt("nid");
                String ntitle = rs.getString("ntitle");
                String nauthor = rs.getString("nauthor");
                news = new News(nid, ntitle, nauthor);
                newsList.add(news);
            }
        } finally {
            JdbcUtil.closeAll(rs, pstmt, null);
        }
        return newsList;
    }
}
