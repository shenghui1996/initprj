<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.sas.pdg.mrr.dto.UserDTO" %>
<%@ page import="java.util.Date" %>
<%@ include file="../include/inc_common.jsp"%>
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
<html>
<head>
<%@ include file="../include/inc_js.jsp"%>
<script type="text/javascript"> 
$(document).ready(function() { 
	var mailval = $.cookie("gb.mail"), pwdval = $.cookie("gb.password");

	$.getJSON('ajaxlogin.htm?fixie=' + new Date().getMilliseconds(), {cache:false,mail:mailval,password:pwdval})
	 .done(function( data ) {
		var hash = window.location.hash.substring(1);
		if(data.uid!==0){
			if(hash.length>0){
				window.location.href= hash;
			}else{
				window.location.href= global_contextPath + '/user/' + $.trim(data) + '.htm';
			}
		}else{
			window.location.href= '<%=contextPath%>/syslogin.htm';
		}
	})
	.fail(function( jqxhr, textStatus, error ) {
		$.cookie("gb.mail",'',{expires: 30, path: '/'});
		$.cookie("gb.password",'',{expires: 30, path: '/'});
		window.location.href= '<%=contextPath%>/syslogin.htm';
	});
});
</script>
</head>
<body>
	<div style="text-align:center;font-size:14px;margin-top:200px;font-family:'microsoft yahei',Arial,sans-serif;">自动登录中，请稍后 ...</div>
</body>
</html>