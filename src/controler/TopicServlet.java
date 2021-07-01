package controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hisoft.news.javabean.Topic;
import com.hisoft.news.service.TopicService;
import com.hisoft.news.service.impl.TopicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet(urlPatterns = "/topic.do")
public class TopicServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    public String getInitParameter(String name) {
        return super.getInitParameter(name);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        TopicService topicService = new TopicServiceImpl();
        if ("list".equals(action)) {//查询主题列表
            List<Topic> topics = topicService.findAllTopics();
//            StringBuilder sb = new StringBuilder("[");
//            for (Topic topic : topics) {
//                sb.append("{\"tid\":\""+topic.getTid()+"\",\"tname\":\""+topic.getTname()+"\"},");
//            }
//            sb.deleteCharAt(sb.length()-1);
//            sb.append("]");
//            out.print(sb.toString());
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(topics);
            out.print(jsonStr);
//            request.setAttribute("topics", topics);
//            request.getRequestDispatcher("newspages/topic_list.jsp").forward(request, response);
        } else if ("addtopic".equals(action)) {//添加主题
            String tname = request.getParameter("tname");
            if (tname == null || "".equals(tname)) {
                request.setAttribute("error", "主题名称不能为空");
                request.getRequestDispatcher("newspages/topic_add.jsp").forward(request, response);
            } else {
                //判断新增的主题是否已存在，如果存在，不能添加
                int count = topicService.saveTopic(tname);
                if (count == -1) {//主题已存在
                    request.setAttribute("error", "主题名称已存在，请重新添加");
                    request.getRequestDispatcher("newspages/topic_add.jsp").forward(request, response);
                } else {//主题不存在，可以添加
                    if (count > 0) {
                       List<Topic> topics = topicService.findAllTopics();
                        request.getServletContext().setAttribute("topics", topics);
                        out.print("<script type = \"text/javaScript\" >\n" +
                                "alert(\"添加成功！\");\n" +
                                "location.href = \"newspages/topic_list.jsp?action=list\";\n" +
                                "</script >");

                    } else {
                        request.setAttribute("error", "添加失败，请联系管理员");
                        request.getRequestDispatcher("newspages/topic_add.jsp").forward(request, response);
                    }
                }
            }
        }else if ("updatetopic".equals(action)) {//执行修改操作
                String tname = request.getParameter("tname");
                int tid = Integer.parseInt(request.getParameter("tid"));
                if (tname == null || "".equals(tname)) {
                    request.setAttribute("error", "主题名称不能为空");
                    request.getRequestDispatcher("newspages/topic_modify.jsp").forward(request, response);
                } else {
                    //判断新增的主题是否已存在，如果存在，不能修改
                    int count = topicService.modifyTopic(tid,tname);
                    if (count == -1) {
                        request.setAttribute("error", "主题名称已存在，请换个名字吧");
                        request.getRequestDispatcher("newspages/topic_modify.jsp").forward(request, response);
                    } else {//主题不存在，可以修改
                        if (count > 0) {
                            List<Topic> topics = topicService.findAllTopics();
                            request.getServletContext().setAttribute("topics", topics);
                            out.print("<script type=\"text/javaScript\">\n" +
                                    "                                    alert(\"修改成功！\");\n" +
                                    "                            location.href = \"newspages/topic_list.jsp?action=list\";\n" +
                                    "</script>");
                        } else {
                            request.setAttribute("error", "修改失败，请联系管理员");
                            request.getRequestDispatcher("newspages/topic_modify.jsp").forward(request, response);
                        }
                    }
                }
            } else if ("del".equals(action)) {//删除操作
                //主题下如果有新闻，不能删除
                int tid = Integer.parseInt(request.getParameter("tid"));

                int count = topicService.deleteTopic(tid);
                if (count == -1) {
out.print("<script type=\"text/javaScript\">\n" +
        "                            alert(\"该主题下有新闻，不能删除！\");\n" +
        "                    location.href = \"newspages/topic_list.jsp?action=list\";\n" +
        "</script>");
                } else if (count == 0) {
out.print("<script type=\"text/javaScript\">\n" +
        "                            alert(\"删除失败！请联系管理员\");\n" +
        "                    location.href = \"newspages/topic_list.jsp?action=list\";\n" +
        "</script>");
                } else {
                    List<Topic> topics = topicService.findAllTopics();
                    request.getServletContext().setAttribute("topics", topics);
out.print("<script type=\"text/javaScript\">\n" +
        "                            alert(\"删除成功！\");\n" +
        "                    location.href = \"newspages/topic_list.jsp?action=list\";\n" +
        "</script>");
                }
            }
        }
    }

