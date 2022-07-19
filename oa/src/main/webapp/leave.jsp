<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
	<base href="<%=path%>">
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="css/amazeui.min.css" />
		<link rel="stylesheet" href="css/admin.css" />
	<script src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		$(function () {
			$("#chkAll").click(function (){
				$("#tUser input[name=chks]").prop('checked',this.checked);
			})

			$("#tUser input[type=checkbox]").click(function () {
				var size=$("#tUser input[type=checkbox]").size();
				var checkSize=$("#tUser input[type=checkbox]:checked").size();
				size==checkSize?$("#chkAll").prop("checked",true):$("#chkAll").prop("checked",false);
			})

			/** 待审批*/
			$("#pendingBtn").click(function () {
				window.location.href="noteController/selectAllNote?estatus=2"
			})

			/** 已审批*/
			$("#havedPendingBtn").click(function () {
				window.location.href="noteController/selectAllNote?estatus=1"
			})
			/** 请假记录*/
			$("#leaveBtn").click(function () {
				window.location.href="noteController/selectAllNote?estatus=0";
			})

			$("#tUser button[name=agreeBtn]").click(function () {
				var id=$(this).attr("agreeId");
				$.ajax({
					url: 'noteController/addRecord',
					type:'get',
					dataType:'json',
					contentType:'application/json;charset=utf-8',
					data:{'estatus':0,'nid':id},
					success:function (data) {
						if (data==true){
							window.parent.location.href="noteController/selectAllNote?estatus=0";
						}else if (data==false){
							alert("失败")
						}
					}
				})
			})

			$("#tUser button[name=returnBtn]").click(function () {
				var id=$(this).attr("agreeId");
				$.ajax({
					url: 'noteController/addRecord',
					type:'get',
					dataType:'json',
					contentType:'application/json;charset=utf-8',
					data:{'estatus':0,'nid':id},
					success:function (data) {
						if (data==true){
							window.parent.location.href="noteController/selectAllNote?estatus=2";
						}else if (data==false){
							alert("失败")
						}
					}
				})
			})

			$("#tUser button[name=noagreeBtn]").click(function () {
				var id=$(this).attr("agreeId");
				$.ajax({
					url: 'noteController/addRecord',
					type:'get',
					dataType:'json',
					contentType:'application/json;charset=utf-8',
					data:{'estatus':0,'nid':id},
					success:function (data) {
						if (data==true){
							window.parent.location.href="noteController/selectAllNote?estatus=3";
						}else if (data==false){
							alert("失败")
						}
					}
				})
			})


		})
	</script>
	</head>

	<body>
		<div class="admin-content-body">
			<div class="am-cf am-padding am-padding-bottom-0">
				<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">请假管理</strong><small></small></div>
			</div>

			<hr>

			<div class="am-g">
				<div class="am-u-sm-12 am-u-md-6">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<button type="button" class="am-btn am-btn-default btnAdd"><span class="am-icon-plus"></span>申请请假</button>
							<button type="button" id="leaveBtn" style="margin-left: 20px;" class="am-btn am-btn-default btnFindNotes"><span class="am-icon-archive"></span> 请假记录 </button>
							<c:if test="${user.position<2}">
							<button type="button" id="pendingBtn" style="margin-left: 20px;" class="am-btn am-btn-default btnFindNoCheck"><span class="am-icon-archive"></span> 待审批</button>
							<button type="button" id="havedPendingBtn" style="margin-left: 20px;" class="am-btn am-btn-default btnFindCheck"><span class="am-icon-archive"></span> 已审批 </button>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<form class="am-form">
						<table class="am-table am-table-striped am-table-hover table-main">
							<thead>
								<tr>
									<th class="table-check"><input type="checkbox" id="chkAll"></th>
									<th class="table-id" style="width:100px;">ID</th>
									<th class="table-title">申请人</th>
									<th class="table-title">请假标题</th>
									<th class="table-type">事由说明</th>
									<th class="table-dname">请假开始日期</th>
									<th class="table-role">请假结束日期</th>
									<th class="table-role">主管审批状态</th>
									<c:if test="${user.position<2}">
									<th class="table-set">操作</th>
									</c:if>
								</tr>
							</thead>
							<tbody id="tUser">
							<c:forEach items="${notes.list}" var="note">
								<tr>
									<td><input type="checkbox" name="chks" ></td>
									<td>${note.nid}</td>
									<td>${note.realname}</td>
									<td>${note.title}</td>
									<td>${note.context}</td>
									<td class="am-hide-sm-only"><span class="am-badge  am-badge-danger "><fmt:formatDate value="${note.enddate}" pattern="yyyy年MM月dd日" /></span></td>
									<td><span class="am-badge  am-badge-danger "><fmt:formatDate value="${note.enddate}" pattern="yyyy年MM月dd日" /></span></td>
									<c:if test="${note.estatus==0}">
										<td>待审批</td>
									</c:if>
									<c:if test="${note.estatus==1}">
									<td>同意</td>
									</c:if>
									<c:if test="${note.estatus==2}">
										<td>打回</td>
									</c:if>
									<c:if test="${note.estatus==3}">
										<td>不同意</td>
									</c:if>

									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<c:if test="${user.position<2}">
												<button name="agreeBtn" type="button" agreeId="${note.nid}" id="depart_24" class="am-btn am-btn-default am-btn-xs am-text-secondary btnedit"><span class="am-icon-pencil-square-o"></span> 同意</button>
												<button name="returnBtn" type="button" class="am-btn am-btn-default am-btn-xs am-text-danger amt-hide-sm-only"><span class="am-icon-trash-o"></span> 打回</button>
												<button name="noagreeBtn" type="button" id="depart_24" class="am-btn am-btn-default am-btn-xs am-text-secondary btnedit"><span class="am-icon-pencil-square-o"></span> 不同意</button>
												</c:if>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>

							</tbody>
						</table>
						<div class="am-cf" style="margin-right: 30px;">
							共 ${notes.size} 条记录
							<div class="am-fr">
								<ul class="am-pagination">
									<li class="am-disabled">
										<a href="#">«</a>
									</li>
									<li class="am-active">
										<a href="#">1</a>
									</li>
									<li>
										<a href="#">»</a>
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
		<script>
			$(function() {
				$(".btnAdd").click(function() {
					$.jq_Panel({
						title: "申请请假",
						iframeWidth: 600,
						iframeHeight: 560,
						url: "addLeave.jsp"
					});
				});
			});
		</script>
	</body>

</html>