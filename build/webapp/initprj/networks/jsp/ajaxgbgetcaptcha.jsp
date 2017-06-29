<%@ page contentType="application/json;charset=UTF-8" language="java" %>  
<%@ page import="java.io.StringWriter"%> 
<%@ page import="com.sas.pdg.mrr.servlet.CaptchaServlet"%>
<%@ page import="org.codehaus.jackson.JsonFactory"%> 
<%@ page import="org.codehaus.jackson.JsonGenerator"%> 
<%@ include file="/networks/include/inc_session.jsp"%>
<%
StringBuffer sb = new StringBuffer();
JsonFactory f = new JsonFactory();
StringWriter jsonwriter = new StringWriter();
JsonGenerator g = f.createJsonGenerator(jsonwriter);	
g.writeStartObject();

String sessionToken = CaptchaServlet.getToken(session);
String requestToken = request.getParameter("captcha");

//System.out.println("### " + sessionToken + ":" + requestToken);

boolean captcharesult = sessionToken != null && requestToken != null && sessionToken.toLowerCase().equals(requestToken.toLowerCase());

if(captcharesult){
	g.writeStringField("captcharesult", "ok"); //succeed
}else{
	g.writeStringField("captcharesult", "failed"); //failed
}

g.writeEndObject();	
g.close();
jsonwriter.flush();
jsonwriter.close();
sb.append(jsonwriter.getBuffer());
%>
<%=sb%>