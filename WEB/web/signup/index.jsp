<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 4/28/17
  Time: 12:14 PM
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

    <title>Signup</title>
</head>
<script src="signup/signup.js"></script>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-6">
            <form action="" method="post" class="form-signin">
                <h3 class="form-signin-heading">Account Info</h3>

                <p>Enter your email:</p>
                <input type="email" id="email" class="form-control" placeholder="john@stuff.net" name="email" required>
                <p>Enter a username:</p>
                <input type="username" id="username" class="form-control" placeholder="Username" name="username" required>
                <p>Account type:</p>
                <input type="radio" name="account-type" id="radio-customer" checked>
                <label for="radio-customer" class="text">Customer</label><br>
                <input type="radio" name="account-type" id="radio-establishment">
                <label for="radio-establishment" class="text">Business Owner</label>
            </form>
        </div>
        <div class="col-sm-6">
            <form action="" method="post" class="form-signin">
                <h3 class="form-signin-heading">Password</h3>
                <p>Enter a password: (Must contain an uppercase, a lowercase, a number the sacrafice of a first born, and the blood of a demi-god)</p>
                <input type="password" id="password" class="form-control" placeholder="Password" name="password" required>
                <input type="password" id="password-confirm" class="form-control" placeholder="Retype Password" name="password-confirm" required>
                <div class="checkbox">
                    <label>
                        <input type="checkbox">I am not a robot
                    </label>
                    <br><br>
                    <button type="button" class="btn btnDark" onclick="sendRegisterRequest()">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>