package com.hisoft.news.service;

import com.hisoft.news.javabean.NewsUser;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-18 17:08:50
 **/
public interface NewsUserService {
    /**
     * 注册
     * @return
     */
    public int saveUser(String uname,String upwd);

    /**
     * 登录
     * @param uname
     * @param upwd
     * @return
     */
    public NewsUser login(String uname, String upwd);
}
