package web;

import javax.servlet.*;
import java.io.IOException;

public class EncodingsFilterFilter implements Filter {
    private String charset;
    private String forceEncoding;
    public void destroy() {
        System.out.println("destroy..............");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
       req.setCharacterEncoding(charset);
//       resp.setContentType("text/html;charset="+charset);

        System.out.println("请求过来了。。。。。。。。。。");
        chain.doFilter(req, resp);
        System.out.println("响应又通过过滤器了。。。。。。。");
    }

    public void init(FilterConfig config) throws ServletException {
        charset = config.getInitParameter("charset");
        charset = config.getInitParameter("forceEncoding");
        System.out.println("init..............");
    }

}
