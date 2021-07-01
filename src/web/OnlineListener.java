package web;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
@WebListener()
public class OnlineListener implements HttpSessionAttributeListener {



    public void attributeAdded(HttpSessionBindingEvent sbe) {
      if (sbe.getName().equals("user")){
          System.out.println(sbe.getValue()+"上线了");
      }
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        if (sbe.getName().equals("user")){
            System.out.println(sbe.getValue()+"下线了");
        }
    }


}
