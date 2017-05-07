<%--
  Created by IntelliJ IDEA.
  User: phil
  Date: 5/6/17
  Time: 9:34 PM
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

    <link href="theme.css" rel="stylesheet">
    <link href="homepage-establishment/eventEditor/eventEditor.css" rel="stylesheet">

    <title>Editor</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Buissness Name</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">Create Event</a></li>
            <li><a href="#">View Planned Events</a></li>
            <li><a href="#">Manage Employees</a></li>
            <li><a href="#">Finances</a></li>
            <li><a href="#">Account Settings</a></li>
            <li><a href="#">Signout</a></li>
        </ul>
    </div>
</nav>
    <div class="container">
        <div class="row">
            <p>Event Name</p>
            <input type="text" class="form-control" style="max-width: 500px">
            <p>Description</p>
            <textarea rows="4" cols="50" class="shit form-control"></textarea>
            <p>Date</p>
                <input id="date" type="date" class="form-control" placeholder="mm/dd/yyyy" required>
            <p>Time</p>
            <input type="time" id="time" class="form-control shit" placeholder="11111" required>
            <p>Price</p>
            <input type="number" id="price" class="form-control"   required>
            <p>Tickets</p>
            <input type="number" id="tickets" class="form-control"  placeholder="" required>
        </div>
    </div>
</body>
</html>
