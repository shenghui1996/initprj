<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="include/inc_common.jsp"%>
<!DOCTYPE html>
<html>

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://cdn.gbtags.com/twitter-bootstrap/3.2.0/css/bootstrap.min.css">
  <title>404 错误, 没有找到此链接</title>
</head>

<body style="background: #edeff0;">
<div class="container">
  <div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
      <a href="<%=contextPath%>/search.htm?source=gbtags">
        <img class="img-responsive center-block" src="<%=contextPath%>/networks/assets/img/404.jpg" alt="404文件没有找到错误">
      </a>
    </div>
  </div>
</div>

</body>

</html>