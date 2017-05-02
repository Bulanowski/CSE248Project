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
    <base href="/CSE248_war_exploded/">
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
        <form action="" method="post" class="form-signin">
            <h1 class="form-signin-heading">Register Account</h1>
            <h3 class="form-signin-heading">Account Info</h3>

            <h4 class="form-signin-heading">Enter your email:</h4>
            <input type="email" id="email" class="form-control" placeholder="john@stuff.net" name="email" required>
            <h4 class="form-signin-heading">Enter a username:</h4>
            <input type="username" id="username" class="form-control" placeholder="Username" name="username" required>
            <h4 class="form-signin-heading">Enter a password: (Must contain an uppercase, a lowercase, a number the sacrafice of a first born, and the blood of a demi-god)</h4>
            <input type="password" id="password" class="form-control" placeholder="Password" name="password" required>
            <h4 class="form-signin-heading">Confirm password:</h4>
            <input type="password" id="password-confirm" class="form-control" placeholder="Confirm Password" name="password-confirm" required>
            <b></b>

            <h3 class="form-signin-heading">Personal Info</h3>

            <h4 class="form-signin-heading">Address</h4>
            <input id="address" type="text" class="form-control" placeholder="42 University St." name="address" required>
            <h4 class="form-signin-heading">Zip Code</h4>
            <input type="number" id="zip" class="form-control" placeholder="11111" name="zip" required>
            <h4 class="form-signin-heading">Birthday</h4>
            <input type="date" id="birthday" class="form-control"  name="birthday" required>
            <h4 class="form-signin-heading">Phone Number</h4>
            <input type="tel" id="phone" class="form-control" name="phone" placeholder="631-222-1984" required>
            <div class="checkbox">
                <label>
                    <input type="checkbox">I am not a robot
                </label>
            </div>
            <button type="button" class="btn btnDark" onclick="CreateAccount()">Submit</button>
        </form>
    </div>
</body>
</html>
