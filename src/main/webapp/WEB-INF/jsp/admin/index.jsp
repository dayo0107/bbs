<%--@elvariable id="subject" type="org.apache.shiro.subject.Subject"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/static/css/style.css" />
    <title>admin</title>
</head>
<body class="box">
    <div class="showing">
        <h2>后台管理系统</h2>
    </div>
    <div class="showing">
     <p>${subject.principal},${msg}</p>
    </div>
</body>
</html>
