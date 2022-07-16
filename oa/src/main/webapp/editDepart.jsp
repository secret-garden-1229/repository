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
		<script src="js/jquery-1.11.3.min.js"></script>
		<style>
			.admin-main{
				padding-top: 0px;
			}
			
		</style>
		<script type="text/javascript">
			$(function () {
				var id=window.location.search.substring(4);
				$("#addRole").click(function () {
					var dname=$("#roleName").val();
					var duty=$("#remark").val();
					$.ajax({
						url:'departController/updateDepartById',
						type:'get',
						dataType:'json',
						data:{'dname':dname,'duty':duty,'did':id},
						success:function (data) {
							if (data==true){
								alert("修改成功！")
								window.parent.location.href='setting/selectEfficientDepartment?dstatus=0';
							}else {
								alert("修改失败！ ")
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
						<form class="am-form am-form-horizontal" action="role/addroleSubmit.action" method="post" style="padding-top: 30px;">
							<input value="504" name="roleId" type="hidden">
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									部门名称
								</label>
								<div class="am-u-sm-9">
									<input id="roleName" required="" placeholder="请输入部门名称" value="" name="departName" type="text">
									<small id="helpRole">输入部门名称。</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									备注说明
								</label>
								<div class="am-u-sm-9">
									<textarea rows="" id="remark" cols="50" placeholder="请输入备注说明" name="roleDesc"></textarea>
									<small>输入备注说明。</small>
								</div>
							</div>
							<div class="am-form-group">
								<div class="am-u-sm-9 am-u-sm-push-3">
									<input id="addRole" class="am-btn am-btn-success"  value="编辑部门" type="button">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
