<%@ page contentType="text/html;charset=UTF-8"%>  
<%@page import="eu.bitwalker.useragentutils.*"%> 
<%

String userAgent = request.getHeader("user-agent");
UserAgent ua = UserAgent.parseUserAgentString(userAgent);
String browserName = ua.getBrowser().toString();

StringBuffer buffer = new StringBuffer();
if(browserName.equals("IE5")||browserName.equals("IE5_5")||browserName.equals("IE6")||browserName.equals("IE7")||browserName.equals("IE8")){
	buffer.append(request.getContextPath() + "/networks/update-browser.html");
}else{
	buffer.append(request.getContextPath() + "/syslogin.htm");
}
response.sendRedirect(buffer.toString());
%>

