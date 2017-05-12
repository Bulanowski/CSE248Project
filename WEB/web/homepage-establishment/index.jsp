<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 5/3/17
  Time: 9:26 AM
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
    <script src="main.js"></script>

    <title>Establishment Home</title>
</head>
<body style="padding-top: 0px">

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
            <li><a href="homepage-establishment/settings/">Account Settings</a></li>
            <li><a href="" onclick="signOut()">Sign Out</a></li>
        </ul>
    </div>
</nav>
<div class="row">
    <div class="col-sm-4">
        <h2 class="header">Financial Overview</h2>
    </div>
    <div class="col-sm-8"><img src="images/FinancialChart.jpeg" alt="NO IMUG" style="width:700px;height:350px;"></div>
</div>
<div class="row">
    <div class="col-sm-4">Stuff</div>
    <div class="col-sm-8">Things</div>
</div>
</body>
</html>
