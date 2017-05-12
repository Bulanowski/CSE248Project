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
    <link href="theme.css" rel="stylesheet">
    <%--<link href="homepage/homepage.css?v=0.0.7" rel="stylesheet">--%>

    <script src="main.js"></script>
    <script src="homepage-establishment/settings/settings.js"></script>
    <title>Settings</title>
</head>
<body>
<%--<div class="container">--%>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Buissness Name</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a disabled="true">Home</a></li>
                <li><a href="homepage-establishment/eventEditor/">Create Event</a></li>
                <li><a href="#">View Planned Events</a></li>
                <li><a href="#">Manage Employees</a></li>
                <li><a href="#">Finances</a></li>
                <li class="active"><a disabled="true">Account Settings</a></li>
                <li><a href="" onclick="signOut()">Sign Out</a></li>
            </ul>
        </div>
    </nav>
<%--</div>--%>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <form action="" method="post" class="form-signin">
                <h2>Change Password</h2>
                <input type="password" id="old-password" class="form-control" placeholder="Old Password" name="password">
                <input type="password" id="password" class="form-control" placeholder="New Password" name="password">
                <input type="password" id="password-confirm" class="form-control" placeholder="Retype new Password" name="password-confirm">
                <button type="button" class="btn btnDark" onclick="sendChangePasswordRequest()">Change Password</button>
            </form>
        </div>
        <div class="col-sm-4">
            <form action="" method="post" class="form-signin">
                <h2>Business Info</h2>
                <p>Business Name</p>
                <input type="text" id="name" name="name" class="form-control" placeholder="Business Name">
                <p>Business Phone Number</p>
                <input type="number" id="phone" name="phone" class="form-control" placeholder="Phone Number">
                <p>Business Address</p>
                <input type="text" id="address" name="address" class="form-control" placeholder="Street Address">
                <input type="number" id="zip" name="zip" class="form-control" placeholder="Zip Code">
                <button type="button" class="btn btnDark" onclick="">Update Info</button>
            </form>
        </div>
        <div class="col-sm-4">
            <form action="" method="post" class="form-signin">
                <h2>Hours</h2>
                <p>Open</p>
                <input type="time" id="time-open" name="time-close" class="form-control">
                <p>Close</p>
                <input type="time" id="time-close" name="time-close"class="form-control">
                <button type="button" class="btn btnDark" onclick="">Change Hours</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
