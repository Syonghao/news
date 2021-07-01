package controler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
    urlPatterns = {"/hello","/hello.do"},
        initParams = {@WebInitParam(name="charset",value="utf-8")},
        loadOnStartup = 1
)
public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
//        String username = request.getParameter("username");
        String email = request.getParameter("email");
        if (email.equals("123456@qq.com")) {
            out.print(1);
        } else {
            out.print(0);
        }

//        if(username.equals("admin")){
//            out.print(1);
//        }else{
//            out.print(0);
//        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
