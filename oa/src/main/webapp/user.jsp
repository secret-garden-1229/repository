<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
	<base href="<%=path%>">
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="css/amazeui.min.css" />
		<link rel="stylesheet" href="css/admin.css" />
	</head>

	<body>
		<div class="admin-content-body">
			<div class="am-cf am-padding am-padding-bottom-0">
				<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户管理</strong><small></small></div>
			</div>
			<hr>
			<div class="am-g">
				<div class="am-u-sm-12 am-u-md-6">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<button type="button" class="am-btn am-btn-default btnAdd"><span class="am-icon-plus"></span> 新增</button>
							<button type="button" id="onBtn" style="margin-left: 20px;" class="am-btn am-btn-default btnIn">
								<span class="am-icon-child">在职员工</span>
							</button>
							<button type="button" style="margin-left: 20px;" id="outBtn" class="am-btn am-btn-default btnOut">
								<span class="am-icon-child">离职员工</span>
							</button>
						</div>
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-3">
				</div>
				<div class="am-u-sm-12 am-u-md-3">
					<div class="am-input-group am-input-group-sm" style="margin-right: 30px;">
						<input type="text" id="select" class="am-form-field" placeholder="请输入用户姓名">
						<span class="am-input-group-btn">
							<button class="am-btn am-btn-default" id="selectbtn" type="button">搜索</button>
						</span>
					</div>
				</div>
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<form class="am-form">
						<table class="am-table am-table-striped am-table-hover table-main">
							<thead>
								<tr>
									<th class="table-check"><input type="checkbox" id="chkAl"></th>
									<th class="table-id" style="width:100px;">ID</th>
									<th class="table-title">姓名</th>
									<th class="table-type">用户名</th>
									<th class="table-author am-hide-sm-only">性别</th>
									<th class="table-dname">所在部门</th>
									<th class="table-role">用户角色</th>
									<th class="table-set">操作</th>
								</tr>
							</thead>
							<tbody id="tUser">
							<c:forEach items="${pages.list}" var="page" >
								<tr>
									<td><input type="checkbox" name="chks" inputID="${page.eid}"></td>
									<td>${page.eid}</td>
									<td>
										<a href="#">${page.realname}</a>
									</td>
									<td>${page.ename}</td>
									<td class="am-hide-sm-only"><span class="am-badge  am-badge-danger ">${page.esex==0?"男":"女"}</span></td>
									<td>${page.dname}</td>
									<td>${page.position}</td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs"><button type="button" id="depart_24" class="am-btn am-btn-default am-btn-xs am-text-secondary btnedit"><span class="am-icon-pencil-square-o">编辑</span> </button><button type="button" class="am-btn am-btn-default am-btn-xs am-text-danger amt-hide-sm-only"><span class="am-icon-trash-o" id="${page.eid}" name="deleteBtn">删除</span></button></div>
										</div>
									</td>
								</tr>
							</c:forEach>


							</tbody>
						</table>
						<div class="am-cf" style="margin-right: 30px;">
							共 ${pages.size} 条记录
							<div class="am-fr">
								<ul class="am-pagination">
									<li class="am-disableds">
										<c:if test="${pages.hasPreviousPage}">
											<a href="userController/selectEmployee?pageNum=${pages.prePage}">«</a>
										</c:if>

										<c:if test="${pages.hasPreviousPage==false}">
											<a href="javascript:;">«</a>
										</c:if>
									</li>

									<li>
										<c:if test="${pages.hasNextPage}">
											<a href="userController/selectEmployee?pageNum=${pages.nextPage}">»</a>
										</c:if>
										<c:if test="${pages.hasNextPage==false}">
											<a href="javascript:;">»</a>
										</c:if>
									</li>
								</ul>
							</div>
						</div>
						<hr>
					</form>
				</div>
			</div>
		</div>

		<script type="text/javascript" src="js/jquery-1.11.3.min.js" ></script>
		<script type="text/javascript" src="myplugs/js/plugs.js" ></script>
		<script type="text/javascript">
			$(function() {
				$(".btnAdd").click(function() {
					$.jq_Panel({
						title: "添加员工",
						iframeWidth: 600,
						iframeHeight: 560,
						url: "userController/selectAllDepartIDAndDname"
					});
				});
				$(".btnedit").click(function() {
					var size=$("#tUser input[type=checkbox]:checked").size();
					if (size!=1){
						alert("只能选择一个");
						return false;
					}
					var id=$("#tUser input[type=checkbox]:checked").attr("inputID");
					$.jq_Panel({
						title: "编辑员工",
						iframeWidth: 600,
						iframeHeight: 560,
						url: "userController/selectEmpoleeById?eid="+id
					});
				});

				/**
				 * 在职员工
				 * */
				$("#onBtn").click(function () {
					window.location.href="userController/selectEmployee?estatus=0"
				})

				$("#outBtn").click(function () {
					window.location.href="userController/selectEmployee?estatus=1"
				})

				$("#selectbtn").click(function () {
					var realname=$("#select").val();
					window.location.href="userController/selectEmployee?realname="+realname;
				})

				$("#chkAl").click(function (){
					$("#tUser input[name=chks]").prop('checked',this.checked);

				})

				$("#tUser input[type=checkbox]").click(function () {
					var size=$("#tUser input[type=checkbox]").size();
					var checkSize=$("#tUser input[type=checkbox]:checked").size();
					size==checkSize?$("#chkAl").prop("checked",true):$("#chkAl").prop("checked",false);
				})

				$("#tUser").on("click","span[name=deleteBtn]",function () {
					var size=$("#tUser input[type=checkbox]:checked").size();
					if (size!=1){
						alert("只能选择一个");
						return false;
					}
					if (size==1){
						var id=$(this).attr("id");
						alert(id)
						$.ajax({
							url:'userController/deleteEmployeeById',
							dataType:'json',
							type:'get',
							data:{eid:id,estatus:1},
							success:function (data) {
								if (data=="false"){
									alert("删除失败");
								}else {
									alert("删除成功")
									window.location.href="userController/selectEmployee?estatus=0";
								}
							}
						})
					}

				})


			});



		</script>
	</body>

</html>