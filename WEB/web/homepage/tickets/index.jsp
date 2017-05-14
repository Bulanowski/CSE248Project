<%--
  Created by IntelliJ IDEA.
  User: phil
  Date: 5/13/17
  Time: 6:10 PM
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
    <link href="homepage/tickets/tickets.css" rel="stylesheet">

    <script src="main.js"></script>
    <script src="homepage/tickets/tickets.js"></script>
    <script src="homepage/search/search.js"></script>
    <title>Tickets</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-custom">
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <li><a href="homepage/"><span class=""></span> Home</a></li>
                <li class="nav-item"><a disabled="true" class="nav-link active"><span class="glyphicon-envelope"></span>
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
<h1 class="noTickets">No Tickets purchased.</h1>
<div>
    <ol  id="ticketList" style="padding: 0%">
        <li id="ticket1" style="display: none" class="listItems">
            <div  class="info" style="margin-left: 50px">
                <h2 class="event">Event Name</h2>
                <p class="amount">$ Amount</p>
                <div style="display: inline-flex;">
                    <p class="date" style="margin-right: 80px"> Date</p>
                    <p class="time">Time</p>
                </div>
            </div>
            <div class="">
                <p class="ticketID" style="margin-top: 10px">ID# 12345678</p>
                <button class="btn btnBright cancelBtn">Cancel Ticket</button>
                <div class="assuranceText w3-animate-right" style="display: none">
                <p>Are You Sure?</p>
                    <div style="display: inline-flex">
                        <button class="btn btnBright yes">Yes</button>
                        <button class="btn btnBright  no">No</button>
                    </div>
                </div>
            </div>
        </li>
    </ol>
</div>
</body>
    <script>getTickets()</script>
</html>
