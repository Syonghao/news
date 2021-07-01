package com.hisoft.news.dao.impl;

import com.hisoft.news.dao.CommentsDao;
import com.hisoft.news.javabean.Comments;
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
 * @create: 2021-06-19 09:52:05
 **/
public class CommentsDaoImpl implements CommentsDao {
    private Connection conn;

    public CommentsDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void delByNid(int nid) throws SQLException {
        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement("delete from comments where cnid = ?");
        pstmt.setInt(1,nid);
        pstmt.executeUpdate();
        JdbcUtil.closeAll(null,pstmt,null);
    }

    @Override
    public void insertcomment(Comments comments) throws SQLException {
        PreparedStatement pstmt = null;


            pstmt = conn.prepareStatement("insert into commments(cnid,ccontent,cdate,cauthor) values(?,?,?,?)");
            pstmt.setInt(1,comments.getCnid());
            pstmt.setString(2,comments.getCcontent());
            pstmt.setObject(3,comments.getCdate());
            pstmt.setString(4,comments.getCauthor());
            pstmt.executeUpdate();
            JdbcUtil.closeAll(null, pstmt, null);

    }

    @Override
    public int saveComments(Comments comments) throws SQLException {
        int count = 0;
        PreparedStatement pstmt = conn.prepareStatement("insert into comments(cnid,ccontent,cdate,cip,cauthor) values(?,?,?,?,?)");
        pstmt.setInt(1,comments.getCnid());
        pstmt.setString(2,comments.getCcontent());
        pstmt.setObject(3,comments.getCdate());
        pstmt.setString(4,comments.getCip());
        pstmt.setString(5,comments.getCauthor());
        count = pstmt.executeUpdate();
        JdbcUtil.closeAll(null,pstmt,null);
        return count;
    }

    @Override
    public List<Comments> getCommentsByNid(int nid) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Comments comments = null;
        List<Comments> commentsList = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select * from comments where cnid = ? order by cdate desc");
            pstmt.setInt(1,nid);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int cid = rs.getInt("cid");
                String ccontent = rs.getString("ccontent");
                Date cdate = rs.getDate("cdate");
                String cip = rs.getString("cip");
                String cauthor = rs.getString("cauthor");
                comments = new Comments(cid, nid,ccontent,cdate,cip,cauthor);
                commentsList.add(comments);
            }
        }finally {
            JdbcUtil.closeAll(rs,pstmt,null);
        }

        return commentsList;
    }
}
