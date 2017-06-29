<%@ page contentType="application/json;charset=UTF-8" language="java" %>  
<%@ page import="java.io.StringWriter"%> 
<%@ page import="org.codehaus.jackson.JsonFactory"%> 
<%@ page import="org.codehaus.jackson.JsonGenerator"%> 
<%@ include file="/networks/include/inc_session.jsp"%>
<%
StringBuffer sb = new StringBuffer();
JsonFactory f = new JsonFactory();
StringWriter jsonwriter = new StringWriter();
JsonGenerator g = f.createJsonGenerator(jsonwriter);	
g.writeStartObject();
if(onlineuser!=null){
	g.writeStringField("uid", onlineuser.getUid()+"");
}else{
	g.writeStringField("uid", "0");
}
g.writeEndObject();	
g.close();
jsonwriter.flush();
jsonwriter.close();
sb.append(jsonwriter.getBuffer());
%>
<%=sb%>