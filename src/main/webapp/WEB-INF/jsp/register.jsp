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
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery/2.0.0/jquery.min.js"></script>

    <script type="text/javascript" >
        var countdown=60;
        function settime(obj) {
            if (countdown === 0) {
                obj.removeAttribute("disabled");
                obj.value="发送激活邮件";
                countdown = 60;
                return;
            } else {
                obj.setAttribute("disabled", true);
                obj.value="重新发送(" + countdown + ")";
                countdown--;
            }
            setTimeout(function() {settime(obj) },1000)
        }
    </script>
    <title>注册</title>
</head>
<body>
    <div class="showing">
        <form method="POST" >
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
                    <td colspan="2" align="center"><input id="sender" class="sendmail" type="button" value="发送激活邮件" onclick="settime(this)" ></td>
                </tr>
            </table>
        </form>

        <div id="messageDiv" class="red"></div>
        <script>
            <%--AJAX--%>
            $(function() {
                $("input.sendmail").click(function () {
                    var page = "addUser";
                    var name = $("#username").val();
                    var password = $("#password").val();
                    var email = $("#email").val();
                    $.post(
                        page,
                        {"username": name, "password": password ,"email": email},
                        function (result) {
                            $("#messageDiv").html("提示:"+result);
                        }
                    );
                });
            });
        </script>
    </div>
</body>
</html>
<%--<form method="POST" action="addUser">--%>
    <%--<table class="t_center">--%>
        <%--<tr>--%>
            <%--<td><label for="username">用户名:</label></td>--%>
            <%--<td><input type="text" name="username" id="username" /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><label for="password">密码:</label></td>--%>
            <%--<td><input type="password" name="password" id="password" /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><label for="email">邮箱:</label></td>--%>
            <%--<td><input type="email" name="email" id="email"/></td>--%>
        <%--<tr>--%>
            <%--<td colspan="2" align="center"><input id="sender" type="submit" value="发送激活邮件" /></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</form>--%>
