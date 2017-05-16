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
    <script src="main.js"></script>
    <script src="homepage-establishment/eventEditor/EventEditor.js"></script>

    <title>Editor</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="homepage-establishment/business">Buissness Name</a>
        </div>
        <ol class="nav navbar-nav">
            <li><a href="homepage-establishment/">Home</a></li>
            <li class="active"><a disabled="true">Create Event</a></li>
            <li><a href="#">View Planned Events</a></li>
            <li><a href="homepage-establishment/employees/">Manage Employees</a></li>
            <li><a href="#">Finances</a></li>
            <li><a href="homepage-establishment/settings/">Account Settings</a></li>
            <li><a href="" onclick="signOut()">Sign Out</a></li>
        </ol>
    </div>
</nav>
    <div class="container">
        <form action="" method="post">
            <p>Event Name</p>
            <input type="text" id="name" class="form-control" style="max-width: 500px" />
            <p>Description</p>
            <textarea id="description" rows="4" cols="50" class="form-control" style="resize:none"></textarea>
            <p>Image</p>
            <input type="text" id="imageSrc" class="form-control" style="max-width: 500px" />
            <div class="row">
                <div class="col-md-6">
                    <p>Date</p>
                    <input type="date" id="date" class="form-control" placeholder="mm/dd/yyyy" required />
                </div>
                <div class="col-md-6">
                    <p>Time</p>
                    <input type="time" id="time" class="form-control" placeholder="11111" required />
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <p>Price</p>
                    <input type="number" id="price" class="form-control"   required />
                </div>
                <div class="col-md-6">
                    <p>Tickets</p>
                    <input type="number" id="maxTickets" class="form-control"  placeholder="" required />
                </div>
            </div>
            <button type="button" class="btn btnDark" onclick="sendRegisterEventRequest()">Submit</button>
        </form>
    </div>
</body>
</html>
