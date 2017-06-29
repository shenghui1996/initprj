$(function(){		
	if($("#data-notification-tmpl").length&&global_onlineuser.uid){
		var pagesize=15, page=0, dto = {isread:0};
		identityService.findAllUnReadActivities(dto,{
			callback:function(data){
				
				$.each(data, function (i, item) {
					item.creationdate = Date.parse(item.creationdate);
				});
				
		        var template = Handlebars.compile($("#data-notification-tmpl").html());
		        var htmlcontent = template(data);
		        $('#noticontainer').append( htmlcontent );	       
				if(data.length>0){
					
				}else{
					$('#noticontainer').append('<li style="list-style:none;padding:8px;">没有未读消息</li>');
				}
				$('.loading').addClass('hidden');			
			},
			exceptionHandler:function(msg, ex){
				var errmsg = new GBMessage({text:'系统出错，请稍后重试...',id:'msgid'});
   				errmsg.show();
   				$.gberror(msg);
   				$('.loading').addClass('hidden');		
			}
		});
		/*
			自动标记已读
		*/
		identityService.saveReadActivity({
			callback:function(data){
				
			}
		});
		
		$('#showmorenoti').click(function(){
			var hdto = {isread:1,start:page*pagesize,offset:pagesize,userid:global_onlineuser.uid};
			var l = Ladda.create(this);
 			l.start();
 			$('#hisloading').removeClass("hidden");
			identityService.findAllActivitiesInPageByUid(hdto,{
				callback:function(data){
					$.each(data, function (i, item) {
						item.creationdate = Date.parse(item.creationdate);
					});
					
			        var template = Handlebars.compile($("#data-notification-tmpl").html());
			        var htmlcontent = template(data);
			        $('#noticontainer_history').append( htmlcontent );	       
					if(data.length>0){
						page++;
					}else{
						$('#showmorenoti').addClass('hidden');
						$('#noticontainer_history').append('<li style="list-style:none;padding:8px;">没有更多历史消息</li>');
					}
					l.stop();	
					$('#hisloading').addClass("hidden");			
				},
				exceptionHandler:function(msg, ex){
					var errmsg = new GBMessage({text:'系统出错，请稍后重试...',id:'msgid'});
	   				errmsg.show();
	   				l.stop();
	   				$.gberror(msg);
	   				$('.hisloading').addClass("hidden");		
				}
			});
		});
		$('#showmorenoti').trigger('click');
	}	
});