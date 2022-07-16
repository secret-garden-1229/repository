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
		<link rel="stylesheet" href="css/app.css">
		<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
		<style>
			.admin-main{
				padding-top: 0px;
			}
			.am-form-group{
				margin-top: 30px;
			}
		</style>
	</head>
	<body>
		
		<div class="am-cf admin-main">
			<!-- content start -->
			<div class="admin-content">
				<div class="admin-content-body">
					<div class="am-g">
						<form class="am-form am-form-horizontal" action="role/addroleSubmit.action" method="get" style="padding-top: 30px;">
							<input value="504" name="roleId" type="hidden">
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									申请人
								</label>
								<div class="am-u-sm-9">
									<input id="roleName" required="" placeholder="获取申请人姓名" value="" name="departName" type="text">
								</div>
							</div>
							<div class="am-form-group">
								<label for="title" class="am-u-sm-3 am-form-label">
									请假标题
								</label>
								<div class="am-u-sm-9">
									<input type="text" name="title" id="title" placeholder="请输入请假标题"/>
								</div>
							</div>
							<div class="am-form-group">
								<label for="context" class="am-u-sm-3 am-form-label">
									请假原因
								</label>
								<div class="am-u-sm-9">
									<textarea rows="" id="context" cols="50" placeholder="请输入请假原因" name="context"></textarea>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									开始时间
								</label>
								<div class="am-u-sm-9">
									<div class="data">
										<div class="input-group date">
											<div class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</div>
											<input type="text" name="start" placeholder="请输入请假开始时间"  class="form-control pull-right" id="startDate">
										</div>
									</div>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									结束时间
								</label>
								<div class="am-u-sm-9">
									<div class="data">
										<div class="input-group date">
											<div class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</div>
											<input type="text" name="end" placeholder="请输入请假结束时间"  class="form-control pull-right" id="endDate">
										</div>
									</div>
								</div>
							</div>
							<div class="am-form-group">
								<label for="length" class="am-u-sm-3 am-form-label">
									请假时长
								</label>
								<div class="am-u-sm-9">
									<input type="text" id="length" name="length" placeholder="请假时长"/>
								</div>
							</div>
							<div class="am-form-group">
								<div class="am-u-sm-9 am-u-sm-push-3">
									<input id="addRole" class="am-btn am-btn-success" value="申请请假" type="button">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script src="js/jquery-1.11.3.min.js"></script>
		<script src="plugins/datepicker/bootstrap-datepicker.js"></script>
		<script src="plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
		<script>
			$(document).ready(function() {
				$('#startDate').datepicker({
					autoclose: true,
					language: 'zh-CN'
				});
				$('#endDate').datepicker({
					autoclose: true,
					language: 'zh-CN'
				});

				$("#addRole").click(function () {
					var title=$("#title").val();
					var context=$("#context").val()
					var startdate=$("#startDate").val();
					var enddate=$("#endDate").val();
					var length=$("#length").val();
					$.ajax({
						url:'noteController/addRecord',
						type:'post',
						dataType:'json',
						contentType:'application/json;charset=utf-8',
						data:JSON.stringify({title:title,context:context,startdate:startdate,enddate:enddate,length:length}),
						success:function (data) {
							if (data==true){
								alert("登录成功！")
								window.parent.location.href='noteController/selectAllNote?estatus=0';
							}else{
								alert("登录失败！")
							}
						}
					})

				})

			});
		</script>
	</body>
</html>
