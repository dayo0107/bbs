<%--
  Created by IntelliJ IDEA.
  User: jj
  Date: 2018/9/21
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/static/css/style.css" />
    <title>Post</title>
</head>
<body class="box">
    <jsp:include page="include_top.jsp"/>

    <!-- 显示主题 -->
    <table class="t_post" cellspacing="0">
        <tr class="title">
            <td width="10%">1楼</td>
            <td class="navBar">${post.title}</td>
        </tr>
        <tr>
            <td class="content"></td>
            <td><pre>${post.content}</pre></td>
        </tr>
        <tr class="info">
            <td >
                <shiro:hasRole name="admin">
                    <a href="${pageContext.request.contextPath}/home/deletePost?pid=${post.id}">del</a>
                </shiro:hasRole>
            </td>
            <td >
                <span  class="green-r">${post.user.username}</span>
                <span  class="grey-r">${post.createDate}</span>
            </td>
        </tr>
    </table>
    <!-- 显示回复列表 -->
    <c:forEach items="${post.replies}" var="r" >
        <table class="t_post" cellspacing="0">
            <tr class="title">
                <td class="num"> ${r.index_+1}楼</td>
                <td></td>
            </tr>
            <tr class="content">
                <td width="10%"></td>
                <td>
                    <c:choose>
                        <c:when test="${r.user.state == 2 || r.user.state == 1}"><pre>${r.content}</pre></c:when>
                        <c:otherwise><pre class="red">This account has been muted</pre></c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr class="info" >
                <td>
                    <shiro:hasRole name="admin">
                        <a href="${pageContext.request.contextPath}/post/deleteReply?rid=${r.id}&pid=${post.id}">del</a>
                    </shiro:hasRole>
                </td>
                <td >
                    <shiro:hasRole name="admin">
                        <a href="${pageContext.request.contextPath}/admin/muteFore?uid=${r.user.id}&pid=${post.id}">&nbsp;&nbsp;mute</a>
                    </shiro:hasRole>
                    <span  class="green-r">${r.user.username}</span>
                    <span  class="grey-r">${r.createDate}</span>
                </td>
            </tr>
        </table>
    </c:forEach>
    <%--pageUtil--%>
    <div style="text-align:center">
        <a href="?start=0${page.param}">首  页</a>
        <a href="?start=${page.start-page.count}${page.param}">上一页</a>
        <a href="?start=${page.start+page.count}${page.param}">下一页</a>
        <a href="?start=${page.last}${page.param}">末  页</a>
    </div>
    <%--回复主题--%>
    <form action="${pageContext.request.contextPath}/post/addReply" >
        <table class="t_post">
            <tr>
                <td>
                    <textarea class="textarea" name="content" title="内容" placeholder="限制255字符"></textarea>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <input type="submit" value="回 复" />
                    <input type="hidden" name="pid" value="${post.id}">
                </td>
            </tr>
        </table>

    </form>

</body>
</html>
