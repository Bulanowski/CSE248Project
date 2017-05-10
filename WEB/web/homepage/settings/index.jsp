<%--
  Created by IntelliJ IDEA.
  User: phil
  Date: 5/9/17
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="../../">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%--bootstrap core--%>
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <%--bootstrap theme--%>
    <link href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet">

    <link href="w3.css" rel="stylesheet">
    <link href="theme.css?v=0.0.7" rel="stylesheet">
    <link href="homepage/homepage.css?v=0.0.7" rel="stylesheet">

    <script src="main.js"></script>
    <title>Settings</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-custom">
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <li><a href="homepage/"><span class=""></span> Home</a></li>
                <li class="nav-item"><a href="#" class="nav-link active"><span class="glyphicon-envelope"></span>
                    Tickets</a>
                </li>
                <li><a href="#"><span class=" active glyphicon glyphicon-user"></span> Account Settings</a></li>
                <li>
                    <form class="nav navbar-form">
                        <div class="form-group">
                            <input placeholder="Search" class=" Testy form-control searchbar">
                            <button type="submit" class="Testy btn btnDark">Submit</button>
                        </div>
                    </form>
                </li>
                <li><a href="" onclick="signOut()">Sign Out</a></li>
            </ul>
        </div>
    </nav>
</div>
<div class="container">
    <form action="" method="post" class="form-signin">
        <p>Change password</p>
        <input type="password" id="oldpassword" class="form-control" placeholder="Old Password" name="password" required>
        <input type="password" id="password" class="form-control" placeholder="New Password" name="password" required>
        <input type="password" id="password-confirm" class="form-control" placeholder="Retype new Password" name="password-confirm" required>
        <div class="checkbox">
        </div>
    </form>
</div>
</body>
</html>
