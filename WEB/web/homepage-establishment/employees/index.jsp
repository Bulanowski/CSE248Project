<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 5/15/17
  Time: 8:36 PM
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
    <link href="homepage-establishment/employees/employees.css" rel="stylesheet">

    <script src="main.js"></script>
    <script src=""></script>

    <title>Employee Management</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="homepage-establishment/business">Buissness Name</a>
        </div>
        <ol class="nav navbar-nav">
            <li><a href="homepage-establishment/">Home</a></li>
            <li><a href="homepage-establishment/eventEditor/">Create Event</a></li>
            <li><a href="#">View Planned Events</a></li>
            <li class="active"><a disabled="true">Manage Employees</a></li>
            <li><a href="#">Finances</a></li>
            <li><a href="homepage-establishment/settings/">Account Settings</a></li>
            <li><a href="" onclick="signOut()">Sign Out</a></li>
        </ol>
    </div>
</nav>

<table style="width:100%">
    <h1>Employee History</h1>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Position</th>
        <th>Salary/Payment</th>  <%--Should be 2 options: "Salary" or "Payment"--%>

        <th>$ Amount</th>
        <th>Employment Status</th> <%--Should only be "Employed", "Contract Over", "Quit", or "Terminated" --%>
    </tr>
    <tr>
        <td>Jill</td>
        <td>Smith</td>
        <td>Bartender</td>
        <td>Salary</td>
        <td>60,000</td>
        <td>Employed</td>
    </tr>
    <tr>
        <td>Bob</td>
        <td>Builder</td>
        <td>Construction</td>
        <td>Contracted</td>
        <td>4,500</td>
        <td>Contract Over</td>
    </tr>
    <tr>
        <td>Ted</td>
        <td>Bill</td>
        <td>Dishwasher</td>
        <td>Salary</td>
        <td>26,000</td>
        <td>Terminated</td>
    </tr>
</table>

</body>
</html>
