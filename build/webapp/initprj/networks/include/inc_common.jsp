<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<%@page import="eu.bitwalker.useragentutils.*"%> 

<%

String gb_userAgent = request.getHeader("user-agent");
UserAgent gb_ua = UserAgent.parseUserAgentString(gb_userAgent);
String gb_browserName = gb_ua.getBrowser().toString();

StringBuffer gb_buffer = new StringBuffer();
if(gb_browserName.equals("IE5")
	||gb_browserName.equals("IE5_5")
	||gb_browserName.equals("IE6")
	||gb_browserName.equals("IE7")
	||gb_browserName.equals("IE8")){
	gb_buffer.append(request.getContextPath() + "/networks/update-browser.html");
	response.sendRedirect(gb_buffer.toString());
}

%>

<%
String contextPath = request.getContextPath();
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
response.setContentType("text/html; charset=UTF-8");
%>