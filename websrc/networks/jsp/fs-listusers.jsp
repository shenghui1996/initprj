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
						if(data.length>0){
					        var template = Handlebars.compile($("#data-tmpl").html());
					        var htmlcontent = template(data);
	
					        
							var $newEls = $(htmlcontent);
		       				$users.append($newEls); 
					         
							$('#morebutton').text('更多用户');
						}else{
							$('#morebutton').hide();
							$('#nomorewrapper').html('<div class="alert alert-warning textcenter">没有可显示的用户了</div>');
						}
					}
				});
				
				page++;

			});
			
	
            //修改角色确认按钮
			$("#btn-confirm").click(function(e){
				e.preventDefault();
				var uid = $('#uid').val(),
					roleid = $('input[name="roleid"]:checked').val(),
					utidlist = [];

				$('#btn-confirm:contains("确认")').prop("disabled", true);
				identityService.saveRoleAdminForAdmin(uid, roleid, utidlist,{
					callback:function(data){
						$('#btn-confirm:contains("确认")').text('已保存');

						$('button.roleadmin').each(function(i, item){
							if($(item).data('uid') === parseInt(uid, 10)){
								$(item).data('roleid', roleid);
							}
						});

						setTimeout(function(){
							$('#btn-confirm:contains("已保存")').prop("disabled", false).text("确认");
							$(".role-modal").modal('hide');

						},"1000");


					}
				});

			});
			
		
			
			$('#morebutton').trigger('click');		
			
			$('#users').on('click','.roleadmin',function(e){
				var uid = $(this).data('uid'),roleid = $(this).data('roleid');

			    var $roleid = $('input[name="roleid"]');
		    	if(roleid === 0){
		        	$roleid.filter('[value=0]').prop('checked', true);
		        }else if(roleid === 1){
		        	$roleid.filter('[value=1]').prop('checked', true);
		        }else if(roleid === -2){
		        	$roleid.filter('[value=-2]').prop('checked', true);
		        }
				
				$('#uid').val(uid);
			});
			
			$('#role-usertags').on('click','.usertag',function(e){
				var $this = $(this);
				$this.toggleClass('btn-primary');
				
				if($this.data('modify')){
					$this.data('modify', 0);
				}else{
					$this.data('modify', 1);
				}
				
				e.preventDefault();
			});
			
			$('#users').on('click','.morebtn4detail',function(e){
				$(this).parent().find('.moreinfo').toggleClass('hidden');
			});
		});

		Handlebars.registerHelper('fromNow', function(creationdate) {
			var creationdate = moment(creationdate),
				now = moment();
			if(now.diff(creationdate, 'days')>=1){
				return creationdate.calendar(); 
			}else{
				return creationdate.fromNow();
			}
		});
	</script>		
	<script id="data-tmpl" type="text/x-handlebars-template">
		{{#each this}}
		<article class="col-md-2">
			<div class="igwell">
				<a target="_blank" href="<%=contextPath%>/mobilemypost/{{uid}}.htm">
					{{#if avatar}}
						<img id="avatar" class="img-responsive" src="<%=contextPath%>/networks/avatars/{{avatar}}">
					{{else}}
						<img id="avatar" class="img-responsive" src="<%=contextPath%>/networks/avatars/avatar.jpg">
					{{/if}}
				</a>

				<h5>
					<a target="_blank" href="<%=contextPath%>/mypost/{{uid}}.htm">{{{fullname}}}</a>
					{{#if roleid}}
						<span class="mdi-daren small" title="社区达人"></span>
					{{/if}}
				</h1>
				
				<p>{{university}}</p>
				<p>{{company}}</p>	
				<p>{{mail}}</p>	
				<button class="btn btn-xs btn-block btn-primary morebtn4detail">详细信息</button>	
				<div class="hidden moreinfo">				
					<p>简介：{{{selfintro}}}</p>
					<p>注册日期：{{{fromNow creationdate}}}</p>
					<p>登录日期：{{{fromNow lastlogindate}}}</p>
					<p>个人金币：{{totalcoin}}币</p>
					<p>用户状态：
						{{#compare deleted 0 operator="=="}}
							<span style="font-size:12px;color:green;">正常用户</span>
						{{/compare}}
						{{#compare deleted 1 operator="=="}}
							<span style="font-size:12px;color:red;">已删除用户</span>
						{{/compare}}
						{{#compare deleted -1 operator="=="}}
							<span style="font-size:12px;color:brown;">验证中用户</span>
						{{/compare}}
					</p>
					<p>
						<button type="button" class="btn btn-xs btn-block btn-warning roleadmin"  data-uid="{{uid}}" data-roleid="{{roleid}}" data-toggle="modal" data-target=".role-modal">
							角色管理
						</button>
						<!--
						{{#compare totalcoin 0 operator=">"}}
						<a class="btn btn-primary" href="<%=contextPath%>/admincoin.htm?uid={{uid}}" role="button">扣除金币</a>
						{{/compare}}
						//-->
					</p>
				</div>
			</div>
		</article>
		{{/each}}
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
														<%--模态框--%>
														<div class="modal fade role-modal"  tabindex="-1" role="dialog" aria-labelledby="roleModalLabel">
															<div class="modal-dialog" role="document">
																<div class="modal-content">
																	<div class="modal-header" id="rolemanage-dialog">
																		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																		<h3 class="modal-title" id="myModalLabel">角色管理</h3>
																	</div>
																	<div class="modal-body">
																		<form class="" id="editform">
																			<h4>用户权限</h4>
																			<div class="radio-inline">
																				<label>
																					<input  name="roleid" type="radio" value="0" checked>
																					用户
																				</label>
																			</div>
																			<div class="radio-inline">
																				<label>
																					<input name="roleid" type="radio" value="1">
																					达人
																				</label>
																			</div>
																			<div class="radio-inline">
																				<label>
																					<input name="roleid" type="radio" value="-2" >管理员
																				</label>
																			</div>
																			<div class="radio-inline">
																				<label>
																					<input name="roleid" type="radio" value="-1">超级管理员
																				</label>
																			</div>
																			<hr/>
																			<input type="hidden" id="uid" />
																		</form>
																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-primary" id="btn-confirm">确认</button>
																		<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
																	</div>
																</div>
															</div>
														</div>
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