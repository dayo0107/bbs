<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css" />

<div class="showing">
    <h2>权限不足</h2>
    ${ex.message}
    <br>
    <a href="#" onClick="history.back()">返回</a>
</div>