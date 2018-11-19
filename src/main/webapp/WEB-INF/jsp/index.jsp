<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/static/css/style.css" />
    <title>hi!BBS入口</title>
</head>
<body  >
        <div class="showing">
        <h2>hi!BBS</h2><br/>

            <c:if test="${empty user.username}">
                <a href="login">登录</a><br/>
            </c:if>
            <c:if test="${!empty user.username}">
                <span class="desc">你好，${user.username}。</span>
                <a href="${pageContext.request.contextPath}/doLogout">退出</a><br/>
            </c:if>

            <div style="margin-bottom: 15px"></div>

            <a href="register">注册新账号</a><br>
            <a href="${pageContext.request.contextPath}/home">BBS (需要登陆)</a>
        </div>

</body>
</html>
