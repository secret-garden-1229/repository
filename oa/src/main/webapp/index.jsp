<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
	<base href="<%=path%>">
		<meta charset="utf-8">
		<title>首页</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">


		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="css/cropper.min.css" />
		<link rel="stylesheet" type="text/css" href="css/sitelogo.css" />
		<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />

		<script src="js/jquery-1.11.3.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/cropper.js"></script>
		<script src="js/sitelogo.js"></script>


		<!-- <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> -->
		<link rel="stylesheet" type="text/css" href="css/vendor.css">
		<link rel="stylesheet" type="text/css" href="css/theme.css">
		<link rel="stylesheet" type="text/css" href="css/utility.css">
		<link rel="stylesheet" type="text/css" href="css/custom.css">
		<link rel="shortcut icon" href="img/favicon.ico">

		<style>
			.nav li {
				border-bottom: 1px solid #eee;
			}
			.glyphicons-facebook{
				margin-right: 40px;
			}
		</style>

	</head>

	<!-- Start: Header -->
	<header class="navbar navbar-fixed-top">
		<div class="navbar-branding">
			<span id="toggle_sidemenu_l" class="glyphicons glyphicons-show_lines"></span>
			<a class="navbar-brand" style="padding-top: 7px;">钉钉办公管理系统</a>
		</div>

		<div class="navbar-right">
			<div class="navbar-menus">
				<!--
					消息通知，可作为待审批的记录条数
				-->
				<div class="btn-group" id="alert_menu">
					<button type="button" class="dropdown-toggle" data-toggle="dropdown">
						<span class="glyphicons glyphicons-bell"></span>
						<b>3</b>
					</button>
				</div>
				<div class="btn-group" id="comment_menu"></div>
				<!-- 个人信息 -->
				<div class="btn-group" id="toggle_sidemenu_r">
					<button type="button"> <span class="glyphicons glyphicons-parents"></span></button>
				</div>
			</div>
		</div>
	</header>
	<div id="main">
		<aside id="sidebar_left" >
			<!-- 登录用户基本信息 -->
			<div class="user-info">
				<div class="media">
					<a class="pull-left" href="#">
						<div class="media-object border border-purple br64 bw2 p2" data-toggle="modal" data-target="#avatar-modal">
							<img id="photo" class="br64" src="img/timg.gif" alt="...">
						</div>

					</a>
					<div class="mobile-link"><span class="glyphicons glyphicons-show_big_thumbnails"></span></div>
					<div class="media-body">
						<h5 class="media-heading mt5 mbn fw700 cursor">${user.realname}<span class="caret ml5"></span></h5>
						<div class="media-links fs11">
							<a href="#">${user.dname}</a><i class="fa fa-circle text-muted fs3 p8 va-m"></i>
							<a href="loginOut">退出</a>
						</div>
					</div>
				</div>
			</div>
			<div class="sidebar-menu">
				<ul class="nav sidebar-nav">
					<li>
						<a  class="accordion-toggle" href="setting/selectEfficientDepartment?dstatus=0" target="right"> <span class="glyphicons glyphicons-user"></span><span
							 class="sidebar-title">部门管理</span><span class="caret"></span></a>
					</li>
					<li>
						<a class="accordion-toggle" href="userController/selectEmployee?estatus=0" target="right"><span class="glyphicons glyphicons-user"></span><span
							 class="sidebar-title">用户管理</span><span class="caret"></span></a>
					</li>

					<li>
						<a class="accordion-toggle" href="noteController/selectAllNote?estatus=0" target="right"><span class="glyphicons glyphicons-user"></span><span
							 class="sidebar-title">请假管理</span><span class="caret"></span></a>
					</li>
					<li>
						<%--admin-content--%>
						<a class="accordion-toggle" href="javascript:void(0)" onclick="updatePwd('修改密码',1)" target="right"><span class="glyphicons glyphicons-user"></span><span
							 class="sidebar-title">修改密码</span><span class="caret"></span></a>
					</li>
				</ul>
			</div>
		</aside>

		<div style="position: absolute;left: 230px;width:88%;">
			<iframe scrolling="yes" frameborder="0" name="right" src="myinfo.jsp" width="100%" height="900"></iframe>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="myplugs/js/plugs.js"></script>
	<script type="text/javascript">
		//添加编辑弹出层
		function updatePwd(title, id) {
			$.jq_Panel({
				title: title,
				iframeWidth: 500,
				iframeHeight: 300,
				url: "updatePwd.jsp"
			});
		}
	</script>
	<!-- 更换头像 -->
	<div class="user_pic" style="margin: 10px;">
		<img src="">
	</div>

	<div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog"
	 tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<form class="avatar-form" id="myForm" name="photo">
					<!-- 模态框头部 -->
					<div class="modal-header">
						<!-- 关闭按钮 -->
						<button class="close" data-dismiss="modal" type="button">&times;</button>
						<!-- 模态框标题 -->
						<h4 class="modal-title" id="avatar-modal-label">更换头像</h4>
					</div>
					<!-- 模态框内容 -->
					<div class="modal-body">
						<div class="avatar-body">
							<!-- 选择头像区域 -->
							<div class="avatar-upload">
								<input class="avatar-src" name="avatar_src" type="hidden">
								<input class="avatar-data" name="avatar_data" type="hidden">
								<!--<label for="avatarInput" style="line-height: 35px;">选择图片</label>-->
								<button class="btn btn-danger" type="button" style="height: 35px;" onClick="$('input[id=avatarInput]').click();">请选择图片</button>
								<span id="avatar-name"></span>
								<input class="avatar-input hide" id="avatarInput" <%--name="avatar_file"--%> name="photo" type="file">
							</div>
							<div class="row">
								<div class="col-md-9">
									<div class="avatar-wrapper"></div>
								</div>
								<div class="col-md-3">
									<div class="avatar-preview preview-lg" id="imageHead"></div>
								</div>
							</div>
							<div class="row avatar-btns">
								<div class="col-md-4">
									<div class="btn-group">
										<button class="btn btn-danger fa fa-undo" data-method="rotate" data-option="-90" type="button" title="Rotate -90 degrees">
											向左旋转</button>
									</div>
									<div class="btn-group">
										<button class="btn  btn-danger fa fa-repeat" data-method="rotate" data-option="90" type="button" title="Rotate 90 degrees">
											向右旋转</button>
									</div>
								</div>
								<div class="col-md-5" style="text-align: right;">
									<button class="btn btn-danger fa fa-arrows" data-method="setDragMode" data-option="move" type="button" title="移动">
										<span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="$().cropper(&quot;setDragMode&quot;, &quot;move&quot;)">
										</span>
									</button>
									<button type="button" class="btn btn-danger fa fa-search-plus" data-method="zoom" data-option="0.1" title="放大图片">
										<span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="$().cropper(&quot;zoom&quot;, 0.1)">
										</span>
									</button>
									<button type="button" class="btn btn-danger fa fa-search-minus" data-method="zoom" data-option="-0.1" title="缩小图片">
										<span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="$().cropper(&quot;zoom&quot;, -0.1)">
										</span>
									</button>
									<button type="button" class="btn btn-danger fa fa-refresh" data-method="reset" title="重置图片">
										<span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="$().cropper(&quot;reset&quot;)"
										 aria-describedby="tooltip866214"></span>
									</button>
								</div>
								<div class="col-md-3">
									<button class="btn btn-danger btn-block avatar-save fa fa-save" id="saveImg" type="button" data-dismiss="modal"> 保存修改</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
<script type="text/javascript" src="js/jquery-3.6.0.min.js" ></script>
	<script src="js/html2canvas.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		//做个下简易的验证  大小 格式
		$('#avatarInput').on('change', function(e) {
			var filemaxsize = 1024 * 5; //5M
			var target = $(e.target);
			alert(target[0].id);
			var Size = target[0].files[0].size / 1024;
			if (Size > filemaxsize) {
				alert('图片过大，请重新选择!');
				$(".avatar-wrapper").childre().remove;
				return false;
			}
			if (!this.files[0].type.match(/image.*/)) {
				alert('请选择正确的图片!')
			} else {
				var filename = document.querySelector("#avatar-name");
				var texts = document.querySelector("#avatarInput").value;
				var teststr = texts; //你这里的路径写错了
				testend = teststr.match(/[^\\]+\.[^\(]+/i); //直接完整文件名的
				filename.innerHTML = testend;
			}

		});

		$(".avatar-save").on("click", function() {
			var img_lg = document.getElementById('imageHead');
			// 截图小的显示框内的内容
			html2canvas(img_lg, {
				allowTaint: true,
				taintTest: false,
				onrendered: function(canvas) {
					canvas.id = "mycanvas";
					//生成base64图片数据
					var dataUrl = canvas.toDataURL("image/jpeg");
					var newImg = document.createElement("img");
					newImg.src = dataUrl;
					imagesAjax(dataUrl)
				}
			});
		})

		function imagesAjax(src) {
		/*	alert(src)
			var formData = new FormData(src);*/
			var data = {};
			data.img = src;
			data.jid = $('#jid').val();
			 var formData =new  FormData($("#myForm")[0]);
			$.ajax({
				url: "upload_logo.do",
				ContentType : 'multipart/form-data',
				cache : false,
				contentType : false,
				processData : false,
				data: formData,
				type: "POST",
				success: function(re) {
					alert(re)
					if (re != null) {
						$("#photo").attr("src", re);
					}
				}
			});
			/*$.ajax({
				url: "upload_logo.do",
				/!*encType: 'multipart/form-data',
				cache : false,
				async: false,
				processData : false,
				contentType : false,*!/
				data:formData,
				type: "POST",
				traditional: true,
				dataType:'json',
				success: function(re) {
					alert(re);
					if (re != null) {
						$("#photo").attr("src", re);
					}
				}
			});*/
		}
	</script>
	</body>
</html>
