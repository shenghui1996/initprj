<%@ page contentType="text/html;charset=UTF-8" language="java" %>  
<%@ include file="../include/inc_session.jsp"%>
<%@ include file="../include/inc_common.jsp"%>

<html>
<head>
	<script type="text/javascript">
		$(function(){
			var pagesize=6, page = 0;
			$('#morebutton').click(function(event){
				
				
				var userdto = {start: page*pagesize,offset:pagesize},
					$users = $('#users');
				
				identityService.findAllUsersByPageForAdmin(userdto,{
					callback:function(data){
						$('#showmoreloading').hide();
						
						var content = '';
						if(data.length>0){
							for(var i=0;i<data.length;i++){
								if(data[i].avatar == ''){
									data[i].avatar = 'avatar.jpg';
								}
								content += '<article class="col-md-2">';
								content += '<div class="text-center igwell">' 
												+ '<img id="avatar" class="img-responsive" src="<%=contextPath%>/networks/avatars/' + data[i].avatar + '"/>'
												+ data[i].fullname
										 + '</div>';
								content += '</article>';
							}
					        
					        $users.append(content); 
							$('#morebutton').text('更多用户');
						}else{
							$('#morebutton').hide();
							$('#nomorewrapper').html('<div class="alert alert-warning textcenter">没有可显示的用户了</div>');
						}
					}
				});
				
				page++;

			});
			
			$('#morebutton').trigger('click');		
			
			$('#users').on('click','.morebtn4detail',function(e){
				$(this).parent().find('.moreinfo').toggleClass('hidden');
			});
		});
	</script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div id="tagcontainer">


						<%--每个用户--%>
							<div class="row">
								<div class="col-md-12 col-sm-12 col-xs-12">
									<section id="conent" class="fullwidth">
										<section>
											<div class="row">
												<div class="col-md-12">
													<div id="showmoreloading"></div>
												</div>
											</div>
											
											<div class="row">
												<div class="col-md-12 col-sm-12 col-xs-12">
													<div class="row">
														<section id="users"  style="width:100%">

														</section>
													</div>
												</div>
											</div>
										</section>
										<div id="nomorewrapper"></div>
										<hr class="divider">
										<section>
											<div style="text-align:center;">
												<span class="iconbefore">
													<a href="#" class="gbbutton btn btn-primary btn-block" id="morebutton">
														更多用户
													</a>
												</span>
											</div>
										</section>
									</section>
								</div>
							</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>