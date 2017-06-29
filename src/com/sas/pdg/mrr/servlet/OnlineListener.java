package com.sas.pdg.mrr.servlet;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.sas.pdg.mrr.dto.UserDTO;

public class OnlineListener implements ServletContextListener,ServletContextAttributeListener,HttpSessionListener,HttpSessionAttributeListener {  
    private ServletContext application = null ;
    private HttpSession session = null ;  
    public void contextInitialized(ServletContextEvent sce) {       
        //初始化一个application对象
        this.application=sce.getServletContext() ;
        //设置一个列表属性，用于保存在线用户名
        this.application.setAttribute("online", new ArrayList()) ;
    }   
    public void contextDestroyed(ServletContextEvent sce) {       
    }   
    public void attributeAdded(ServletContextAttributeEvent scab) {      
    }  
    public void attributeRemoved(ServletContextAttributeEvent scab) {      
    }   
    public void attributeReplaced(ServletContextAttributeEvent scab) {
       
    }   
    public void sessionCreated(HttpSessionEvent se) {     
    	
    }  
    public void sessionDestroyed(HttpSessionEvent se) {
   
        //取得用户名列表
        List online=(List)this.application.getAttribute("online") ;
        //取得当前用户名
        UserDTO username=(UserDTO)se.getSession().getAttribute("system.userinfo") ;
        //将此用户名从列表中删除
        online.remove(username) ;
        //将删除后的列表重新设置到application属性中
        this.application.setAttribute("online", online) ;
        
//        System.out.println("LOG:::::::::::::::::::::::::::::sessionDestroyed");
    }  
    public void attributeAdded(HttpSessionBindingEvent se) {
        //取得用户名列表
        List online=(List)this.application.getAttribute("online") ;
        //将当前用户名添加到列表中
        if(se.getName().equals("system.userinfo")){
        	UserDTO olduserdto = (UserDTO)se.getValue();
        	UserDTO userdto = (UserDTO)se.getSession().getAttribute("system.userinfo");
        	online.remove(olduserdto);
        	online.add(userdto);
        }
        //将添加后的列表重新设置到application属性中
        this.application.setAttribute("online", online) ;
//        System.out.println("LOG:::::::::::::::::::::::::::::attributeAdded");
    }
   
    public void attributeRemoved(HttpSessionBindingEvent se) {
//    	System.out.println("LOG:::::::::::::::::::::::::::::attributeRemoved");
    }  
    public void attributeReplaced(HttpSessionBindingEvent se) {     
        //取得用户名列表
        List online=(List)this.application.getAttribute("online") ;
        //将当前用户名添加到列表中
        if(se.getName().equals("system.userinfo")){
        	UserDTO olduserdto = (UserDTO)se.getValue();
        	UserDTO userdto = (UserDTO)se.getSession().getAttribute("system.userinfo");
        	online.remove(olduserdto);
        	online.add(userdto);
        }
        //将添加后的列表重新设置到application属性中
        this.application.setAttribute("online", online) ;
    }
}
