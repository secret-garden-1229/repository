<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
	<base href="<%=path%>">
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="css/amazeui.min.css">
		<link rel="stylesheet" href="css/admin.css">
	</head>
	<body>
		<div class="admin-content-body" style="">
			<div class="am-cf am-padding am-padding-bottom-0">
				<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">部门管理</strong><small></small></div>
			</div>
			<hr>
			<div class="am-g">
				<div class="am-u-sm-12 am-u-md-6">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<button type="button" class="am-btn am-btn-default btnAdd">
								<span class="am-icon-plus"></span> 新增
							</button>
							<button type="button" style="margin-left: 20px;" class="am-btn am-btn-default btnFindEnable">
								<span class="am-icon-archive" id="efficientBtn"> 有效部门</span>
							</button>
							<button type="button" style="margin-left: 20px;" class="am-btn am-btn-default btnFindDisable">
								<span class="am-icon-archive" id="noEfficientBtn">已撤销部门</span>
							</button>
						</div>
					</div>
				</div>
			</div>

			<div class="am-g" style="margin-top: -30px;">
				<div class="am-u-sm-12">
					<form class="am-form">
						<table class="am-table am-table-striped am-table-hover table-main">
							<thead>
								<tr>
									<th class="table-check">
										<input id="chkAll" type="checkbox">
									</th>
									<th class="table-id">
										ID
									</th>
									<th class="table-title">
										部门名称
									</th>
									<th class="table-remark">
										职责
									</th>
									<th class="table-remark">
										部门创建时间
									</th>
									<th class="table-remark">
										备注
									</th>
									<th class="table-set">
										操作
									</th>
								</tr>
							</thead>
							<tbody id="tUser">
							<c:forEach items="${departList}" var="depart">
								<tr>
									<td><input name="chks" value="27" type="checkbox" inputID="${depart.did}"></td>
									<td>${depart.did}</td>
									<td>${depart.dname}</td>
									<td>${depart.duty}</td>
									<td><fmt:formatDate value="${depart.credate}" pattern="yyyy年MM月dd日" /></td>
									<td>${depart.dstatus==0?"使用":"撤销"}</td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<button type="button" id="depart_27" class="am-btn am-btn-default am-btn-xs am-text-secondary btnedit">
													<span class="am-icon-pencil-square-o" name="updateBtn" >编辑</span>
												</button>
												<button type="button" class="am-btn am-btn-default am-btn-xs am-text-danger amt-hide-sm-only" >
													<span class="am-icon-trash-o" name="deleteBtn" id="${depart.did}">删除</span>
												</button>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>


							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
		<script src="js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="myplugs/js/plugs.js"></script>
		<script type="text/javascript">
			$(function() {
				$("#chkAll").click(function (){
					$("#tUser input[name=chks]").prop('checked',this.checked);

				})
				$(".btnedit").click(function() {
					var size=$("#tUser input[type=checkbox]:checked").size();
					if (size!=1){
						alert("只能选择一个");
						return false;
					}
					var id=$("#tUser input[type=checkbox]:checked").attr("inputID");
					$.jq_Panel({
						title: "编辑部门",
						iframeWidth: 500,
						iframeHeight: 300,
						url: "editDepart.jsp?id="+id
					});
					//window.location.href = "editDepart?did=" + did;
				});
				$(".btnAdd").click(function() {
					$.jq_Panel({
						title: "添加部门",
						iframeWidth: 500,
						iframeHeight: 300,
						url: "addDepart.jsp"
					});
				});
			});
			function updateDepart(did) {
				window.location.href = "editDepart?did=" + did;
			}

			$("#efficientBtn").click(function () {
				window.location.href="setting/selectEfficientDepartment?dstatus=0";
			})
			$("#noEfficientBtn").click(function () {
				window.location.href="setting/selectEfficientDepartment?dstatus=1";
			})

			$("#tUser input[type=checkbox]").click(function () {
				var size=$("#tUser input[type=checkbox]").size();
				var checkSize=$("#tUser input[type=checkbox]:checked").size();
				size==checkSize?$("#chkAll").prop("checked",true):$("#chkAll").prop("checked",false);
			})

/**
 * 删除操作
 * */
			$("#tUser").on("click","span[name=deleteBtn]",function () {
				var size=$("#tUser input[type=checkbox]:checked").size();
				if (size!=1){
					alert("只能选择一个");
					return false;
				}
				if (size==1){
					var id=$(this).attr("id");
					$.ajax({
						url:'departController/deleteDepartById',
						dataType:'json',
						data:{id:id},
						success:function (data) {
							if (data=="false"){
								alert("删除失败");
							}else {
								alert("删除成功")
								window.location.href="setting/selectEfficientDepartment?dstatus=1";
							}
						}
					})
				}

			})

		</script>
	</body>
</html>
