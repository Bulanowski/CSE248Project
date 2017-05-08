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
                <%--<h1 class="form-signin-heading">Register Account</h1>--%>
                <h3 class="form-signin-heading">Account Info</h3>

                    <p>Enter your email:</p>
                    <input type="email" id="email" class="form-control" placeholder="john@stuff.net" name="email" required>
                    <p>Enter a username:</p>
                    <input type="username" id="username" class="form-control" placeholder="Username" name="username" required>
                    <p>Enter a password: (Must contain an uppercase, a lowercase, a number the sacrafice of a first born, and the blood of a demi-god)</p>
                    <input type="password" id="password" class="form-control" placeholder="Password" name="password" required>
                    <p>Confirm password:</p>
                    <input type="password" id="password-confirm" class="form-control" placeholder="Confirm Password" name="password-confirm" required>
                    <p>Account type</p>
                    <input type="radio" id="radio-customer"  name="account-type" value="Customer" checked> Customer
                    <input type="radio" id="radio-establishment" name="account-type" value="Establishment"> Business Owner <br>
            </form>
        </div>
        <div class="col-sm-6">
            <form action="" method="post" class="form-signin">
                <b></b><b></b>
                <h3 class="form-signin-heading">Account Owner Info</h3>

                <p>Address</p>
                <input id="address" type="text" class="form-control" placeholder="42 University St." name="address" required>
                <p>Zip Code</p>
                <input type="number" id="zip" class="form-control" placeholder="11111" name="zip" required>
                <p>Birthday</p>
                <input type="date" id="birthday" class="form-control"  name="birthday" required>
                <p>Phone Number</p>
                <input type="tel" id="phone" class="form-control" name="phone" placeholder="631-222-1984" required>
                <div class="checkbox">
                    <label>
                        <input type="checkbox">I am not a robot
                    </label>
                </div>
                <button type="button" class="btn btnDark" onclick="sendRegisterRequest()">Submit</button>
                <a href="homepage-establishment/index.jsp" class="btn btnDark">Establishment home</a>
            </form>
        </div>
    </div>
</div>

</body>
</html>