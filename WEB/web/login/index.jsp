<%--
  Created by IntelliJ IDEA.
  User: phil
  Date: 4/20/17
  Time: 12:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <base href="../">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%--bootstrap core--%>
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <%--bootstrap theme--%>
    <link href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet">

    <link href="theme.css" rel="stylesheet">
    <link href="login/login.css" rel="stylesheet">

    <title>Login</title>
  </head>
  <script src="Test.js"></script>
  <script>
      document.getElementById("password").onkeydown = function(e) {
          if (e.keyCode == 13) {
              UserAction();
          }
      }
  </script>
  <body>
  <div class="container">
      <form action="" method="post" class="form-signin">
        <h2 class="form-signin-heading"> Please Sign in</h2>
        <input type="username" id="username" class="form-control" placeholder="Username" name="username" required autofocus>
        <input type="password" id="password" class="form-control" placeholder="Password" name="password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me">Remember me
          </label>
        </div>
        <button type="button" onclick="UserAction()" class="btn btnDark">Login</button>
        <a href="signup/index.jsp" class="btn btnDark">Sign up</a>
        <p id="test"></p>
      </form>
  </div>
  </body>
</html>
