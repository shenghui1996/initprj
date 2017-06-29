<%
if(onlineuser==null||onlineuser.getUid()==0){

	StringBuffer buffer = new StringBuffer();
	String getURI = (String)request.getAttribute("javax.servlet.forward.request_uri"); //this help to get real url used in browser
	String query = request.getQueryString()==null?"":request.getQueryString();
	if(!query.equals("")){
		query = "?" + query;
	}
	if(getURI.indexOf("logout")<0){
		buffer.append(contextPath).append("/syslogin.htm").append("#").append(getURI).append(query);
	}else{
		buffer.append(contextPath).append("/syslogin.htm");
	}
	response.sendRedirect(buffer.toString());
}
%>