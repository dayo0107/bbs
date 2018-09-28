<%--@elvariable id="subject" type="org.apache.shiro.subject.Subject"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <script src="${pageContext.request.contextPath}/static/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <title>admin</title>
</head>
<body>
    <div class="text-center">
        <h2>后台管理系统</h2>
    </div>
    <div class="text-center">
        <p>${subject.principal},${msg}</p>
    </div>

    <table class="table">
        <tr>
            <th>UID</th>
            <th>Name</th>
            <th>State</th>
            <th>更改权限</th>
        </tr>
        <c:forEach items="${users}" var="u" >
            <tr>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>
                    <c:if test="${u.state == 1}" >管理员</c:if>
                    <c:if test="${u.state == 2}" >正常用户</c:if>
                    <c:if test="${u.state == 3}" >封号</c:if>
                    <c:if test="${u.state == 4}" >禁言</c:if>
                </td>
                <td>
                    <c:if test="${u.state != 1}" >
                    <a href="<c:url value="/admin/banUser"><c:param name="id" value="${u.id}"/> </c:url>">Ban</a>
                    <a href="<c:url value="/admin/muteUser"><c:param name="id" value="${u.id}"/> </c:url>">Mute</a>
                    <a href="<c:url value="/admin/releaseUser"><c:param name="id" value="${u.id}"/> </c:url>">Release</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
  <jsp:include page="/WEB-INF/jsp/admin/inlcude_pageUtil.jsp"/>
</body>
</html>
