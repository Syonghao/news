package com.hisoft.news.javabean;

import java.util.Date;
import java.util.List;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-17 17:33:10
 **/
public class News {
    private int nid;
    private int ntid;
    private String ntitle;
    private String nauthor;
    private Date ncreateDate;
    private String npicPath;
    private String ncontent;
    private Date nmodifyDate;
    private String nsummary;

    private List<Comments> commentsList;

    public News() {
    }

    public News(int nid, String ntitle, Date ncreateDate) {
        this.nid = nid;
        this.ntitle = ntitle;
        this.ncreateDate = ncreateDate;
    }

    public News(int nid, String ntitle, String nauthor, String ncontent,Date ncreateDate) {
        this.nid = nid;
        this.ntitle = ntitle;
        this.nauthor = nauthor;
        this.ncontent = ncontent;
        this.ncreateDate = ncreateDate;
    }

    public News(int nid, int ntid, String ntitle, String nauthor, Date ncreateDate, String npicPath, String ncontent, Date nmodifyDate, String nsummary) {
        this.nid = nid;
        this.ntid = ntid;
        this.ntitle = ntitle;
        this.nauthor = nauthor;
        this.ncreateDate = ncreateDate;
        this.npicPath = npicPath;
        this.ncontent = ncontent;
        this.nmodifyDate = nmodifyDate;
        this.nsummary = nsummary;
    }

    public News(int nid, String ntitle, String nauthor) {
        this.nid = nid;
        this.ntitle = ntitle;
        this.nauthor = nauthor;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getNtid() {
        return ntid;
    }

    public void setNtid(int ntid) {
        this.ntid = ntid;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNauthor() {
        return nauthor;
    }

    public void setNauthor(String nauthor) {
        this.nauthor = nauthor;
    }

    public Date getNcreateDate() {
        return ncreateDate;
    }

    public void setNcreateDate(Date ncreateDate) {
        this.ncreateDate = ncreateDate;
    }

    public String getNpicPath() {
        return npicPath;
    }

    public void setNpicPath(String npicPath) {
        this.npicPath = npicPath;
    }

    public String getNcontent() {
        return ncontent;
    }

    public void setNcontent(String ncontent) {
        this.ncontent = ncontent;
    }

    public Date getNmodifyDate() {
        return nmodifyDate;
    }

    public void setNmodifyDate(Date nmodifyDate) {
        this.nmodifyDate = nmodifyDate;
    }

    public String getNsummary() {
        return nsummary;
    }

    public void setNsummary(String nsummary) {
        this.nsummary = nsummary;
    }
}
