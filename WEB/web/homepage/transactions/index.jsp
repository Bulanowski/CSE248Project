<%--
  Created by IntelliJ IDEA.
  User: phil
  Date: 5/15/17
  Time: 8:44 PM
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
    <link href="homepage/transactions/transactions.css" rel="stylesheet">

    <script src="main.js"></script>
    <script src="homepage/search/search.js"></script>
    <script src="homepage/transactions/transactions.js"></script>
    <title>Transactions</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-custom">
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <li><a href="homepage/"><span class=""></span> Home</a></li>
                <li class="nav-item"><a href="homepage/tickets" class="nav-link active"><span class="glyphicon-envelope"></span>
                    Tickets</a>
                </li>
                <li><a href="homepage/transactions" disabled="true"><span class=""></span>Transactions</a></li>
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
<h1 class="noTransaction">No Transactions Made.</h1>
<div>
    <ol  id="transactionList" style="padding: 0%">
        <li id="transaction" class="listItems" style="display: none">
            <div  class="info" style="margin-left: 50px">
                <h2 class="event">Event Name</h2>
                <p class="amount">$ Amount</p>
                <div style="display: inline-flex;">
                    <p class="date" style="margin-right: 80px"> Date</p>
                </div>
            </div>
            <div class="">
                <p class="transactionID" style="margin-top: 10px">ID# 12345678</p>
                    <p class="sender">Sender: Business</p>
                    <p class="recipient">Recipient: Account Name</p>
                </div>
        </li>
    </ol>
</div>

</body>
    <script>getTransactions()</script>
</html>
