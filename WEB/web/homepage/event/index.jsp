<%--
  Created by IntelliJ IDEA.
  User: phil
  Date: 5/13/17
  Time: 12:14 AM
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
    <link href="homepage/homepage.css" rel="stylesheet">
    <link href="homepage/event/event.css" rel="stylesheet">

    <script src="main.js"></script>
    <title>Event</title>
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
                <li><a href="homepage/settings"><span class="glyphicon glyphicon-user"></span> Account Settings</a></li>
                <li>
                    <div class="nav navbar-form">
                        <div class="form-group">
                            <input id="searchField" placeholder="Search" class=" Testy form-control searchbar">
                            <button onclick="search()" type="submit" class="Testy btn btnDark">Submit</button>
                        </div>
                    </div>
                </li>
                <li><a href="" onclick="signOut()">Sign Out</a></li>
            </ul>
        </div>
    </nav>
</div>


<br>
<div class="container">

    <div class="desc">
        <h1 id="eventName">Event Name</h1>
        <h2 id="businessName">Business Name</h2>
            <p id="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras mauris est, volutpat vel nulla in, sodales consectetur velit. Donec suscipit nisi enim, ut mattis mauris maximus eget. Pellentesque condimentum nulla sit amet nisl feugiat rutrum. Nunc ante enim, pulvinar at metus eu, mattis lacinia justo. Nam sit amet purus orci. In bibendum ultrices eros a aliquam. Nulla ultricies lectus eu augue sed.</p>
            <br>
            <div  class="dateTime" style="display: flex;">
                <p style="margin-right: 20%;">Date</p>
                <p>Time</p>
            </div>
        <button class="btn btnBright">Buy Tickets</button>
    </div>
</div>
</body>
</html>
