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
    <link href="homepage-establishment/business/business.css" rel="stylesheet">


    <script src="main.js"></script>
    <script src="homepage-establishment/business/business.js"></script>
    <title>Business</title>
</head>
<body>
<nav class="navbar navbar-inverse" style="margin-top: -40px">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="homepage-establishment/">Buissness Name</a>
        </div>
        <ol class="nav navbar-nav">
            <li><a href="homepage-establishment/">Home</a></li>
            <li><a href="homepage-establishment/eventEditor/">Create Event</a></li>
            <li><a href="#">View Planned Events</a></li>
            <li><a href="homepage-establishment/employees/">Manage Employees</a></li>
            <li><a href="#">Finances</a></li>
            <li><a href="homepage-establishment/settings/">Account Settings</a></li>
            <li><a href="" onclick="signOut()">Sign Out</a></li>
        </ol>
    </div>
</nav>
<br>
<h1 id="noBusiness" class="noBusiness" style="display: none">No Business Found.</h1>
<div id="businessPage" class="container" >

    <div class="business">
        <h2 id="businessName">Business Name</h2>
        <img class="businessImg" src="https://i.imgur.com/1wc10tt.jpg">
        <div style="display: inline-flex">
            <h3 id="location">Location:</h3>
            <p style="margin-left: 10px; margin-top: 13px">123 Main Street, New York, New York, 10012</p>
        </div>
        <br>
        <h3>Description:</h3>
        <div class="descriptionItems">
            <p id="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras mauris est, volutpat vel nulla in, sodales consectetur velit. Donec suscipit nisi enim, ut mattis mauris maximus eget. Pellentesque condimentum nulla sit amet nisl feugiat rutrum. Nunc ante enim, pulvinar at metus eu, mattis lacinia justo. Nam sit amet purus orci. In bibendum ultrices eros a aliquam. Nulla ultricies lectus eu augue sed.</p>
        </div>
        <button type="button" class="btn btnDark" id="showChangeDescriptionButton"  onclick="showChangeDescription()">Change Description</button>
        <div class="changeDescription" style="display: none">
            <form>
                <textarea rows="5" cols="100" id="changeDesc">This is a test of the emergency broadcast system</textarea>
                <button type="button" class="btn btnDark" onclick="">Submit Changes</button>
            </form>
        </div>
        <div>
            <br>
            <h3>Business Hours:</h3>
            <br>
            <TABLE id="businessHours" class="businessHours">
                <tr>
                    <th width="12%">Mon</th>
                    <th width="12%">Tues</th>
                    <th width="12%">Wed</th>
                    <th width="12%">Thurs</th>
                    <th width="12%">Fri</th>
                    <th width="12%">Sun</th>
                    <th width="12%">Sat</th>
                </tr>
                <tr>
                    <td id="mon">1</td>
                    <td id="tues" >2</td>
                    <td id="wed" >3</td>
                    <td id="thurs">4</td>
                    <td id="fri" >5</td>
                    <td id="sat" >6</td>
                    <td id="sun" >7</td>
                </tr>
            </TABLE>
        </div>
        <div class="changeOpenHours">
            <p>Open</p>
            <input type="time" class="hours" style="display: inline-flex">
            <input type="time" class="hours" style="display: inline-flex">
            <input type="time" class="hours" style="display: inline-flex">
            <input type="time" class="hours" style="display: inline-flex">
            <input type="time" class="hours" style="display: inline-flex">
            <input type="time" class="hours" style="display: inline-flex">
            <input type="time" class="hours" style="display: inline-flex">
        </div>
        <br>
        <div class="changeCloseHours">
            <p>Close</p>
            <input type="time" class="hours" style="display: inline-flex">
            <input type="time" class="hours" style="display: inline-flex">
            <input type="time" class="hours" style="display: inline-flex">
            <input type="time" class="hours" style="display: inline-flex">
            <input type="time" class="hours" style="display: inline-flex">
            <input type="time" class="hours" style="display: inline-flex">
            <input type="time" class="hours" style="display: inline-flex">
            <br>
            <button type="button" class="btn btnDark" style="margin-top: 20px" onclick="">Submit Changes</button>
        </div>

        <br>
        <h3>Upcoming Events:</h3>
        <ul>
            <li id="event" class="eventBox">
                <div class="eventDetails">
                    <p class="eventName" style="width: 80%"> Event Name:</p>
                    <p class="date"> Date: 12/31/1999 </p>
                </div>
            </li>

        </ul>
    </div>

</div>
</body>
<script>parseURI()</script>
</html>
