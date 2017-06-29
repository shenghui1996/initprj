/*
 * 登录模块
 */
 
var gbmobile_gblogon = {
	logon: function(){
		$("#loginform").validationEngine('attach',{promptPosition : "topLeft", scroll: false, autoHidePrompt:true, autoHideDelay:2000});
		
		$("#password").keypress(function(event) {
		  if ( event.which == 13 ) {
		     event.preventDefault();
			 $("#logonbtn").trigger('click');	
		  }
		});
		
		$("#logonbtn").click(function(check){
			if(!$("#loginform").validationEngine('validate')){
				return;
			}
			var mailval=$('#email').val();
			var pwdval=$(':password').val()||$('#password').val();//fix the bug login failed for ie6/7/8
			
	 		var l = Ladda.create(this);
	 		l.start();

			identityService.PasswordEncryption(pwdval,{
					async:false,
					callback:function(data){
					   pwdval=data;
					}

			});		
			$.getJSON('ajaxlogin.htm?fixie=' + new Date().getMilliseconds(), {cache:false,mail:mailval,password:pwdval}, function(data) {
				if(data.uid!=="0"){
					/* save cookie for autologin */
					if($('#autolog').data('autosave')){
						$.cookie("gb.mail",mailval,{expires: 30, path: '/'});
						$.cookie("gb.password",pwdval,{expires: 30, path: '/'});
					}
					
					var hash = window.location.hash.substring(1);
					
					if(hash.length>0){
						window.location.href= hash;
					}else{
						window.location.href= global_contextPath + '/index.htm';
					}
					
					$('#logalert').removeClass("alert-danger").addClass("alert-success").removeClass('hidden')
						.find('span').first().addClass('glyphicon-ok-sign').removeClass('glyphicon-exclamation-sign');
					$('#loginfo').html('请稍候，登录中...');
				}else{
					$('#logalert').addClass("alert-danger").removeClass("alert-success").removeClass('hidden')
					.find('span').first().addClass('glyphicon-exclamation-sign').removeClass('glyphicon-ok-sign');
				}
				l.stop();
			});
			
			check.preventDefault();//此处阻止提交表单  
		});
		
		$('#autolog').click(function(){
			var $this = $(this), autologflag = $this.data('autosave');
			if(autologflag){
				$this.data('autosave',0);
			}else{
				$this.data('autosave',1);
			}
			$this.toggleClass('btn-default').toggleClass('btn-warning').find('.glyphicon').toggleClass('glyphicon-check').toggleClass('glyphicon-unchecked');
		});
		
		if($.cookie("gb.mail")&&$.cookie("gb.password")){
			$('#autolog').removeClass('btn-default').addClass('btn-warning');
		};
	}
}

$(function(){
	gbmobile_gblogon.logon();
});
