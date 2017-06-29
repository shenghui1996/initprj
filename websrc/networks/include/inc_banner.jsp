<%@ page contentType="text/html;charset=UTF-8"%> 

<!--
<div style="margin:5px 0 20px;">
	<a href="http://www.kaikeba.com" style="margin:0;">
		<img src="http://www.gbtags.com/gb/laitu/1600x400" class="img-responsive" />
	</a>
</div>
//-->

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <a href="<%=contextPath%>/mobileshare/84.htm">
      <img src="<%=contextPath%>/networks/assets/img/slider/1.jpg" />
      
      <div class="carousel-caption">
        
      </div>
      </a>
    </div>
    <div class="item">
      <a href="<%=contextPath%>/mobileshare/71.htm">
      <img src="<%=contextPath%>/networks/assets/img/slider/2.jpg" />
      
      <div class="carousel-caption">
        
      </div>
      </a>
    </div>
    <div class="item">
      <a href="<%=contextPath%>/mobileshare/88.htm">
      <img src="<%=contextPath%>/networks/assets/img/slider/3.jpg" />
      
      <div class="carousel-caption">
        
      </div>
      </a>
    </div>
    <div class="item">
      <a href="<%=contextPath%>/mobileshare/38.htm">
      <img src="<%=contextPath%>/networks/assets/img/slider/4.jpg" />
      
      <div class="carousel-caption">
        	
      </div>
      </a>
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">前一张</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">后一张</span>
  </a>
</div>
