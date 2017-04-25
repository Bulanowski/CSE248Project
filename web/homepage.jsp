<%--
  Created by IntelliJ IDEA.
  User: phil
  Date: 4/23/17
  Time: 1:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%--bootstrap core--%>
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <%--bootstrap theme--%>
    <link href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet">

    <link href="theme.css" rel="stylesheet">

    <script src="Test.js"></script>



    <title>Home</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-custom">
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <li class="nav-item"><a href="#" class="nav-link active"><span class="glyphicon-envelope"></span>
                    Tickets</a>
                </li>
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> Account Settings</a></li>
                <li>
                    <form class="nav navbar-form">
                        <div class="form-group">
                            <input type="text" placeholder="Search" class="form-control searchbar">
                            <button type="submit" class="btn btnDark">Submit</button>
                        </div>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div>
    <p class="btnDark" id="username"></p>
</div>
</body>
<script>getUserName()</script>
</html>
