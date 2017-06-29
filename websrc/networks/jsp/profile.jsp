<%@ page contentType="text/html;charset=UTF-8"%>  
<%@ include file="../include/inc_session.jsp"%>
<%@ include file="../include/inc_common.jsp"%>
<%@ include file="../include/inc_redirect.jsp"%>

<html>
<head>
	<title><%= onlineuser.getFullname()%> 的个人中心</title>
	<style type="text/css">
		.my-profile .btn-primary.active:not(.btn-link):not(.btn-flat) {
			background-color: #00b5a4 !important;
		}
		.my-profile .nav-tabs {
			border: none;
		}
		.my-profile .nav-tabs > li > a {
			padding: 8px 30px;
		}
		.my-profile .profile-headding {
			padding-top: 0 !important;
			padding-bottom: 0 !important;
		}
		.my-profile .no-padding {
			padding-top: 0 !important;
			padding-right: 0 !important;
			padding-bottom: 0 !important;
			padding-left: 0 !important;
		}
		.my-profile .profile-save .btn {
			padding: 10px 20px;
		}
		.my-profile .profile-bg {
			height: 150px;
			background-size: cover;
		}
		.my-profile .avatar-wrapper > div {
			display: block;
			position: absolute;
			top: 0;
			left: 0;
		}
		.my-profile .avatar-wrapper {
			position: relative;
			width: 150px;
			height: 75px;
			margin: 0 auto;
			top: -85px;
		}
		.my-profile .avatar-wrapper .avatar {
			max-width: 140px;
			border: 1px solid #fff;
			border-radius: 100%;
			box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
		}
		.my-profile .avatar-wrapper .avatar-upload-layer {
			width: 140px;
			height: 140px;
			padding: 0;
			margin: 0;
			border-radius: 100%;
			box-shadow: 0 0 1000px 1000px rgba(0, 0, 0, 0.6) inset;
		}
		.my-profile .avatar-wrapper .avatar-upload-layer:hover {
			box-shadow: 0 0 0 transparent inset;
		}
		.my-profile .avatar-wrapper .fa-camera::before {
			display: block;
			font-size: 30px;
			line-height: 160px;
		}
		.my-profile #avatar-upload {
			display: block;
			width: 140px;
			height: 140px;
			border-radius: 100%;
			transform: translate(0, 0) scale(1);
			-ms-transform: scale(1); /* IE*/
		}
		.my-profile .avatar-btn-bar {
			text-align: center;
			display: block;
			margin-bottom: 15px;
			border: 1px dotted #ddd;
			padding: 5px;
		}
		.my-profile .avatar-btn-bar .btn {
			margin: 0;
		}
		.my-profile .margin-bottom-30 {
			margin-bottom: 30px !important;
		}
		.my-profile .margin-bottom-15 {
			margin-bottom: 15px !important;
		}
		.my-profile .relative-user {
			list-style: none;
			padding: 0;
		}
		.my-profile .relative-user li {
			display: inline-block;
			padding: 0 10px;
			border-right: 1px solid #d9d9d9;
		}
		.my-profile .relative-user li:last-child {
			border: none;
		}
		.my-profile .relative-user li a {
			text-decoration: none;
		}
		.my-profile .relative-user li a:hover, .my-profile .relative-user li a:focus {
			text-decoration: none;
		}
		.my-profile .relative-user li h5 {
			margin-top: 0 !important;
			margin-right: 0 !important;
			margin-bottom: 0 !important;
			margin-left: 0 !important;
		}
		.my-profile .no-bottom-border {
			border-bottom: none !important;
		}
		.my-profile .nav-tabs > li.active {
			background-color: #b3b3b3 !important;
			border-radius: 0 !important;
		}
		.my-profile .base-info-container form, .my-profile .password-container form {
			padding-top: 2em;
			padding-left: 4em;
			padding-right: 4em;
			padding-bottom: 4em;
		}
		@media (max-width: 768px) {
			.my-profile .base-info-container form, .my-profile .password-container form {
				padding-left: 0;
				padding-right: 0;
			}
			.my-profile .base-info-container form .mdi-hardware-phone-iphone, .my-profile .base-info-container form .fa-envelope, .my-profile .password-container form .fa-envelope, .my-profile .password-container form .fa-envelope {
				display: inline-block !important;
			}
			.my-profile .base-info-container form .text-hidden, .my-profile .password-container form .text-hidden {
				display: none;
			}
			.my-profile .base-info-container form .bind-phone, .my-profile .base-info-container form .email-captcha, .my-profile .password-container form .bind-phone, .my-profile .password-container form .email-captcha {
				padding-left: 0.4em;
				padding-right: 0.4em;
			}
		}
		.my-profile .base-info-container form .mdi-hardware-phone-iphone, .my-profile .base-info-container form .fa-envelope, .my-profile .password-container form .fa-envelope, .my-profile .password-container form .fa-envelope {
			display: none;
		}
		.my-profile .base-info-container .input-group, .my-profile .password-container .input-group {
			padding: 1em;
		}
		.my-profile .base-info-container .input-group-addon, .my-profile .password-container .input-group-addon {
			padding-right: 2em;
			min-width: 7em;
			text-align: right;
		}
		.my-profile .change-phone-container .input-group {
			padding: 1em;
		}
		.my-profile .change-phone-container .input-group-addon {
			padding-right: 2em;
			min-width: 7em;
			text-align: right;
		}
		@media (max-width: 767px) {
			.my-profile .change-phone-container .phone-captcha {
				padding-left: 0.4em;
				padding-right: 0.4em;
			}
			.my-profile .change-phone-container .mdi-content-send {
				display: inline-block !important;
			}
			.my-profile .change-phone-container .text-hidden {
				display: none;
			}
		}
		.my-profile .change-phone-container .mdi-content-send {
			display: none;
		}
		.my-profile .btn-bar {
			padding-right: 1.5em;
			text-align: right;
		}
		.my-profile [class^='fa-'] {
			font-size: 15px;
		}
		.my-profile .gb-hide {
			display: none;
		}
		.my-profile .change-phone-footer {
			padding-left: 2em;
			padding-right: 3em;
			padding-bottom: 2em;
		}
		.my-profile .change-phone-footer .btn-default {
			margin-bottom: 10px !important;
		}
		.my-profile .success-status {
			color: #00b300;
		}
		.my-profile .error-status {
			color: #ff3333;
		}
		.my-profile .success-bar span, .my-profile .error-bar span {
			display: block;
			padding: 0.6em;
			text-align: center;
		}
		.my-profile .success-bar {
			padding: 0 1.3em;
		}
		.my-profile .success-bar span {
			background-color: #00cd00;
			color: #fff;
		}
		.my-profile .error-bar {
			padding: 0 1.3em;
		}
		.my-profile .error-bar span {
			background-color: #ff4d4d;
			color: #fff;
		}

	</style>
</head>
<body>
	<script type="text/javascript">
		$(function(){
			var	uid = global_onlineuser.uid,
				Util = {
					setform: function(formid,data){
						var $form = $('#'+formid),
							$fields = $form.length > 0 ? $form.find('input:visible,textarea'):[],
							i = 0,
							len = 0,
							field_name;

						if(data !== null && $fields.length > 0){
							for(i = 0, len = $fields.length; i < len; i ++){
								field_name = $fields[i].name;
								$($fields[i]).val(data[field_name]).data(field_name,data[field_name]);

								if(field_name === 'mobile'){
									if(data[field_name]!=='' && data[field_name] !== null){
										$('.bind-phone').find('.text-hidden').text('更换手机号').parent().attr('title','更换手机');
									}else{
										//如果是第三方登录，邮箱注册，手机为空，则设置字段可编辑
										$($fields[i]).attr('disabled',false);
									}
								}

								if(field_name === 'mail'){
									if(data[field_name] !== null && data[field_name] !== ''){
										if(data.deleted===0){
											$('.email-captcha').remove();
										}
										$($fields[i]).attr('disabled',true);
									}else{
										//如果是第三方登录，手机注册，邮箱为空，则设置字段可编辑
										$($fields[i]).attr('disabled',false);
									}
								}
							}
						}
					},
					getform: function(formid){
						var $form = $('#'+formid),
						//保存信息的时候不保存email和mobile因为这两项已经在验证的时候保存过了
								$fields = $form.length > 0 ? $form.find('input:visible,textarea'):[],
								result = {},
								i = 0,
								len = 0;

						if($fields.length > 0){
							for(i = 0, len = $fields.length; i < len; i ++){
								result[$fields[i].name] = $.trim($($fields[i]).val());
							}
						}

						return result;
					},
					//清空表单
					clearform: function(formid){
						var $form = $('#'+formid),
								$fields = $form.length > 0 ? $form.find('input,textarea'): [],
								$status = $form.find('.input-group-btn'),
								i = 0,
								len = 0;

						if($status.length > 0){
							$status.html('');
						}
						if($fields.length > 0){
							for(len = $fields.length ; i < len ; i ++){
								$($fields[i]).val('');
							}
						}
					},

					//修改密码校验原始密码
					verifyPassword: function(param,ca_data,callback,tcallback){
						var $field = param.field,
								$status = param.status,
								original_pwd = param.original;

						identityService.getUserInfo(global_onlineuser.uid,{
							callback: function(data){
								if(data !== null && data.password !== ''){
									if(original_pwd == ''){
										$field.validationEngine('showPrompt','请输入原始密码','gb','topLeft',true);
									}else{
										identityService.verifyPwd(original_pwd,{
											callback: function(data){
												if(data){
													$status.html('<span class="fa fa-check success-status"></span>');
													callback !== void 0 ? callback(ca_data) : '';
												}else{
													$status.html('<span class="fa fa-times error-status"></span>');
													$field.validationEngine('showPrompt','密码错误','gb','topLeft',true);
												}
											}
										});
									}
								}else if(data !== null && data.password == ''){
									//如果是第三方登陆账户,无需验证原始密码即可更改密码
									tcallback(param);
								}
								else if(data == null){
									$field.validationEngine('showPrompt','请重新登录','gb','topLeft',true);
								}
							},
							exceptionHandler: function(msg,err){
								$field.validationEngine('showPrompt','请重新登录','gb','topLeft',true);
							}
						});

					}
				};

			//初始化用户信息 base-info-form
			(function(uid){
				if(uid > 0){
					identityService.getUserInfo(uid,{
						callback: function(data){
							if(data !== null){
								Util.setform('base-info-form',data);
								//初始化头像
								var avatar_uri = global_contextPath + '/networks/avatars/'+ (data.avatar === '' ? 'avatar.jpg': data.avatar),
										$avatar = $('.avatar-container img');

								$avatar.attr('src',avatar_uri).data('original',avatar_uri);
								$('.gb-selfintro').text(data.selfintro);
								$('.gb-fullname').text(data.fullname);

							}
						}
					});

					//关注粉丝
					identityService.findFollowAndTagsFanAndTagsSizeByUid(uid,{
						callback: function(data){
							var follower_count = 0,
									fans_count = 0;
							if(data !== null && data.length > 2){
								follower_count = data[0].usertagids,
										fans_count = data[2].usertagids;
							}
							$('.relative-user .followers').text(fans_count);
							$('.relative-user .fans').text(follower_count);
						}
					});
				}else{
					location.href = global_contextPath + '/syslogin.htm';
				}
			})(uid);

			$('#base-info-form').validationEngine('attach',{promptPosition : "topLeft", scroll: false, autoHidePrompt:true, autoHideDelay:2000});
			//保存用户基本信息
			$('#save-profile').click(function(event){
				event.preventDefault();

				var $this = $(this),
					uid = global_onlineuser.uid,
					dto,
					L;
				L = Ladda.create(this);
				L.start();

				if(!$('#base-info-form').validationEngine('validate')){
					L.stop();
					return false;
				}
				if(uid > 0){
					dto = Util.getform('base-info-form');
					dto.uid = uid;
					dto.sitename="";

					$this.attr('disabled',true);
					identityService.findUserByFullname(dto.fullname,{
						callback: function(data){
							if(data !== null && data.uid !== global_onlineuser.uid){
								$('#full-name').validationEngine('showPrompt','此用户名已被占用，请修改其它用户名', 'gb', 'topLeft', true);
								$this.attr('disabled',false);
								L.stop();
							}else{
								identityService.saveUserBaseInfo(dto,{
									callback: function(data){
										if(data > 0){
											$this.attr('disabled',false);
											$('.baseinfo-success-bar').fadeIn();
											setTimeout(function(){
												$('.baseinfo-success-bar').fadeOut();
											},2000);
											L.stop();
											setTimeout(function(){
												window.location.href='reloaduser.htm?url=profile.htm';
											},800);
										}
									},
									exceptionHandler: function(msg,e){
										$this.attr('disabled',false);
										$('.baseinfo-error-bar').fadeIn();
										setTimeout(function(){
											$('.baseinfo-error-bar').fadeOut();
										},2000);
										L.stop();
									}
								});
							}
						}
					});
				}else{

				}
			});



			//取消保存用户基本信息
			$('#cancle-profile').click(function(event){
				event.preventDefault();

				var $form = $('#base-info-form'),
						$fields = $form.length > 0 ? $form.find('input:visible,textarea'): [],
						i = 0,
						len = 0,
						field_name,
						$field;

				if($fields.length > 0){
					for(len = $fields.length ; i < len ; i ++){
						$field = $($fields[i]);
						field_name = $fields[i].name;
						$field.val($field.data(field_name));
					}
				}
			});

			$('#change-password-form').validationEngine('attach',{promptPosition : "topLeft", scroll: false, autoHidePrompt:true, autoHideDelay:2000});
			//校验用户原始密码
			$('#original-pwd').blur(function(){
				var $this = $(this),
						original_pwd = $this.val(),
						$status = $this.next();

				Util.verifyPassword({field: $this,status: $status,original: original_pwd});
			});
			//新密码校验
			$('#new-pwd').blur(function(){
				var $this = $(this),
						original_pwd = $this.val(),
						$status = $this.next();
				if(original_pwd.length >= 6){
					$status.html("<span class='fa fa-check success-status'></span>");
				}else{
					$status.html("<span class='fa fa-times error-status'></span>");
				}
			});
			$('#new-pwd-verify').change(function(){
				var $this = $(this),
						$new_pwd_field = $('#new-pwd'),
						verify_pwd = $this.val(),
						original_pwd = $new_pwd_field.val(),
						$status = $this.next();

				if(verify_pwd.length < 6 || verify_pwd !== original_pwd){
					$status.html("<span class='fa fa-times error-status'></span>");
					$this.validationEngine('showPrompt','两次输入的密码不一致','gb','topLeft',true);
				}else{
					$status.html("<span class='fa fa-check success-status'></span>");
				}
			});

			//保存密码
			$('.change-pwd-btn').click(function(event){
				event.preventDefault();

				var $original_field = $('#original-pwd'),
						$new_field = $('#new-pwd'),
						$verify_field = $('#new-pwd-verify'),
						L = Ladda.create(this),
						$this = $(this);

				L.start();
				$this.attr('disabled',true);
				if(!$('#change-password-form').validationEngine('validate')){
					L.stop();
					return;
				}
				Util.verifyPassword({
					field: $original_field,
					status: $original_field.next(),
					original: $original_field.val(),
					verify_pwd: $verify_field.val(),
					ladda: L,
					target: $this},{
					new_pwd: $new_field.val(),
					verify_pwd: $verify_field.val(),
					verify_field: $verify_field,
					ladda: L,
					target: $this
				},function(param){
					var new_pwd = param.new_pwd,
							verify_pwd = param.verify_pwd,
							$verify_field = param.verify_field,
							L = param.ladda,
							$this = param.target;

					if(new_pwd === verify_pwd){
						identityService.savePassword({password:verify_pwd},{
							callback: function(data){
								if(data>0){
									Util.clearform('change-password-form');
									L.stop();
									$this.attr('disabled',false);
									$('.pwd-success-bar').fadeIn();
									setTimeout(function(){
										$('.pwd-success-bar').fadeOut();
									},2000);
								}
							},
							exceptionHandler: function(msg,err){
								$('.pwd-error-bar').fadeIn();
								setTimeout(function(){
									$('.pwd-error-bar').fadeOut();
								},2000);
							}
						});
					}else{
						L.stop();
						$verify_field.validationEngine('showPrompt','两次输入的密码不一致','gb','topLeft',true);
					}
				},function(tparam){
					var new_third_password = tparam.verify_pwd,
							L = tparam.ladda,
							$this = tparam.target;
					identityService.savePassword({password:new_third_password},{
						callback: function(data){
							if(data>0){
								Util.clearform('change-password-form');
								L.stop();
								$this.attr('disabled',false);
								$('.pwd-success-bar').fadeIn();
								setTimeout(function(){
									$('.pwd-success-bar').fadeOut();
								},2000);
							}
						},
						exceptionHandler: function(msg,err){
							$('.pwd-error-bar').fadeIn();
							setTimeout(function(){
								$('.pwd-error-bar').fadeOut();
							},2000);
						}
					});
				});
			});

			//取消修改用户密码
			$('.cancel-pwd-btn').click(function(event){
				event.preventDefault();

				Util.clearform('change-password-form');
			});

			//修改用户头像
			$('#change-avatar-form').validationEngine('attach',{promptPosition : "topLeft", scroll: false, autoHidePrompt:true, autoHideDelay:2000});
			var options = {
				url: global_contextPath + '/ajaxfileuploadservlet.htm',
				dataType: 'json',
				multipart: true,
				sequentialUploads: true,
				add: function (e, data) {
					var goUpload = true;
					var uploadFile = data.files[0];
					if (!(/\.(gif|jpg|jpeg|tiff|png)$/i).test(uploadFile.name)) {
						//$('#avatar-upload').validationEngine('showPrompt','请上传图片格式文件（gif|jpg|jpeg|tiff|png','gb','topLeft',true);
						alert('请上传图片格式文件（gif|jpg|jpeg|tiff|png）');
						goUpload = false;
					}

					if (uploadFile.size > 2000000) { // 2mb
						//$('#avatar-upload').validationEngine('showPrompt','上传图片过大（2M以内）','gb','topLeft',true);
						alert('上传图片过大（2M以内）');
						goUpload = false;
					}

					if (goUpload == true) {
						data.submit();
					}
				},
				formData : [
					{
						name: 'type',
						value: 'avatar'
					}
				],
				start: function (e, data) {

				}
			};

			$('#avatar-upload').fileupload($.extend(options,{
				done: function (e, data) {
					var img_src = global_contextPath + data.result.url,
							msg = '';
					if(data.result.url){
						$('.avatar-container img').attr('src',img_src).data('avatar-url',data.result.srvname);
						$('.avatar-btn-bar').fadeIn();
					}else{
						msg = '错误信息：'+data.result.Error;
						//$('#avatar-upload').validationEngine('showPrompt','ddddd','gb','topLeft',true);
						alert('错误信息：' + msg);
					}
				}
			}));

			//保存用户头像
			$('.save-avatar-btn').click(function(event){
				event.preventDefault();

				var avatar_uri = $('.avatar-container img').data('avatar-url');

				if(avatar_uri !== '' && avatar_uri !== void 0){
					identityService.saveUserAvatar({avatar: avatar_uri},{
						callback: function(data){
							if(data > 0 ){
								setTimeout(function(){
									window.location.href='reloaduser.htm?url=profile.htm';
								},800);
							}
						}
					});
				}else{
					$('#avatar-upload').validationEngine('showPrompt','请上传用户头像','gb','topLeft',true);
				}
			});

			//放弃修改头像
			$('.cancle-avatar-btn').click(function(event){
				event.preventDefault();

				var $img = $('.avatar-container img');

				$img.attr('src',$img.data('original'));
				$('.avatar-btn-bar').fadeOut();
			});

			//发送邮件
			$('.email-captcha').click(function(event){
				event.preventDefault();

				var emial = $.trim($('#email').val()),
						$this = $(this),
						count = $this.data('count'),
						timer,
						original_text = $this.text();
				$this.attr('disabled',true);

				identityService.updateSendEmailCountByMail(emial,{
					callback: function(data){
						if(data){
							$this.removeClass('btn-primary')
									.addClass('btn-success')
									.html('<span class="fa fa-check"> '+count+'</span>秒后可以重新发送');
							$(document).width() < 768 ? $this.html('<span class="fa fa-check"> '+count+'</span>') : '';
							if(timer){
								clearInterval(timer);
							}else{
								timer = setInterval(function(){
									if(count > 0){
										$('.fa-check',$this).html(--count);
									}else{
										clearInterval(timer);
										$this.removeClass('btn-success')
												.addClass('btn-primary')
												.html(original_text)
												.attr('disabled',false);
									}
								},1000);
							}
						}else{
							$this.removeClass('btn-primary btn-success')
									.addClass('btn-danger')
									.html('<span class="fa fa-times"> 今日已超过可发送次数</span>')
									.attr('disabled',true);
							$(document).width() < 768 ? $this.html('<span class="fa fa-times"></span>') : '';
						}
					},
					exceptionHandler: function(){
						$this.removeClass('btn-primary btn-success')
								.addClass('btn-danger')
								.html('<span class="fa fa-times"> 发送失败，刷新页面重新发送</span>')
								.attr('disabled',false);
						$(document).width() < 768 ? $this.html('<span class="fa fa-times"></span>') : '';
					}
				});
			});
		});
	</script>

	<div class="container my-profile">
		<div class="row">
			<%--左侧栏--%>
			<div class="col-md-3">
				<%--左侧栏头部--%>
				<div class="panel panel-primary margin-bottom-30">
					<div class="panel-heading profile-bg"></div>
					<div class="panel-body text-center">
						<form role="form" id="change-avatar-form">
							<div class="avatar-wrapper">
								<div class="avatar-container">
									<img src="http://www.gbtags.com/gb/laitu/200x200" class="avatar"/>
								</div>
								<div>
									<span class="btn fileinput-button avatar-upload-layer btn-lg">
										<span class="fa fa-camera fa-lg"></span>
										<input id="avatar-upload" type="file" name="files[]" multiple>
									</span>
								</div>
							</div>
							<div class="avatar-btn-bar gb-hide">
								<a href="#" class="btn btn-success btn-sm save-avatar-btn">保存</a>
								<a href="#" class="btn btn-default btn-sm cancle-avatar-btn">放弃</a>
							</div>
						</form>

						<h5 class="panel-title margin-bottom-15 gb-fullname"></h5>
						<p class="margin-bottom-30 gb-selfintro"></p>
						<ul class="relative-user">

						</ul>
					</div>
				</div>
				<!--左侧栏中部-->
			</div>
			<%--内容栏--%>
			<div class="col-md-9">
				<div class="panel panel-primary margin-bottom-30">
					<div class="panel-heading text-center profile-headding">
						<ul class="nav nav-tabs btn-group" role="group">
							<li class="btn btn-primary no-padding active"><a href="#my-baseinfo" data-toggle="tab">基本资料</a></li>
							<li class="btn btn-primary no-padding"><a href="#my-password" role="button" data-toggle="tab">修改密码</a></li>
						</ul>
					</div>
					<div class="tab-content">
						<div class="panel-body tab-pane base-info-container active" id="my-baseinfo">
							<form role="form" id="base-info-form">
								<div class="input-group">
									<span class="input-group-addon">用户名:</span>
									<input type="text" id="full-name" name="fullname" class="form-control validate[required,custom[onlyChineseLetterNumber],minSize[2],maxSize[16]]" placeholder="用户昵称">
								</div>
								<div class="input-group">
									<span class="input-group-addon">专业签名:</span>
									<input type="text" id="signature" name="signature" class="form-control validate[required,minSize[1],maxSize[20]]" placeholder="专业签名">
								</div>
								<div class="input-group">
									<span class="input-group-addon">真实姓名:</span>
									<input type="text" id="real-name" name="truename" class="form-control validate[maxSize[20]]" placeholder="真实姓名">
								</div>
								<div class="input-group">
									<span class="input-group-addon">邮箱:</span>
									<input type="text" id="email" name="mail" class="form-control validate[custom[email]" disabled="disabled" placeholder="邮箱">
                                <span class="input-group-btn">
                                    <a href="javascript:;" class="btn btn-primary btn-flat email-captcha" role="button" data-count="300">
										<span class="text-hidden">验证邮箱</span>
										<span class=" fa fa-envelope"></span>
									</a>
                                </span>
								</div>
								<div class="input-group">
									<span class="input-group-addon">微信:</span>
									<input type="text" id="wechat" name="wechat" class="form-control validate[maxSize[20]]" placeholder="微信">
								</div>
								<div class="input-group">
									<span class="input-group-addon">QQ:</span>
									<input type="text" id="qq-no" name="qq" class="form-control validate[maxSize[20]]" placeholder="QQ">
								</div>
								<div class="input-group">
									<span class="input-group-addon">微博:</span>
									<input type="text" id="weibo" name="weibo" class="form-control validate[custom[url]]" placeholder="http://weibo.com/u/yourweiboID">
								</div>
								<div class="input-group">
									<span class="input-group-addon">真实地址:</span>
									<input type="text" id="address" name="address" class="form-control validate[maxSize[100]]" placeholder="地址">
								</div>
								<div class="input-group">
									<span class="input-group-addon">毕业学校:</span>
									<input type="text" id="graduate-school" name="university" class="form-control validate[maxSize[20]]" placeholder="毕业学校">
								</div>
								<div class="input-group">
									<span class="input-group-addon">就职公司:</span>
									<input type="text" id="company" name="company" class="form-control validate[maxSize[20]]" placeholder="就职公司">
								</div>
								<div class="input-group" id="mypage" style="display: none">
									<span class="input-group-addon">个性主页</span>
									<input type="text" id="site-name" name="sitename" class="form-control validate[maxSize[20],custom[onlyLetterNumber]]"  value="个人主页">
								</div>
								<div class="input-group">
									<span class="input-group-addon">个人网站:</span>
									<input type="text" id="my-website" name="website" class="form-control validate[maxSize[100],custom[url]]" placeholder="http://www.your.site.name.com">
								</div>
								<div class="input-group">
									<span class="input-group-addon">个人介绍:</span>
									<textarea type="text" id="my-intro" rows="3" name="selfintro" class="form-control validate[required,maxSize[200]]" placeholder="来点自我介绍，让大家更好的认识你"></textarea>
								</div>
								<div class="success-bar gb-hide baseinfo-success-bar">
									<span class="fa fa-check"> 保存成功</span>
								</div>
								<div class="error-bar gb-hide baseinfo-error-bar">
									<span class="fa fa-times"> 保存失败，请刷新页面重试</span>
								</div>
								<div class="row btn-bar">
									<button class="btn btn-success ladda-button" id="save-profile" data-style="expand-right">
										<span class="ladda-label">保存修改</span>
									</button>
									<a href="javascript:;" class="btn btn-default" id="cancle-profile">取消</a>
								</div>
							</form>
						</div>
						<div class="panel-body tab-pane password-container" id="my-password">
							<div class="col-md-12">
								<%--<div class="alert alert-warning alert-dismissible">--%>
								<%--<button type="button" class="close" data-dismiss="alert">×</button>--%>
								<%--<span class="mdi-social-notifications-on"> 提示</span>--%>
								<%--<span> 第三方登录用户，首次设置密码，原始密码为空</span>--%>
								<%--</div>--%>
							</div>
							<%--保存关于秘密的修改--%>
							<form role="form" id="change-password-form">
								<div class="input-group">
									<span class="input-group-addon">原始密码:</span>
									<input type="password" id="original-pwd" name="oripwd" class="form-control validate[minSize[6]" placeholder="初次设置密码,原始密码留空">
									<span class="input-group-btn"></span>
								</div>
								<div class="input-group">
									<span class="input-group-addon">新密码:</span>
									<input type="password" id="new-pwd" name="newpwd" class="form-control validate[required,minSize[6]]" placeholder="至少输入6位密码">
									<span class="input-group-btn"></span>
								</div>
								<div class="input-group">
									<span class="input-group-addon">确认新密码:</span>
									<input type="password" id="new-pwd-verify" name="newpwdverify" class="form-control validate[required,minSize[6]]" placeholder="再次输入新密码">
									<span class="input-group-btn"></span>
								</div>
								<div class="success-bar gb-hide pwd-success-bar">
									<span class="fa fa-check"> 保存成功</span>
								</div>
								<div class="error-bar gb-hide pwd-error-bar">
									<span class="fa fa-times"> 保存失败，请刷新页面重试</span>
								</div>
								<div class="row btn-bar">
									<button class="btn btn-success ladda-button change-pwd-btn" data-style="expand-right">
										<span class="ladda-label">保存修改</span>
									</button>
									<a class="btn btn-default cancel-pwd-btn">取消</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>