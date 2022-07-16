<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
    <base href="<%=path%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
    $(function (){
        $("#loginBtn").click(function () {
            var username= $.trim($("#username").val())
            var password=$.trim($("#textarea").val())
            $.ajax({
                url:'setting/login',
                type:'post',
                data:{
                    username:username,
                    password:password
                },
                success:function (data) {
                    if (data=='true'||data==true){
                        window.location.href='setting/toIndex';
                    }else {
                        alert("请确定找密码正确！")
                    }
                }
            })

        })
    })
</script>
</head>

<body>
<div id="web">
<p style="height:180px;"></p>
<p align="center"><img src="img/logzi.png" /></p>
<p style="height:40px;"></p>
<div class="login">
   <div class="banner">
   <iframe id="frame_banner" src="sban/banner.html" height="218" width="100%"  allowtransparency="true" title="test"  scrolling="no" frameborder="0"></iframe>
   </div>
   <div class="logmain">
      <h1>&nbsp;</h1>
      <div class="logdv">
         <span class="logzi">账 号：</span>
        <input name="username" type="text" id="username" class="ipt" />
      </div>
      <div class="logdv">
      <span class="logzi">密 码：</span>
        <input name="password" type="password" id="textarea" class="ipt" />
      </div>
      <div class="logdv">
        <p class="logzi">&nbsp;</p>
        <a href="#" class="more">忘记密码</a>
        <input name="remember" type="checkbox" value=""  class="cex"/>记住密码
      </div>
      <div class="logdv" style="height:40px;">
        <p class="logzi">&nbsp;</p>    
        <input name="提交" id="loginBtn" type="button" class="btnbg" value="点击登录" />
      </div>
      <div>
        <a href="#" class="more">注册</a>
      </div>
   </div>
</div>
<p style="height:100px;"></p>
<!--<p align="center">技术支持:xxxxxxxxxx</p>-->
</div>
</body>
</html>
