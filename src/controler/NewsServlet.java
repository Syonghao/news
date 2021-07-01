package controler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hisoft.news.javabean.News;
import com.hisoft.news.service.NewsService;
import com.hisoft.news.service.TopicService;
import com.hisoft.news.service.impl.NewsServiceImpl;
import com.hisoft.news.service.impl.TopicServiceImpl;
import com.hisoft.news.util.page;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
@WebServlet(urlPatterns = {"/news","/news.do"})
public class NewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        NewsService newsService = new NewsServiceImpl();
        TopicService topicService = new TopicServiceImpl();
        if ("index".equals(action)) {//进入首页
            String tidStr = request.getParameter("tid");
            Integer tid = tidStr == null ? null : Integer.parseInt(request.getParameter("tid"));

//            List<Topic> topics = topicService.findAllTopics();
            List<News> newsList = newsService.findNewsByTid(tid);

//            Map<String, List> map = new HashMap<>();
//            map.put("topics", topics);
//            map.put("newsList", newsList);
//            request.setAttribute("map", map);
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//            StringBuilder sb = new StringBuilder("[");
//            //&#58
//
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//
//            for (News news: newsList) {
//                sb.append("{\"nid\":"+news.getNid()+",\"ntitle\":\""+news.getNtitle().replace("\"","&quot;")+"\",\"ncreateDate\":\""+df.format(news.getNcreateDate())+"\"},");
//            }
//            sb.deleteCharAt(sb.length()-1);
//            sb.append("]");
//            out.print(sb.toString());

            String jsonStr = JSON.toJSONStringWithDateFormat(newsList,"yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteMapNullValue);
            out.print(jsonStr);
        } else if ("readnews".equals(action)) {//查看单条新闻详细信息
            int nid = Integer.parseInt(request.getParameter("nid"));
            //读取单条新：标题，时间，作者，主体内容，评论列表
            News news = newsService.readNews(nid);
            request.setAttribute("news", news);
            request.getRequestDispatcher("news_read.jsp").forward(request, response);
        } else if ("backnews".equals(action)) {//管理员界面的新闻列表
            String currPageNo = request.getParameter("currPageNo");
            int pageIndex = currPageNo==null?1:Integer.parseInt(currPageNo);
            page newsPage = newsService.queryPageNews(pageIndex);
            request.setAttribute("newsPage", newsPage);
            request.getRequestDispatcher("newspages/admin.jsp").forward(request, response);
        } else if ("del".equals(action)) {//删除新闻
            int nid = Integer.parseInt(request.getParameter("nid"));
            int result = newsService.delNews(nid);
            if (result > 0) {//删除成功
                out.print("<script type=\"text/javaScript\">\n" +
                        "                        alert(\"删除成功！\");\n" +
                        "                location.href = \"news?action=backnews\";\n" +
                        "</script>");
            } else {
                out.print("<script type=\"text/javaScript\">\n" +
                        "                        alert(\"删除失败！请联系管理员\");\n" +
                        "                location.href = \"news?action=backnews\";\n" +
                        "</script>");
            }
        }else if ("addnews".equals(action)){
            News news = new News();
            String fieldName = "";

            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            //上传文件的存储路径（服务器文件系统上的绝对文件路径）
            ServletContext application =this.getServletContext();
            String uploadFilePath = application.getRealPath("upload" );
            if(isMultipart){  //判断文件或目录是否存在
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(1024*1024*5);

                try {
                    //解析form表单中所有文件
                    List<FileItem> items = upload.parseRequest(request);
                    for (FileItem item: items){
                        if (item.isFormField()) {  //文件表单字段
                            fieldName = item.getFieldName();
                            if (fieldName.equals("ntid")) {
                                news.setNtid(Integer.parseInt(item.getString("UTF-8")));
                            } else if (fieldName.equals("ntitle")) {
                                news.setNtitle(item.getString("UTF-8"));
                            } else if (fieldName.equals("nauthor")) {
                                news.setNauthor(item.getString("UTF-8"));
                            } else if (fieldName.equals("nsummary")) {
                                news.setNsummary(item.getString("UTF-8"));
                            } else if (fieldName.equals("ncontent")) {
                                news.setNcontent(item.getString("UTF-8"));
                            }
                        }else {
                            String fileName = item.getName();
                            List<String> filType= Arrays.asList("gif","bmp","jpg");
                            String ext=fileName.substring(fileName.lastIndexOf(".")+1);
                            if (fileName != null && !fileName.equals("") && !filType.contains(ext)) {
                                out.print("<script type=\"text/javascript\">\n" +
                                        "                                        alert(\"上传失败，文件只能是GIF、JPG、JPEG\");\n" +
                                        "                                location.href = \"newspages/news_add.jsp\";\n" +
                                        "</script>");
                            }else if (fileName !=null && !fileName.equals("")){
                                File saveFile = new File(uploadFilePath, item.getName());
                                item.write(saveFile);
                                news.setNpicPath(uploadFilePath + "\\" + item.getName());
                            }
                        }
                    }
                }catch (FileUploadBase.SizeLimitExceededException ex){
                    out.print("<script type=\"text/javascript\">\n" +
                            "                            alert(\"上传失败，文件太大，最大5MB\");\n" +
                            "                    location.href = \"newspages/new_add.jsp\";\n" +
                            "</script>");
                } catch (FileUploadException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (newsService.addNews(news)){
                out.print("<script type=\"text/javascript\">\n" +
                        "                        alert(\"上传成功！\");\n" +
                        "                location.href = \"newspages/admin.jsp\";\n" +
                        "</script>");
            }else{
                out.print("<script type=\"text/javascript\">\n" +
                        "                        alert(\"上传失败，请联系管理员！\");\n" +
                        "                location.href = \"newspages/news_add.jsp\";\n" +
                        "</script>");
            }
        }
    }
}
