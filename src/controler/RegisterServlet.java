package controler;

import com.hisoft.news.service.NewsUserService;
import com.hisoft.news.service.impl.NewsUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(urlPatterns = "/register.do")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        request.setCharacterEncoding("utf-8");
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        String reupwd = request.getParameter("reupwd");
        if (uname == null ||"".equals(uname)){
            request.setAttribute("error","用户名不能为空");
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }else if(upwd == null || "".equals(upwd)){
            request.setAttribute("error","密码不能为空");
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }else if(reupwd == null || "".equals(reupwd)){
            request.setAttribute("error","重复密码不能为空");
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }else if(!upwd.equals(reupwd)){
            request.setAttribute("error","两次输入的密码不一致");
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }else{
            //调用Dao层得到结果，根据结果跳转页面
//        NewsUserDao userDao = new NewsUserDaoImpl();
            NewsUserService userService = new NewsUserServiceImpl();
            int count = userService.saveUser(uname,upwd);
            if (count > 0){
                response.sendRedirect("index.jsp");
            }else{
                request.setAttribute("error","注册失败，请联系管理员");
                request.getRequestDispatcher("register.jsp").forward(request,response);
            }
        }
    }
}
