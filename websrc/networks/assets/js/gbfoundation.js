/*
 * 基础模块：foundation
 */

/* ---------------------------------------------------------------------- */
/*   Material Design：实现效果
/* ---------------------------------------------------------------------- */
$(function() {
    $.material.init();//Material Design 基础效果
});

/* ---------------------------------------------------------------------- */
/*   辅助方法：控制台Log信息
/* ---------------------------------------------------------------------- */
jQuery.gblog=function(a){if(window.console){console.log(a);}else{}};
jQuery.gberror=function(a){if(window.console){console.error(a);}else{}};

/* ---------------------------------------------------------------------- */
/*   辅助方法：DWR统一出错信息
/* ---------------------------------------------------------------------- */
function handleThisCallsErrors(errorString, exception) { 
   alert('网站无法完成请求，请刷新重试');
   $.gberror('内部错误：' + errorString);
} 
dwr.engine.setErrorHandler(handleThisCallsErrors);

String.prototype.trunc = 
      function(n){
          return this.substr(0,n-1)+(this.length>n?'...':'');
      };

/*
 * moment.js显示日期时间格式
 */		
function displaydate(date){
		var mnow = moment(),
			mdate = moment(date);

	if(mnow.diff(mdate, 'days')>=1){
		return mdate.format("YYYY年MM月D日 HH:mm:ss");
	}else{
		return mdate.fromNow();
	}
}

/* ---------------------------------------------------------------------- */
/* 文字中替换文字为URL地址 
/* ---------------------------------------------------------------------- */
function replacelinks(textlinks) {
    var exp = /(\b(https?|ftp):\/\/[-A-Z0-9+\u0026@#\/%?=~_|!:,.;]*[-A-Z0-9+\u0026@#\/%=~_|])/gi,
        lt = '\u003c',
        gt = '\u003e';
        return textlinks.replace(exp, lt + 'a target="_blank" class="biggest" href=\'$1\'' + gt + '$1' + lt + '/a' + gt);
}

/* ---------------------------------------------------------------------- */
/*    发布页面的pub/sub模式
/* ---------------------------------------------------------------------- */
// 发布接收Pub/sub模型
(function( $ ) {
	var o = $( {} );
	$.each({
		trigger: 'publish',
		on: 'subscribe',
		off: 'unsubscribe'
	}, function( key, val ) {
		jQuery[val] = function() {
			o[key].apply(o, arguments);
		};
	});
})( jQuery );

/* ---------------------------------------------------------------------- */
/*    所有瀑布流页面的全部共享代码：explore，user，mypost，site 等等
/* ---------------------------------------------------------------------- */
/* 关注分享内容模块 */
$(function(){	
 	Handlebars.registerHelper('roleClass', function(roleid) {
		if(roleid!==0){
			return 'mdi-daren';
		}else{
			return '';
		}
    });		
		
 	Handlebars.registerHelper('demoLinks', function(object) {
        var demolinks = object.demourl.split('#'),
            demolinksstr = $('<div></div>');
           
        for(var a=0;a<demolinks.length;a++){
            if(demolinks[a]!==null&&demolinks[a]!==''){
                if(demolinks[a]!==null&&demolinks[a].lastIndexOf('zip')<0){
                	var zipfileandfilename = demolinks[a].substring(demolinks[a].lastIndexOf('uploads/')+8);
                	 filename = zipfileandfilename.substring(zipfileandfilename.indexOf('/')+1);
                	 filename = filename.replace(/\//g, "|");
                	 zipfile = zipfileandfilename.substring(0, zipfileandfilename.indexOf('/'));
                	
                	if(zipfile === ''){
                 		demolinksstr.append('<li><a class="iconbefore gbdemobutton ui-state-hover" target="_blank" href="' + global_contextPath + '/demoviewer/' + object.pid + '/empty/' + filename + '.htm">在线演示'  + a + '</a><li>&nbsp;');
                	}else{
                		demolinksstr.append('<li><a class="iconbefore gbdemobutton ui-state-hover" target="_blank" href="' + global_contextPath + '/demoviewer/' + object.pid + '/' + zipfile + '/' + filename + '.htm">在线演示'  + a + '</a><li>&nbsp;');
                	}
                }else{
                    demolinksstr.append('<li><a class="iconbefore gbdownloadbutton ui-state-hover" target="_blank" href="' + global_contextPath + '/ajaxdownload.htm?filename=' + demolinks[a].substring(demolinks[a].lastIndexOf('/')+1) + '"><span class="ui-icon ui-icon-circle-arrow-s"></span>本地下载</a><li>&nbsp;');
                }
            }
        }

        return demolinksstr.html();
    });
    
	//模板辅助方法:获取功能标签
	Handlebars.registerHelper('functionTagName', function(object) {				
		var usertagidlist = object.usertagids.split(','),
			taglist = object.tags.split(','),
			usertagdarenlist = object.usertagdaren.split(','),
			functionflaglist = object.usertagfunctionflag.split(','),
			tagsstr = '',
			tmpavatar = object.avatar!==''?object.avatar:'avatar.jpg',
			selftagflag = 0;
		
		/* 添加标示来判断自己的标签 */	
		if(object.uid===global_onlineuser.uid){
			selftagflag = 1;
		}					
		
		for(var a=0;a<taglist.length;a++){
			if(functionflaglist[a]==='1'){
				if(taglist[a]==='#原创'){
					tagsstr += 
					'<a class="btn btn-xs btn-material-grey" role="button" href="' + global_contextPath + '/mobileusertag/' + usertagidlist[a] + '.htm"><img class="hidden tagavatar ' + (usertagdarenlist[a]==='1'?'daren':'') + '" src="' + global_contextPath + '/networks/avatars/30x30' + tmpavatar + '" />'
					+ '<span class="glyphicon glyphicon-thumbs-up"></span>' + taglist[a] 
					+ '</a>';
				}else if(taglist[a]==='#文章'){
					tagsstr += 
					'<a class="btn btn-xs btn-material-grey" role="button" href="' + global_contextPath + '/mobileusertag/' + usertagidlist[a] + '.htm"><img class="hidden tagavatar ' + (usertagdarenlist[a]==='1'?'daren':'') + '" src="' + global_contextPath + '/networks/avatars/30x30' + tmpavatar + '" />'
					+ '<span class="glyphicon glyphicon-link"></span>' + taglist[a] 
					+ '</a>';
				}else if(taglist[a]==='#问答'){
					tagsstr += 
					'<a class="btn btn-xs btn-material-grey" role="button" href="' + global_contextPath + '/mobileusertag/' + usertagidlist[a] + '.htm"><img class="hidden tagavatar ' + (usertagdarenlist[a]==='1'?'daren':'') + '" src="' + global_contextPath + '/networks/avatars/30x30' + tmpavatar + '" />'
					+ '<span class="glyphicon glyphicon-question-sign"></span>' + taglist[a] 
					+ '</a>';
				}else{
					tagsstr += 
					'<a class="btn btn-xs btn-material-grey" role="button" href="' + global_contextPath + '/mobileusertag/' + usertagidlist[a] + '.htm"><img class="hidden tagavatar ' + (usertagdarenlist[a]==='1'?'daren':'') + '" src="' + global_contextPath + '/networks/avatars/30x30' + tmpavatar + '" />'
					+ '<span class="glyphicon glyphicon-edit"></span>' + taglist[a] 
					+ '</a>';
				}
				break;
			}
		}
	
		return tagsstr;
	});
	
	//模板辅助方法:生成标签列表
	Handlebars.registerHelper('tagList', function(object) {
		var usertagidlist = object.usertagids.split(','),
			taglist = object.tags.split(','),
			usertagdarenlist = object.usertagdaren.split(','),
			functionflaglist = object.usertagfunctionflag.split(','),
			tagsstr = '',
			tmpavatar = object.avatar!==''?object.avatar:'avatar.jpg',
			selftagflag = 0;
		
		/* 添加标示来判断自己的标签 */	
		if(object.uid==global_onlineuser.uid){
			selftagflag = 1;
		}					
		
		for(var a=0;a<taglist.length;a++){
			if(taglist[a]!==null&&taglist[a]!==''){
				if(functionflaglist[a]!='1'){
					tagsstr += 
					'<a class="btn btn-xs btn-material-grey" target="_blank" href="' + global_contextPath + '/mobileusertag/' + usertagidlist[a] + '.htm">' +
					'<span class="avatar">' +
						/*'<img src="' + global_contextPath + '/networks/avatars/30x30' + tmpavatar + '" />' +*/
					'</span>' +
						taglist[a] +
					'</a>'; 
				}
			}
		}
		
		return tagsstr;
	});

	//模板辅助方法:转换url到<a>
	Handlebars.registerHelper('shortenPostBody', function(body) {
		var stext = $('<div/>').append(body).text().trunc(120);
		return stext;
	});
	
	//模板辅助方法:转换url到<a>
	Handlebars.registerHelper('transformUrl', function(title) {
		return replacelinks(title);
	});
	
	//模板辅助方法:获取domain
	Handlebars.registerHelper('fetchDomainInUrl', function(url) {
		return fetch_domain_of_url(url);
	});
	
	//模板辅助方法:删除标签
	Handlebars.registerHelper('stripTags', function(str) {
		var text = $('<div/>').append(str).text();
		if(text.length){
			text = "&nbsp;-&nbsp;" + text;
		}
		return text;
	});
	
	//模板辅助方法:生成标签过滤字符串
	Handlebars.registerHelper('taglistString', function(object) {
		var taglist = object.tags.split(','),
			tagsstr = '';
		for(a=0;a<taglist.length;a++){
			if(taglist[a]!==null&&taglist[a]!==''){
				if(a===taglist.length-1){
					tagsstr += taglist[a];
				}else{
					tagsstr += taglist[a] + ' ';
				}
			}
		}
		return tagsstr;
	});

	//模板辅助方法:生成类别标签标示用来定义CSS
	Handlebars.registerHelper('functionTagFlagClass', function(object) {
		var taglist = object.tags.split(','),
			functionflaglist = object.usertagfunctionflag.split(','),
			functionTagClass = 'gbshare';		
		
		for(var a=0;a<taglist.length;a++){
			if(functionflaglist[a]==='1'){
				if(taglist[a]==='#原创'){
					functionTagClass = 'gboriginal';
				}else if(taglist[a]==='#文章'){
					functionTagClass = 'gbarticle';
				}else if(taglist[a]==='#问答'){
					functionTagClass = 'gbquestion';
				}else if(taglist[a]==='#资源'){
					functionTagClass = 'gbfreebie';
				}else if(taglist[a]==='#调试'){
					functionTagClass = 'gbdebug';
				}
				break;
			}
		}
		
		return functionTagClass;
	});		
	
	
	//模板辅助方法:生成删除链接
	Handlebars.registerHelper('ifCanDelete', function(object) {
		var controllbar = '';
		
		var taglist = object.tags.split(','),
			functionflaglist = object.usertagfunctionflag.split(','),
			whichedit='';		
		
		for(var a=0;a<taglist.length;a++){
			if(functionflaglist[a]==='1'){
				if(taglist[a]==='#原创'){
					whichedit = 'editpost';
				}else if(taglist[a]==='#文章'){
					whichedit = 'editpost';
				}else if(taglist[a]==='#链接'){
					whichedit = 'editlink';
				}else if(taglist[a]==='#问答'){
					whichedit = 'editquestion';
				}else if(taglist[a]==='#资源'){
					whichedit = 'editfreebie';
				}else if(taglist[a]==='#调试'){
					whichedit = 'editgbdebug';
				}
				break;
			}
		}
		
		if(object.uid==global_onlineuser.uid){
			controllbar = '<a style="cursor:pointer" rel="' + object.pid + '" class="postdelete iconbefore"><span class="ui-icon ui-icon-trash"></span>删除</a>';
		}
		
		if(object.uid==global_onlineuser.uid||global_onlineuser.roleid<0){
			controllbar = controllbar + '<a target="_blank" href="'+ global_contextPath + '/' + whichedit + '.htm?postid=' + object.pid + '" class="iconbefore"><span class="ui-icon ui-icon-pencil"></span>编辑</a>';
		}
		return controllbar;
	});			
	
	//模板辅助方法:生成页面关注按钮
	Handlebars.registerHelper('ifCanFollow', function(object) {
		var button = '', taglist = object.tags.split(',');
				
		if(global_onlineuser.uid!==0&&object.uid!==global_onlineuser.uid){
			button = '<a style="cursor:pointer" rel="' + object.pid + '" class="postfollow iconbefore" title="关注用户标签"><span class="ui-icon ui-icon-tag"></span> ' + taglist.length + ' </a>';
		}
	
		return button;
	});
	
	//模板辅助方法:生成时间戳
	Handlebars.registerHelper('fromNow', function(creationdate) {
		var tmpcreationdate = moment(creationdate),
			now = moment();
		if(now.diff(tmpcreationdate, 'days')>=1){
			return tmpcreationdate.format("YYYY年MM月D日 HH:mm:ss");
		}else{
			return tmpcreationdate.fromNow();
		}
	});
	
	Handlebars.registerHelper('gbtagsTitle', function(obj) {
		return obj.title[0];
	});

	Handlebars.registerHelper('if_even', function(value, options) {
		/*
		console.log('value:', value); // value: 2
		console.log('this:', this); // this: Object {num: 2}
		console.log('fn(this):', options.fn(this)); // fn(this): 2是偶数
		*/
		if((value % 2) == 0) {
			return options.fn(this);
		} else {
			return options.inverse(this);
		}
	});

	Handlebars.registerHelper('shortenTextBy280', function(text) {
		return text.trunc(150);
	});

	//Handlebar helper
	Handlebars.registerHelper('compare', function(lvalue, rvalue, options) {
	
	    if (arguments.length < 3)
	        throw new Error("Handlerbars Helper 'compare' 需要两个参数");
	
	    operator = options.hash.operator || "==";
	
	    var operators = {
	        '==':       function(l,r) { return l == r; },
	        '===':      function(l,r) { return l === r; },
	        '!=':       function(l,r) { return l != r; },
	        '<':        function(l,r) { return l < r; },
	        '>':        function(l,r) { return l > r; },
	        '<=':       function(l,r) { return l <= r; },
	        '>=':       function(l,r) { return l >= r; },
	        'typeof':   function(l,r) { return typeof l == r; }
	    }
	
	    if (!operators[operator])
	        throw new Error("Handlerbars Helper 'compare' 使用了不存在的比较符号 "+operator);
	
	    var result = operators[operator](lvalue,rvalue);
	
	    if( result ) {
	        return options.fn(this);
	    } else {
	        return options.inverse(this);
	    }
	
	});
});