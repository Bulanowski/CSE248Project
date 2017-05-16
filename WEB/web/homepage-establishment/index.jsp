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
    <link href="homepage-establishment/homepage-establishment.css" rel="stylesheet">
    <script src="main.js"></script>
    <script src="homepage-establishment/homepage-establishment.js"></script>

    <title>Establishment Home</title>
</head>
<body style="padding-top: 0px">

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="homepage-establishment/business">Buissness Name</a>
        </div>
        <ol class="nav navbar-nav">
            <li class="active"><a disabled="true">Home</a></li>
            <li><a href="homepage-establishment/eventEditor/">Create Event</a></li>
            <li><a href="#">View Planned Events</a></li>
            <li><a href="homepage-establishment/employees/">Manage Employees</a></li>
            <li><a href="#">Finances</a></li>
            <li><a href="homepage-establishment/settings/">Account Settings</a></li>
            <li><a href="" onclick="signOut()">Sign Out</a></li>
        </ol>
    </div>
</nav>
<div class="row">
    <div class="col-sm-4">
        <h2 class="">Financial Overview</h2>
    </div>
    <div class="col-sm-8">
        <h1 class="noUpcomingEvents" style="display: none">No Upcoming Events</h1>
        <h1 class="upcomingEvents" style="">Upcoming Events</h1>
        <ul id="eventList" style="padding: 0%;">
            <li id="event1" class="listItems">
                <div  class="info" style="margin-left: 50px">
                    <h2 class="event">Event Name</h2>
                    <div style="display: inline-flex">
                        <p class="ticketsSold" style="margin-right: 80px">Tickets Sold</p>
                        <p class="ticketsLeft">Tickets Left</p>
                    </div>
                    <br>
                    <div style="display: inline-flex;">
                        <p class="date" style="margin-right: 126px"> Date</p>
                        <p class="time">Time</p>
                    </div>
                </div>

            </li>
            <li>
            </li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">Stuff</div>
    <div class="col-sm-8">Things</div>
</div>
<script>getEventsFromServer()</script>
</body>
</html>
