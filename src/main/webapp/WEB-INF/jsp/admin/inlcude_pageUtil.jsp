<%--
  Created by IntelliJ IDEA.
  User: jj
  Date: 2018/9/28
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<nav class="text-center">
    <ul class="pagination">
        <li>
            <a  href="?start=0" aria-label="Previous" >
                <span aria-hidden="true">«</span>
            </a>
        </li>

        <li >
            <a  href="?start=${page.start-page.count}" aria-label="Previous" >
                <span aria-hidden="true">‹</span>
            </a>
        </li>

        <c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">
            <li>
                <a href="?start=${status.index*page.count}" class="current">${status.count}</a>
            </li>
        </c:forEach>

        <li >
            <a href="?start=${page.start+page.count}" aria-label="Next">
                <span aria-hidden="true">›</span>
            </a>
        </li>
        <li >
            <a href="?start=${page.last}" aria-label="Next">
                <span aria-hidden="true">»</span>
            </a>
        </li>
    </ul>
</nav>
