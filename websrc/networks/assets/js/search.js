/**
 * Created by cayley on 16/1/22.
 */
//搜索按钮
$(function(){
    $("#site-search").blur(function() {
        $("#searchbox").animate({
            width: "0",
        }).hide();
        $("#searchcontrol").show();
        $("#toolsbar").show();
    });
    var Util = {
        ifItsmall: function() {
            if ($(window).width() > 752 && $(window).width() < 976) {
                $("#formhide").on("click", function() {
                    $("#searchbox").show().animate({
                        width: "113px"
                    }, 300);
                    $("#site-search").focus();
                    $("#searchcontrol").hide();
                    $("#toolsbar").hide();
                });
            } else {
                $("#toolsbar").show();
                $("#formhide").off("click").on("click", function() {
                    $("#searchbox").show().animate({
                        width: "113px"
                    });
                    $("#site-search").focus();
                    $("#searchcontrol").hide();
                });
            }
        }
    };
    Util.ifItsmall();
    $(window).resize(function() {
        Util.ifItsmall();
    });

//查询消息直接用的老版本的方法 gbmobile.js
    function getactivitymsg() {
        /* filter ppt url for this function() , it doesn't need*/
        if (window.location.href.indexOf(global_contextPath + "/ppt/") > -1) {
            return false;
        }
        if (gbin1_userid != 0) {
            var dto = {
                isread: 0
            };
            identityService.findAllUnReadActivities(dto, {
                callback: function(data) {
                    if (data.length > 0) {
                        $("#mymsg").html(data.length).removeClass("hidden").closest(".dropdown-menu").prev().children().addClass("warning-text animated flash");
                    } else {
                        $("#mymsg").html("").addClass("hidden");
                    }
                }
            });
        }
        window.setTimeout("getactivitymsg()", 12e4);
    }
    $(function() {
        if (global_onlineuser.uid > 0) {
            setTimeout(function() {
                getprivatemsg();
            }, 2e3);
            //避免同时向数据库发送请求，间隔2秒
            if (document.URL.indexOf("notification") < 0) {
                setTimeout(function() {
                    getactivitymsg();
                }, 4e3);
            }
        }
    });
});

//搜索功能

$(function(){
    // 默认全站搜索:
    $('#gbtagsNameLabel').addClass('selected');
    //搜索标签无结果时自动搜索全站延时
    var canceltimeout = null,
    //标签搜索无结果倒数搜索全站时给出取消按钮
        confirmcancel = $('<button/>',{'id':'confirmcancel','text':'取消','style':'text-align:center;'}),
    //切换搜索类型
        changeflag = false,
        resultsDiv = $('#resultsDiv'),
        $getMore = $('#more'),
        $newtags = $('#newtags'),
        $loading= $('<div class="loading"></div>'),
        $submitButton = $('#submitButton'),
        settings = {},
        setting = {},
        config = {
            siteURL		: 'gbtags.com',	// Change this to your site
            searchTag	: false,
            searchGBTags: true,
            type		: 'web',
            append		: false,
            perPage		: 10,			// A maximum of 8 is allowed by Google
            page		: 0	,			// The start page
        },
    //复用的工具函数
        Util = {
            searchWhiceOne:function(){
                if(config.searchTag){
                    $('#tagNameLabel').siblings().css('color','#AAA').end().css('color','#4caf50');
                    gbtagSearch();
                    $('#resultnum').hide();
                }else{
                    $('#gbtagsNameLabel').siblings().css('color','#AAA').end().css('color','#4caf50');
                    searchSite();
                    $('#resultnum').show();
                }
            }
        };
    //将地址栏中的搜索内容放到搜索框中
    if(typeof search != 'undefined' && search!==''){
        $('#s').val(search);
        Util.searchWhiceOne();
    }
    
	//搜索自动完成提示
	identityService.findAllTags({
	    callback:function(data) {
	        var taglist = [];
	        $.each(data, function(i, val){
	            taglist.push(val.tagname);
	        });
		    $( "#s" ).typeahead({
		        local: taglist
		    }).each(function() {
		       $(this).prev('.tt-hint').addClass('hidden');
			});
	    }
	});
	

	
    //搜索全站
    function searchSite(settings){
        settings = $.extend({},config,settings);
        settings.term = settings.term || $('#s').val();

        if(changeflag){
            resultsDiv.html('').append($loading);
        }
        $submitButton.attr('disabled', 'disabled');

        if(settings.term === '') {settings.term = '*';}
        var start = ($('.webResult').length/settings.perPage)*settings.perPage;
        var term = encodeURIComponent(settings.term);
        $.getJSON('/gbsearch/gbtags/select?q=title:' + term + '&start=' + start +'&hl=true&hl.fl=title&hl.simple.pre=<em>&hl.simple.post=</em>&wt=json&indent=false', function(data) {
            $('#numfound').html(data.response.numFound);
            $getMore.show();
            if($('.webResult').length<=data.response.numFound){
                var template = Handlebars.compile($("#gbtags-data-tmpl").html());

                var highlight = data.highlighting,
                    rawresultlist = data.response.docs,
                    resultlist = [];

                for(var i=0;i<rawresultlist.length;i++){
                    var id = rawresultlist[i].id,
                        title = rawresultlist[i].title,
                        description  =  rawresultlist[i].description,
                        rn=rawresultlist[i].resourcename;
                        data.rnum=rn;
                    for (var idstr in highlight) {
                        if(idstr === id){
                            if(highlight[idstr].title!==undefined){
                                title = highlight[idstr].title;
                            }
                            break;
                        }
                    }
                    resultlist.push({title:title,id:id,description:description});
                }

                var htmlcontent = template(resultlist);

                $('#resultsDiv').append( htmlcontent );
                $('<div>',{id:'more'}).appendTo(resultsDiv).click(function(){
                    changeflag = false;
                    searchSite({append:true,page:settings.page+1});
                    $(this).hide();
                });
                settings.page++;
            }else{
                $getMore.hide();
                $('#resultsDiv').append( '<div style="text-align:center;">没有更多相关搜索结果</div>' );
            }
            $('#submitButton').removeAttr('disabled');
            $loading.remove();
        });
    }


    $('#resultsDiv').on('click','#confirmcancel', function(){
        clearTimeout(canceltimetout);
        $('#autosearchwrapper').hide();
        confirmcancel.hide();
    });


    //提交
    $('#searchForm').submit(function(e){
        changeflag = true;
        settings.page = 0;
        Util.searchWhiceOne();
        if($('.no-more')!=null||$('.no-more')!=undefined){
            $('.no-more').remove();
        }
        //阻止提交刷新页面的默认行为e.preventDefault无效
        e.preventDefault();
        return false;
    });
    //加载更多
    $getMore.click(function(){
            changeflag = false;
            searchSite({append:true});
    });
});
