<%@ page contentType="text/html;charset=UTF-8" language="java" %>  
<%@ include file="../include/inc_session.jsp"%>
<%@ include file="../include/inc_common.jsp"%>

<html>
<head>
	<title>Friend</title>

</head>

<body>

	<div class="container">
		<div class="row">
			<h3 class="col-md-12 col-xs-12">
				<button id="addFriend" class="btn btn-default">执行新增记录命令</button>
				<button id="updateFriend" class="btn btn-default">执行更新记录命令</button>
				<button id="updateRefresh" class="btn btn-default">更新后刷新页面</button>
			</h3>
		</div>
		
		<div class="row">
			
			<div id="friends">	</div>
			
		</div>
	</div>
	
	<script type="text/javascript">
	function refresh(){
		var $friends=$('#friends');
		var $dto={};
		
		identityService.findAllFriendS(
			{callback:function(data){
							var content = '';
							for(var i=0;i<data.length;i++){
								
								content += '<div class="col-md-2 col-xs-6">'
										+ '<img class="img-responsive" src="/initprj/networks/avatars" ' + data[i].avatar + '"/>'
										+ '<div class="igwell text-center" style="padding:3px;">' + data[i].truename + '</div>'
										+ '</div>';
							}
							$friends.empty();
							$friends.append(content);
						}
			}
		);
	}
		
		
		$('#addFriend').click(function(){
								var dto={uid:1, truename: 'add1', avatar: 'avatar_3.jpg', sex:1};
								identityService.addFriend(
									dto,
									{
										callback:function(data){
											if(data>0){
												alert('add success');
											}
										},
										exception:function(){
											alert('添加失败')
										}
									}
								);
							}				
					  );
		
		
		$('#updateFriend').click(function(){
										var dto={fid:1, uid:1, truename: 'change', avatar: 'avatar_3.jpg', sex:1};
										identityService.updateFriendByUid(
											dto,
											{
												callback:function(data){
													if(data>0){
														alert('update success');
													}
												},
												exception:function(){
													alert('修改失败')
												}
											}
										);
									}				
							  );
		
		$('#updateRefresh').click(function(){
									var dto={fid:1, uid:20, truename: 'second-change', avatar: 'avatar_3.jpg', sex:2};
									identityService.updateAndRefresh(
										dto,
										{
											callback:function(data){
												if(data!=null){
													alert('update success');
													refresh();
												}
											},
											exception:function(){
												alert('修改失败')
											}
										}
									);
								}				
						  );
		
		$(refresh());
		
	</script>

</body>
</html>


