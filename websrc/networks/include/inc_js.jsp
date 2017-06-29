<%@page contentType="text/html;charset=UTF-8"%>  
<%@page import="com.sas.pdg.mrr.util.Utils"%> 
<script type='text/javascript'>
var gbin1_sessionid = '<%=session.getId()%>',
    gbin1_userid = '<%=onlineuser.getUid()%>';

	/* Mapping onlineuser object to javascript object */
	var global_onlineuser = {
			uid:<%=onlineuser.getUid()%>, 
			fullname:'<%=onlineuser.getFullname()%>',
			avatar:'<%=onlineuser.getAvatar()%>',
			background:'<%=onlineuser.getBackground()%>',
			roleid: '<%=onlineuser.getRoleid()%>',
			sitename: '<%=onlineuser.getSitename()%>',
			selfintro: '<%=(onlineuser.getSelfintro()!=null)?onlineuser.getSelfintro().replaceAll("'","\\x27").replaceAll("\n",""):""%>',
			lastlogondate: '<%=Utils.toDateString(onlineuser.getLastlogindate())%>',
			lastlogondateforreminder: '<%=Utils.toDateString(onlineuser.getLastlogindateforreminder())%>',
			deleted: <%=onlineuser.getDeleted()%>
	}, global_contextPath = '<%= contextPath %>';
</script>
<!-- DWR -->
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/identityService.js?v20150225'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js?v20150225'></script>
<script type='text/javascript' src="http://cdn.gbtags.com/jquery/1.7.2/jquery.min.js"></script>
<script type='text/javascript'>window.jQuery || document.write('<script src="<%= contextPath %>/networks/assets/js/jquery-1.7.1.min.js"><\/script>')</script>
<script type='text/javascript' src="http://libs.baidu.com/jqueryui/1.10.2/jquery-ui.min.js"></script>
<script type='text/javascript' src='<%=contextPath%>/networks/assets/js/all.min.js?v20150225.2'></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="http://cdn.gbtags.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.gbtags.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->