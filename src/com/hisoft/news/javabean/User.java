package com.hisoft.news.javabean;

/**
 * @program: news
 * @description:
 * @author: syh
 * @create: 2021-06-24 10:31:54
 **/
public class User {
    private String uname;
    private String cname;
    private String[] hobbies;

    public User(String uname, String cname, String[] hobbies) {
        this.uname = uname;
        this.cname = cname;
        this.hobbies = hobbies;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }
}