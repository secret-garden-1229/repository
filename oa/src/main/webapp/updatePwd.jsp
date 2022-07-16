<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
	<base href="<%=path%>">
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="css/amazeui.min.css">
		<link rel="stylesheet" href="css/admin.css">
		<style>
			.admin-main{
				padding-top: 0px;
			}
		</style>
	</head>
	<body>
		<div class="am-cf admin-main">
			<div class="admin-content">
				<div class="admin-content-body">
					<div class="am-g">
						<form class="am-form am-form-horizontal" style="padding-top:30px;" data-am-validator>
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									新密码 </label>
								<div class="am-u-sm-9">
									<input type="password" id="doc-vld-pwd-1" required placeholder="请输入新密码" name="password"> <small>输入新密码。</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									重复密码</label>
								<div class="am-u-sm-9">
									<input type="password" id="doc-vld-pwd-2" required placeholder="请输入重复密码" name="pwd" data-equal-to="#doc-vld-pwd-1"
									 required> <small>输入重复密码。</small>
								</div>
							</div>
							<div class="am-form-group">
								<div class="am-u-sm-9 am-u-sm-push-3">
									<input type="button" id="btns"  class="am-btn am-btn-success" value="修改密码" />
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="js/jquery-1.11.3.min.js">
		</script>
		<script type="text/javascript" src="myplugs/js/plugs.js">
		</script>
		<script type="text/javascript">

			$("#btns").click(function () {
				var oldPasswords=$("#doc-vld-pwd-2").val();
				var password=$("#doc-vld-pwd-1").val();
				if (oldPasswords!=password){
					return false;
				}
				$.ajax({
					url:'setting/updatePassword',
					type:'get',
					dataType:'json',
					data:{oldPassword:oldPasswords,password:password},
					success:function (data) {
						if (data=='false'){
							alert("修改失败!");
						}else if (data='true'){
							window.parent.location.href='login.jsp';
						}
					}
				})
			})




		</script>
	</body>
</html>
