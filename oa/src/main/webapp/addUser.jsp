<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
	<base href="<%=path%>">
		<meta charset="UTF-8">
		<title></title>
		
			<link rel="stylesheet" href="css/amazeui.min.css">
		<link rel="stylesheet" href="css/admin.css">
		<link rel="stylesheet" href="css/app.css">
		<style>
			.admin-main{
				padding-top: 0px;
			}
		</style>
	<script type="text/javascript" src="js/jquery-1.11.3.min.js" ></script>
	<script type="text/javascript">
		$(function () {
			$("#addRole").click(function () {
				var realname=$("#roleName").val();
				var ename=$("#username").val();
				var epass=$("#user-name").val();
				var esex=$("input[name=esex]:checked").val();
				var did=$("#depart").val();
				var position=$("#position").val();
				alert(position);
				$.ajax({
					url:'userController/addEmployee',
					type:'post',
					dataType:'json',
					contentType: 'application/json;charset=utf-8',
					data:JSON.stringify({
						realname:realname,
						ename:ename,
						epass:epass,
						esex:esex,
						did:did,
						position:position
					}),
					success:function (data) {
						if (data==true){
							alert("添加成功");
							window.parent.location.href="userController/selectEmployee";
						}
					}
				})


			})
		})
	</script>
	</head>
	<body>
		<div class="am-cf admin-main">
			<div class="admin-content">
				<div class="admin-content-body">
					<div class="am-g">
						<form class="am-form am-form-horizontal" method="post" style="padding-top: 30px;">
							<input value="504" name="roleId" type="hidden">
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									员工姓名
								</label>
								<div class="am-u-sm-9">
									<input id="roleName" required="" placeholder="请输入员工姓名" value="" name="realname" type="text">
									<small id="helpRole">输入员工姓名。</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="username" class="am-u-sm-3 am-form-label">
									用户名
								</label>
								<div class="am-u-sm-9">
									<input type="text" name="username" id="username" placeholder="请输入用户用户名"  />
									<small>输入用户名。</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									密码
								</label>
								<div class="am-u-sm-9">
									<input type="password" name="paw" id="user-name" placeholder="请输入用户密码"  />
									<small>输入密码。</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									性别
								</label>
								<div class="am-u-sm-9">
									<input type="radio" name="esex" value="0" checked="checked" /> 男
									<input type="radio" name="esex" value="1" style="margin-left: 30px;"/> 女<br>
									<small>选择性别。</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									所在部门
								</label>
								<div class="am-u-sm-9">
									<select id="depart" placeholder="请选择所在部门" name="dname">
										<c:forEach items="${departList}" var="depart">
											<option value="${depart.did}">${depart.dname}</option>
										</c:forEach>
									</select>
									<small>选择所在部门。</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									用户角色
								</label>
								<div class="am-u-sm-9">
									<select id="position" placeholder="请选择用户角色" name="role">
										<c:forEach items="${collect}" var="employee">
											<option value="${employee.position}">${employee.role}</option>
										</c:forEach>

									</select>
									<small>请选择用户角色。</small>
								</div>
							</div>
							<div class="am-form-group">
								<div class="am-u-sm-9 am-u-sm-push-3">
									<input id="addRole" class="am-btn am-btn-success" value="添加员工" type="button">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
