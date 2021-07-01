package com.hisoft.news.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-16 09:05:34
 **/
public class JdbcUtil {
    public static DataSource ds;

    static {
        Properties pro = new Properties();
        try {
            pro.load(JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = ds.getConnection();
            System.out.println("获取连接："+connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 所有的增删改操作
     *
     * @param sql    要执行的具体insert update delete带占位符的语句
     * @param params 数量不定的具体参数
     * @return
     */
    public static int executeUpdate(String sql, Object... params) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            count = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return count;
    }

    public static void closeAll(ResultSet rs, Statement stmt, Connection conn) {
        //6.关闭连接
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                System.out.println("归还连接："+conn);
                conn.close();//归还连接
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
