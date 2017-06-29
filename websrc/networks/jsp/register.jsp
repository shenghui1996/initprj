<%@page import="com.sas.pdg.mrr.servlet.CaptchaServlet"%>
<%@ page contentType="text/html;charset=UTF-8"%>
  
<%@ include file="../include/inc_session.jsp"%>
<%@ include file="../include/inc_common.jsp"%>

<%

CaptchaServlet.generateToken(session);

%>

<html>
  <head>
    <title>注册</title>
  </head>
  <body>  

  <div class="container">
	<div class="row" style="margin:10px 0 50px;">
		
		<div class="col-xs-12 col-sm-5 col-md-5">
			<h1></h1>
			<img class="img-responsive center-block" src="<%=contextPath%>/networks/themes/img/mobilelogo.png" />
		</div>
		<div class="col-xs-12 col-sm-1 col-md-1"></div>
		<div class="col-xs-12 col-sm-5 col-md-5">
			
			<form role="form" id="regform">
				<div class="page-header">
				<h2 class="text-center text-warning">
					<i class="glyphicon glyphicon-comment"></i> 快速注册
				</h2>
				</div>
			
				<div class="input-group input-group-lg">
				  <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
			      <input type="text" id="fullname" name="email" class="form-control validate[required,custom[onlyChineseLetterNumber],minSize[2]]" placeholder="用户昵称">
			    </div>
			    
				<div class="input-group input-group-lg">
				  <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
			      <input type="text" id="mail" name="email" class="form-control validate[required,custom[email]]" placeholder="电子邮件">
			    </div>
			    
				<div class="input-group input-group-lg">
				  <span class="input-group-addon"><span class="glyphicon glyphicon-eye-close"></span></span>
			      <input type="password" id="pwd" name="password" class="form-control validate[required,minSize[6]]" placeholder="输入密码">
			    </div>
			    
				<div class="input-group input-group-lg">
				  <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
			      <input type="text" id="captcha" name="captcha" class="form-control validate[required,minSize[7]]" placeholder="输入验证码 /*区别大小写*/">
			    </div>
			    
				<div class="input-group input-group-lg" style="width:100%;height:50px;margin:0 auto;background:#FFF;" >
				  <div class="form-control" id="gbcaptchawrapper">
				  	<img class="img-responsive" alt="验证图片" src="<%=contextPath%>/gbcaptcha" />
				  </div>
				  
				  <span class="input-group-addon">
				  	<a class="btn btn-primary" id="refreshCaptcha"><span class="glyphicon glyphicon-refresh"></span></a>
				  </span>
			    </div>
			    
				<hr/>
				
				<button type="button" class="btn btn-primary ladda-button pull-right" id="registerbutton" title="立刻注册" data-style="expand-right">
					<span class="ladda-label">
						<span class="glyphicon glyphicon-log-in"></span> &nbsp; 立刻注册
					</span>
				</button>
				
				<div class="hidden">
					<ul id="userlist"></ul>
					<ul id="userfunctiontaglist"></ul>
				</div>
				
			</form>
			
			<div id="regsucceed" class="hidden">
				<div class="page-header">
				<h2 class="text-center text-primary">
					<i class="glyphicon glyphicon-thumbs-up"></i> 恭喜注册成功
				</h2>
				</div>
				
				<div class="well">
				<div class="alert alert-success large" role="alert">
					<i class="glyphicon glyphicon-certificate"></i> 注册已成功，请立刻访问电子邮箱激活您的注册账号
				</div>
				<hr/>
				<a class="btn btn-warning btn-lg btn-block" href="<%=contextPath%>/syslogin.htm">返回登录页</a>
				</div>
			</div>
		</div>
		
	</div>
  </div>
	
  </body>
</html>
 

