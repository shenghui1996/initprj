<%@ page contentType="text/html;charset=UTF-8"%> 

<div id="relatedpostwrapper" class="igwell" style="margin-top:0px;">
	<h4 style="margin-top:0;margin-bottom:10px;">热点资讯</h4>
	<ul id="popularpostlist" style="list-style:none;padding-left:0;"></ul>
</div>

<script>
	identityService.findMostPopularPostWithinOneMonth({'start':0, 'offset':10},{
			callback:function(data){
				var output=[], $popular=$('#popularpostlist');
				$.each(data, function (i, shot) {
					var tmpdemoimg = 'placeholder.png';
					if(shot.demoimg!==''){
						tmpdemoimg = shot.demoimg;
					}				
					output.push('<li style="margin-bottom:12px;"><a href="' + global_contextPath + '/mobileshare/' + shot.pid + '.htm">' + shot.title + '</a>' 
						+ '</li>');
	
				
					
				});
				$popular.append(output.join(''));
				$popular.find('.loading').hide();
			}
	});
</script>

<div class="igwell" style="margin-top:0px;">
	<a href="#">
		<img src="http://gbtags.com/gb/laitu/500x300" class="img-responsive" />
	</a>
</div>
