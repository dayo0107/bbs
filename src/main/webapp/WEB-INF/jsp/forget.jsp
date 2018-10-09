<%--
  Created by IntelliJ IDEA.
  User: jj
  Date: 2018/10/1
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/static/css/style.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery/2.0.0/jquery.min.js"></script>
    <title>forget</title>
    <script>
        var countdown=60;
        function settime(obj) {
            if (countdown === 0) {
                obj.removeAttribute("disabled");
                obj.value="发送验证码邮件";
                countdown = 60;
                return;
            } else {
                obj.setAttribute("disabled", true);
                obj.value="重新发送验证邮件(" + countdown + "S)";
                countdown--;
            }
            setTimeout(function() {settime(obj) },1000)
        }
    </script>
    <script type="text/javascript" >
        $(function () {
            $("input.sendmail").click(function () {
                var page = "/login/checkEmail";
                var name = $("#username").val();
                var email = $("#email").val();
                $.post(
                    page,
                    {"username": name ,"email": email},
                    function (result) {
                        if(result === "success"){
                            $("#newpassword").show();
                            $("#resetpassword").show();
                            $("#messageDiv").html("提示:请到邮箱查看验证码" );
                        }
                        else {
                            $("#messageDiv").html("提示:" + result);
                        }
                    }
                );
                return false;
            });
        })
    </script>
</head>
<body>
    <div class="showing">
        <h2>忘记密码</h2>
        <form method="post" action="${pageContext.request.contextPath}/login/alterPassword">
            <table class="t_auto">
                <tr>
                    <td><label for="username" >用户名:</label></td>
                    <td><input type="text" name="username" id="username"  readonly="readonly" value="${username}"/></td>
                </tr>
                <tr>
                    <td><label for="email">邮箱:</label></td>
                    <td><input type="email" name="email" id="email"/></td>
                </tr>
                <tr>
                    <td><label for="code">验证码:</label></td>
                    <td><input type="text" name="code" id="code"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input id="sender" class="sendmail" type="button" value="发送验证码邮件" onclick="settime(this)" ></td>
                </tr>
                <tr id="newpassword" hidden="hidden">
                    <td><label for="password">新密码:</label></td>
                    <td><input type="password" name="password" id="password" /></td>
                </tr>
                <tr id="resetpassword" hidden="hidden">
                    <td colspan="2" align="center"><input type="submit" value="重置密码"></td>
                </tr>
            </table>
        </form>
        <div id="messageDiv" class="red"></div>
    </div>
</body>
</html>
