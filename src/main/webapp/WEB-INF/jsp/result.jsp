<%--
  Created by IntelliJ IDEA.
  User: jj
  Date: 2018/9/19
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/static/css/style.css" />
    <title>Result</title>
</head>
<body>
<div class="showing">
    <h2>hi!BBS: ${msg}</h2><br/>
    <a href="index">目录</a><br/>
    <a href="#" onClick="history.back()">返回</a>
</div>
</body>
</html>
