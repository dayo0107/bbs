<%--
  Created by IntelliJ IDEA.
  User: jj
  Date: 2018/9/19
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/static/css/style.css" />
    <title>注册</title>
</head>
<body>
<div class="showing">
    <form method="POST" action="addUser">
        <table class="t_center">
            <tr>
                <td><label for="username">用户名:</label></td>
                <td><input type="text" name="username" id="username" /></td>
            </tr>
            <tr>
                <td><label for="password">密码:</label></td>
                <td><input type="password" name="password" id="password" /></td>
            </tr>
            <tr>
                <td><label for="email">邮箱:</label></td>
                <td><input type="email" name="email" id="email"/></td>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="注册" /></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
