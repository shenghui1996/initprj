package com.sas.pdg.mrr.filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sas.pdg.mrr.dto.UserDTO;
import com.sas.pdg.mrr.exception.BusinessException;
import com.sas.pdg.mrr.exception.SystemException;
import com.sas.pdg.mrr.service.IdentityService;
import com.sas.pdg.mrr.util.BCrypt;
import com.sas.pdg.mrr.util.EncryptUtil;
import com.sas.pdg.mrr.util.Utils;


public class ActionFilter implements Filter
{
  public static IdentityService is = null;
  
  public static final int GUESTID = 0;
  
  public static final int ADMINROLE = 1;
  public static final int USERROLE = 2;
  
  public static final int DELETED = 1;
  public static final int NOTDELETED = 0;
  
  public void destroy()
  {
    // TODO Auto-generated method stub

  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
  {
    // TODO Auto-generated method stub
    String errorMsg = null;
    if (request instanceof HttpServletRequest && response instanceof HttpServletResponse)
    {
      final HttpServletRequest httpRequest = (HttpServletRequest) request;
      final HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpRequest.setCharacterEncoding("UTF-8");
      HttpSession httpSession = httpRequest.getSession(false);
      String path = httpRequest.getServletPath();
     
      if ("/ajaxlogin.htm".equals(path))
      {
          String mail = httpRequest.getParameter("mail");
          String password = httpRequest.getParameter("password"); 

          if ((!Utils.isNull(mail)) && (!Utils.isNull(password)))
          {
        	UserDTO dto = new UserDTO();
            dto = is.findUserByMail(mail);
            
            if(dto!=null){
            	try {
	            	EncryptUtil encrypt = new EncryptUtil();
					String d_pwd = encrypt.base64Decoder(password);
	            	
		            if(BCrypt.checkpw(d_pwd, dto.getPassword())){
		            	dto.setAccessurl(path);
		            	dto.setClientid(httpRequest.getRemoteAddr() + " - " +  httpRequest.getHeader("User-Agent"));
		            	dto.setLastlogindateforreminder(dto.getLastlogindate());
		            	httpSession.setAttribute("system.userinfo", dto);
		            }
            	} catch (Exception e) {
            		// TODO Auto-generated catch block
            		e.printStackTrace();
            	}
            }else{
            	if(httpSession.getAttribute("system.userinfo")==null){
                	dto.setUid(GUESTID);
                	dto.setAccessurl(path);
                	dto.setClientid(httpRequest.getRemoteAddr() + " - " + httpRequest.getHeader("User-Agent"));
                	httpSession.setAttribute("system.userinfo", dto);
            	}
            }
          }
          
      }else if("/logout.htm".equals(path)||"/mobilelogout.htm".equals(path)||"/rtwidget-logout.htm".equals(path)){

    	if(httpSession!=null){
	    	UserDTO dto = new UserDTO();
	    	dto.setUid(GUESTID);
	    	dto.setAccessurl(path);
	    	dto.setClientid(httpRequest.getRemoteAddr() + " - " + httpRequest.getHeader("User-Agent"));
	    	dto.setAvatar("avatar.jpg");
	    	dto.setBackground("");
	    	dto.setSitename("");
	    	dto.setSignature("");
	    	httpSession.setAttribute("system.userinfo", dto);
    	}
    	
        Cookie killMyCookie = new Cookie("gb.mail", null);
        killMyCookie.setMaxAge(0);
        killMyCookie.setPath("/");
        httpResponse.addCookie(killMyCookie);
        Cookie killMyCookie2 = new Cookie("gb.password", null);
        killMyCookie2.setMaxAge(0);
        killMyCookie2.setPath("/");
        httpResponse.addCookie(killMyCookie2);
        
      }else{
    	  if(httpSession==null){
    		  httpSession = httpRequest.getSession(true);
    	  }
    	  Object obj = httpSession.getAttribute("system.userinfo");
    	  if(obj==null){
      		UserDTO dto = new UserDTO();
        	dto.setUid(GUESTID);
        	dto.setAccessurl(path);
        	dto.setClientid(httpRequest.getRemoteAddr() + " - " + httpRequest.getHeader("User-Agent"));
        	dto.setAvatar("avatar.jpg");
        	dto.setBackground("");
        	dto.setSitename("");
        	dto.setLastlogindate(new Date());
        	httpSession.setAttribute("system.userinfo", dto);
    	  }else{
    		UserDTO dto = (UserDTO)obj;
    		dto.setAccessurl(path);
    		httpSession.setAttribute("system.userinfo", dto);
    	  }
      }
      
      if(path.startsWith("/ajaxcaptcha.htm")){
    	Random random = new Random();
    	int intr = random.nextInt(5);
    	httpSession.setAttribute("system.captcha", intr);
      }
      
      filterChain.doFilter(request, response);
    }
  }

  public void init(FilterConfig filter) throws ServletException
  {
    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(filter.getServletContext());
    this.is = (IdentityService)wac.getBean("identityService");
  }
  
}
