<%--
  Created by IntelliJ IDEA.
  User: jj
  Date: 2018/9/20
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/static/css/style.css" />
    <title>hi!BBS</title>
</head>
<body class="box">
    <jsp:include page="include_top.jsp"/>
    <!-- 主题列表 -->
    <table class="t_center">
        <tbody >
            <!--显示表头-->
            <tr class="biaotou">
                <td width="40">编号</td>
                <td width="40">回复</td>
                <td width="500">标题</td>
                <td width="110">作者</td>
                <td width="145">最后回复时间</td>
            </tr>
                <!-- 显示主题列表 -->
                <c:forEach items="${posts}" var="p">
                <tr class="data">
                    <td >${p.id} </td>
                    <td >${p.replyNum}</td>
                    <td><a href="${pageContext.request.contextPath}/home/showPost?pid=${p.id}">${p.title}</a></td>
                    <td >${p.uid}</td>
                    <td >${p.lastDate}</td>
                </tr>
                </c:forEach>
            <tr>
                <td colspan="5" class="num">共有主题数<font color="red">list.size</font>个</td>
            </tr>
        </tbody>
    </table>

    <div style="margin-bottom: 15px"></div>
    <!--发表主题表单-->
    <form action="${pageContext.request.contextPath}/home/addPost">
        <table class="t_post">
            <tr>
                <td align="center">
                 标题<input type="text" name="title" title="标题">
                </td>
            </tr>
            <tr>
                <td>
                    <textarea class="textarea" name="content" title="内容" placeholder="限制255字符"></textarea>
                </td>
            </tr>
            <tr>
                <td align="right"><input type="submit" value="发　表" /></td>
            </tr>
        </table>
    </form>

</body>
</html>
