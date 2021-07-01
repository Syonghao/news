package web;

import com.hisoft.news.javabean.Topic;
import com.hisoft.news.service.TopicService;
import com.hisoft.news.service.impl.TopicServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class GlobalResourceListener implements ServletContextListener{


    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        TopicService topicService = new TopicServiceImpl();
        List<Topic> topics = topicService.findAllTopics();
        application.setAttribute("topics",topics);
        System.out.println("全局资源加载完毕！！");
    }


}
