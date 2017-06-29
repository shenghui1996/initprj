<%@ page contentType="text/html;charset=UTF-8"%>  

<div id="mobilecontent"></div>

<button type="button" class="btn btn-success ladda-button btn-block" id="showmorecontent" title="显示更多" data-style="expand-right">
	<span class="ladda-label">
		<span class="mdi-navigation-refresh"></span> &nbsp; 阅读更多
	</span>
</button>
		
<script id="data-tmpl-blogview" type="text/x-handlebars-template">
	{{#each this}}
		<article class="igwell">
		
			<div class="row">	
								
        		<div class="col-xs-12">	
					
	        		<h4 class="postlisttitle"> 
	        			<a href="<%= contextPath %>/mobileshare/{{pid}}.htm">{{title}}</a>
	        		</h4>
	        		
	        		标签：<!--{{{functionTagName this}}}--> {{{tagList this}}}
	        		
	        		<div class="text-bold">
	        			{{shortenPostBody body}} 
	        		</div>
	        		
	        		<div class="mar15">
	        			<a class="small text-lighter" href="<%= contextPath %>/mobileshare/{{pid}}.htm">更多内容...</a>
	        		</div>
	        		
				</div>
				<!--
				<div class="col-xs-2">
				
					<a href="<%= contextPath %>/mobilemypost/{{uid}}.htm" class="pull-right">
						{{#if avatar}}
							<img class="img-circle" data-fullname="{{fullname}}" data-uid="{{uid}}" width="50" src="<%= contextPath %>/networks/avatars/80x80{{avatar}}" valign="absmiddle">
	  					{{else}}
	    					<img class="img-circle" data-fullname="{{fullname}}" data-uid="{{uid}}" width="50" src="<%= contextPath %>/networks/avatars/80x80avatar.jpg" valign="absmiddle">
						{{/if}}
						<div class="text-center text-avatar" title="极客达人">
							<i class="{{roleClass roleid}} small"></i>&nbsp;{{fullname}}
						</div>							
					</a>
				</div>	
				//-->	
			</div>		
			
			<div class="row">
				<div class="col-xs-12 mar15">	

					<div class="pull-left">
						
						
							<a title="收藏数" class="iconoptions" data-placement="bottom">
								
								<i class="mdi-action-bookmark"></i>
								&nbsp;{{favcount}}
								
							</a>
							
							<a title="留言量" class="iconoptions" data-placement="bottom">
								
								<i class="mdi-action-question-answer"></i>
								&nbsp;{{commentcount}}
								
							</a>
							
							<a title="阅读量" class="iconoptions" data-placement="bottom">
								
								<i class="mdi-action-visibility"></i>
								&nbsp;{{viewcount}}
								
							</a>
							
							<%if(onlineuser.getRoleid()<0){%>
							<a style="color:orange;" href="<%=contextPath%>/editpost.htm?postid={{pid}}">
								编辑
							</a>
							<%}%>
	
					</div>
					
					<!--
					<div class="pull-right text-lighter small">
						{{fromNow creationdate}}
					</div>
					//-->
				</div>
			</div>
		</article>
	{{/each}}
</script>