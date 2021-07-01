package com.hisoft.news.javabean;

import java.util.Date;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-19 09:48:27
 **/
public class Comments {
    private int cid;
    private int cnid;
    private String ccontent;
    private Date cdate;
    private String  cip;
    private String cauthor;

    public Comments() {
    }

    public Comments(int cid, int cnid, String ccontent, Date cdate, String cip, String cauthor) {
        this.cid = cid;
        this.cnid = cnid;
        this.ccontent = ccontent;
        this.cdate = cdate;
        this.cip = cip;
        this.cauthor = cauthor;
    }

    public Comments(int cnid, String ccontent, Date cdate, String cip, String cauthor) {
        this.cnid = cnid;
        this.ccontent = ccontent;
        this.cdate = cdate;
        this.cip = cip;
        this.cauthor = cauthor;
    }

    public Comments(Integer cnid, String cauthor, String cip, String ccontent, Date cdate) {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCnid() {
        return cnid;
    }

    public void setCnid(int cnid) {
        this.cnid = cnid;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getCauthor() {
        return cauthor;
    }

    public void setCauthor(String cauthor) {
        this.cauthor = cauthor;
    }
}
