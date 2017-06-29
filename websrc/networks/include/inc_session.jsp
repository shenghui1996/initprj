<%@ page import="java.util.Date" %>
<%@ page import="com.sas.pdg.mrr.dto.UserDTO" %>
<%
	HttpSession httpSession = request.getSession(false);
	UserDTO onlineuser = (UserDTO)httpSession.getAttribute("system.userinfo");
%>
<%UserDTO user = (UserDTO)request.getAttribute("gbin1.user");%>
<%
if(user==null){
	user = new UserDTO();
	user.setUid(0);
	user.setFullname("");
	user.setAvatar("");
	user.setSitename("");
	user.setBackground("");
	user.setRoleid(0);
	user.setLastlogindate(new Date());
}
%>

<%
	Cookie cookies[]=request.getCookies();
	String mail = "";
	String password = "";
	if(cookies!=null){
		for(int i=0;i<cookies.length;i++){
			String cname=cookies[i].getName();
			if(cname.equals("gb.mail")){
				mail = cookies[i].getValue();
			}
			if(cname.equals("gb.password")){
				password = cookies[i].getValue();
			}
		}
		
		
		String sgetURI = (String)request.getAttribute("javax.servlet.forward.request_uri"); //this help to get real url used in browser
		String squery = request.getQueryString()==null?"":request.getQueryString();
		
		
		if(onlineuser!=null&&onlineuser.getUid()==0){
			if(!(mail.equals("")||password.equals(""))){
			
					if(sgetURI.indexOf("logout.htm")<0){
						response.sendRedirect(request.getContextPath() + "/ajaxautologon.htm#" + sgetURI + "?" + squery);
						
					}else{
						response.sendRedirect(request.getContextPath() + "/syslogin.htm");
	
					}
					
					return;
			}
		}

	}
%>
