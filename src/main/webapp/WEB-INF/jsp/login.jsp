<%--
  Created by IntelliJ IDEA.
  User: jj
  Date: 2018/9/20
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/static/css/style.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery/2.0.0/jquery.min.js"></script>
    <title>登陆</title>
    <script>
        $(function () {
            $("input.send").click(function(){
                var page = "/login/checkUsername";
                var username = $("#username").val();
                $.post(
                    page,
                    {"username":username},
                    function(result){
                        if("success"===result){
                            window.location.href="/login/forget?username="+username;
                        }
                        else{
                            $("#messageDiv").html("用户名不存在");
                        }
                    }
                );
            });
            return false;
        });
    </script>
</head>
<body >
    <div class="showing">
        <form method="POST" action="login">
            <table class="t_auto">
                <tr>
                    <td><label for="username">用户名:</label></td>
                    <td><input type="text" name="username" id="username" placeholder="请勿分享账户" /></td>
                </tr>
                <tr>
                    <td><label for="password">密码:</label></td>
                    <td><input type="password" name="password" id="password" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><label for="rememberMe">记住我</label><input type="checkbox" name="rememberMe" id="rememberMe" /></td>
                </tr>
                <tr>
                    <td ><input type="submit" value="登陆" /></td>
                    <td ><input class="send" type="button" value="忘记/修改密码" /></td>
                </tr>
            </table>
        </form>

        <div id="messageDiv" class="red"></div>

    </div>
</body>
</html>
