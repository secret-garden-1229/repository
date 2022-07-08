<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
    <base href="<%=path%>">
    <title>测试</title>
</head>
<body>
<form action="uploac" method="post" enctype="multipart/form-data">
    上传文件： <input type="file" name="multipartFile">
    <input type="submit" value="提交">
</form>
</body>
</html>
