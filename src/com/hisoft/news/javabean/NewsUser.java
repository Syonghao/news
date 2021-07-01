package com.hisoft.news.javabean;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-16 09:45:30
 **/
public class NewsUser {
    private int uid;
    private String uname;
    private String upwd;

    public NewsUser(){}

    public NewsUser(int uid, String uname, String upwd) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    @Override
    public String toString() {
        return "NewsUser{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                '}';
    }
}
