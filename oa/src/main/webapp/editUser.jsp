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
	<script type="text/javascript" src="js/jquery-1.11.3.min.js" ></script>
	<script type="text/javascript">
		$(function () {
			$("#addRole").click(function () {
				var realname=$("#roleName").val();
				var ename=$("#ename").val();
				var epass=$("#epass").val();
				var esex=$("input[name=esex]:checked").val();
				var did=$("#depart").val();
				var position=$("#role").val();
				$.ajax({
					url:'userController/updateEmployee',
					type:'post',
					dataType:'json',
					contentType: 'application/json;charset=utf-8',
					data:JSON.stringify({
						eid:"${employee.eid}",
						realname:realname,
						ename:ename,
						epass:epass,
						esex:esex,
						did:did,
						position:position
					}),
					success:function (data) {
						if (data==true){
							alert("修改成功");
							window.parent.location.href="userController/selectEmployee?estatus=0";
						}else {
							alert("修改失败");
						}
					}
				})


			})
		})
	</script>
	</head>
	<body>
		
		<div class="am-cf admin-main">
			<!-- content start -->
			<div class="admin-content">
				<div class="admin-content-body">
					<div class="am-g">
						<form class="am-form am-form-horizontal"  method="post" style="padding-top: 30px;">
							<input value="504" name="roleId" type="hidden">
							<div class="am-form-group">
								<label for="roleName" class="am-u-sm-3 am-form-label">
									员工姓名
								</label>
								<div class="am-u-sm-9">
									<input id="roleName" required="" placeholder="请输入员工姓名" value="${employee.realname}" name="realname" type="text">
									<small id="helpRole">输入员工姓名。</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="ename" class="am-u-sm-3 am-form-label">
									用户名
								</label>
								<div class="am-u-sm-9">
									<input type="text" id="ename" name="username" value="${employee.ename}" placeholder="请输入用户用户名"  />
									<small>输入用户名。</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="epass" class="am-u-sm-3 am-form-label">
									密码
								</label>
								<div class="am-u-sm-9">
									<input type="password" id="epass" name="paw" value="${employee.epass}" placeholder="请输入用户密码"  />
									<small>输入密码。</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="esex" class="am-u-sm-3 am-form-label">
									性别
								</label>
								<div class="am-u-sm-9">
									<c:if test="${employee.esex==0}">
										<input type="radio" name="sex" id="esex" value="0" checked="checked" /> 男
										<input type="radio" name="sex" value="1" style="margin-left: 30px;"/> 女<br>
										<small>选择性别。</small>
									</c:if>
									<c:if test="${employee.esex==1}">
										<input type="radio" name="sex" value="0"/> 男
										<input type="radio" name="sex" id="esex" value="1"  checked="checked" style="margin-left: 30px;"/> 女<br>
										<small>选择性别。</small>
									</c:if>
								</div>
							</div>
							<div class="am-form-group">
								<label for="depart" class="am-u-sm-3 am-form-label">
									所在部门
								</label>
								<div class="am-u-sm-9">
									<select id="depart" placeholder="请选择所在部门" name="dname">
										<c:forEach items="${departList}" var="depart">
											<c:if test="${depart.did}==${employee.did}">
												<option value="${depart.did}" selected>${depart.dname}</option>
											</c:if>
											<option value="${depart.did}">${depart.dname}</option>
										</c:forEach>
									</select>
									<!--<textarea rows="" cols="50" placeholder="请输入用户密码" name="roleDesc"></textarea>-->
									<small>选择所在部门。</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="role" class="am-u-sm-3 am-form-label">
									用户角色
								</label>
								<div class="am-u-sm-9">
									<select id="role" placeholder="请选择用户角色" name="role">
										<c:forEach items="${collect}" var="role">
											<c:if test="${role.position}==${employee.position}">
												<option value="${role.did}" selected>${employee.ename}</option>
											</c:if>
											<option value="${role.position}">${role.role}</option>
										</c:forEach>
									<%--<option value="普通员工">普通员工</option>
									<option value="部门主管">部门主管</option>--%>
									</select>
									<small>请选择用户角色。</small>
								</div>
							</div>
							<div class="am-form-group">
								<div class="am-u-sm-9 am-u-sm-push-3">
									<input id="addRole" class="am-btn am-btn-success" value="编辑员工" type="button">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
