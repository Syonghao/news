package com.hisoft.news.service;

import com.hisoft.news.javabean.Topic;

import java.util.List;

/**
 * @program: news
 * @description:
 * @author: wlg
 * @create: 2021-06-18 16:01:20
 **/
public interface TopicService {

    /**
     * 根据id删除主题
     * @param tid
     * @return
     */
    public int deleteTopic(Integer tid);

    /**
     * 查询所有主题
     * @return
     */
    public List<Topic> findAllTopics();


    /**
     * 添加主题
     * @param tname
     * @return
     */
    public int saveTopic(String tname);

    /**
     * 修改主题
     * @param tid
     * @param tname
     * @return
     */
    public int modifyTopic(int tid,String tname);

}
