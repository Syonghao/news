package controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hisoft.news.javabean.Comments;
import com.hisoft.news.service.CommentsService;
import com.hisoft.news.service.impl.CommentsServiceImpl;
import com.hisoft.news.util.JsonResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "CommentsServlet",urlPatterns = "/comments.do")
public class CommentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       PrintWriter out = response.getWriter();
       Integer cnid = Integer.parseInt(request.getParameter("cnid"));
       String cauthor = request.getParameter("cauthor");
        String cip = request.getParameter("cip");
        String ccontent = request.getParameter("ccontent");
        Date cdate = new Date();
        Comments comments = new Comments( cnid,ccontent,cdate,cip,cauthor);
        CommentsService commentsService = new CommentsServiceImpl();
        int result = commentsService.publlishComments(comments);
        ObjectMapper mapper = new ObjectMapper();
        JsonResult jr = null;
        if(result==1){
//            out.print("{\"state\":\"200\",\"cdate\":\""+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(cdate) +"\"}");
            jr = new JsonResult(JsonResult.SUCCESS,null,null);
            out.print(mapper.writeValueAsString(jr));
        }else {
//            out.print("{\"state\":\"201\",\"message\":\"评论发表失败，请联系管理员\"}");

            jr = new JsonResult(JsonResult.ERROR,"评论发表失败，请联系管理员",null);
        }
        out.print(mapper.writeValueAsString(jr));
    }
}
