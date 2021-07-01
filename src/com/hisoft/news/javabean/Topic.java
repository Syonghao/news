package com.hisoft.news.javabean;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-17 10:18:51
 **/
public class Topic {
    private int tid;
    private String tname;

    public Topic() {
    }

    public Topic(int tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
