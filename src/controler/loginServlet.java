package controler;

import com.hisoft.news.javabean.NewsUser;
import com.hisoft.news.service.NewsUserService;
import com.hisoft.news.service.impl.NewsUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/login.do")


public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
//        Cookie[] cookies = request.getCookies();
//        HttpSession session = request.getSession();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("login")){
//                response.sendRedirect("newspages/admin.jsp");
//                return;
//            }
//        }


        request.setCharacterEncoding("utf-8");
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        if (uname == null ||"".equals(uname)){
            request.setAttribute("error","用户名不能为空");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else if(upwd == null || "".equals(upwd)){
            request.setAttribute("error","密码不能为空");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else {
            //
//        NewsUserDao userDao = new NewsUserDaoImpl();
            NewsUserService userService = new NewsUserServiceImpl();
            NewsUser user = userService.login(uname,upwd);

            if (user == null) {
                request.setAttribute("error","用户名或密码错误，请重新登录");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }else{
//                Cookie c = new Cookie("login","login");
//                c.setMaxAge(5*60);
//                response.addCookie(c);


                session.setMaxInactiveInterval(20*60);
                session.setAttribute("user",uname);
                request.getRequestDispatcher("newspages/admin.jsp").forward(request,response);
            }

        }
    }
}
