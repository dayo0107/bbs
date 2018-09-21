<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="showing"style="margin: 15px auto; " >
    <!-- 搜索表单 -->
    <form action="${pageContext.request.contextPath}/search#search"   >
        <a href="${pageContext.request.contextPath}/home"><span class="navBar">hi!BBS</span></a>
        <input type="text" name="keyword" placeholder="无关键字则返回主页">
        <input type="submit" value="搜 索" />
        <span >
                <c:if test="${!empty subject.principal}">
                    <span class="desc">你好，${subject.principal}。</span>
                    <a href="${pageContext.request.contextPath}/doLogout">退出</a>
                </c:if>
             </span>
    </form>
</div>
