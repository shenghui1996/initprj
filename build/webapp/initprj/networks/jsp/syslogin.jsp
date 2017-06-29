<%@ page contentType="text/html;charset=UTF-8"%>  
<%@ include file="../include/inc_session.jsp"%>
<%@ include file="../include/inc_common.jsp"%>
<html>
  <head>
    <title>登录</title>
  </head>
  <body>  

  <div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-8 col-md-8">
			<section class="hidden-xs">
				<h3>
					最新
				</h3>
				<p>
					<img class="img-responsive" src="http://123.59.133.80/ghp/networks/assets/img/ads.jpg"/>
				</p>
			</section>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-4">
			<img class="img-responsive center-block visible-xs" src="<%=contextPath%>/networks/themes/img/mobilelogo.png" />
			
			<h3 class="center-block">
				登录
				<hr style="border-color: #009688;"/>
			</h3>
			
			<form role="form" id="loginform">
				<div class="form-group">
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
                        <input type="text" id="email" name="email" class="form-control validate[required,custom[email]]" placeholder="电子邮件">
                    </div>
                </div>
                
				<div class="form-group">
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-eye-close"></span></span>
                       	<input type="password" id="password" name="password" class="form-control validate[required]" placeholder="输入密码">
                    </div>
                </div>    
			    
			    <div class="btn-group btn-block">
					<button type="button" style="width:50%" class="btn btn-primary ladda-button" id="logonbtn" title="登录" data-style="expand-right">
						<span class="ladda-label">
							<span class="glyphicon glyphicon-log-in"></span> &nbsp; 登录
						</span>
					</button>
					
					<button data-autosave="0" style="width:50%" type="button" class="btn btn-primary ladda-button" id="autolog" title="记住密码" data-style="expand-right">
						<span class="ladda-label">
							<span class="glyphicon glyphicon-unchecked"></span> &nbsp; 记住密码
						</span>
					</button>
				</div>
				<div class="input-group input-group-lg"></div>
				
				<div id="logalert" class="alert alert-danger hidden" style="margin-bottom:0;">
					<span class="glyphicon glyphicon-exclamation-sign"></span>
					<span id="loginfo">&nbsp;登录失败，请检查邮件或密码是否正确</span>
				</div>
			</form>
			<hr class="divider" />
			<div>
		    	<a href="register.htm" class="btn btn-flat btn-primary pull-left">
		    		<!-- <span class="glyphicon glyphicon-registration-mark"></span>&nbsp;注册 //-->
		    	</a>
		    	
		    	<a href="resetpwd.htm" class="btn btn-flat btn-primary pull-right">
		    		<span class="glyphicon glyphicon-search"></span>&nbsp;找回密码
		    	</a>
		    	
		    	<div class="clearfix"></div>
			</div>
			</div>
		</div>
	</div>
  </div>

  </body>
</html>
 

