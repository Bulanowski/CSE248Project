<%--
  Created by IntelliJ IDEA.
  User: phil
  Date: 5/10/17
  Time: 3:08 PM
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
    <link href="homepage/search/search.css" rel="stylesheet">

    <script src="main.js"></script>
    <script src="homepage/search/search.js"></script>

    <title>Search</title>
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
<div id="event-container" class="container">
    <p id="noResults" style="display: none">No results found.</p>
    <ul id="eventList">
        <li id="event1"style="padding: 20px; display: none">
            <div onclick="" class="searchEventBorder">
                <div class="first">
                    <img class="searchEventImg" src="images/icon.jpg">
                    <p class="searchTitle">Title Paragraph</p>
                </div>
                <div class="second">
                    <p class="searchDescription">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas dictum neque at eros tempor, id dictum est cursus. Phasellus finibus arcu sit amet est fermentum, ac iaculis arcu venenatis. In eu maximus quam, non dapibus ante. Aenean posuere ligula pellentesque, mattis mi eu, efficitur tortor. Curabitur imperdiet justo ut lacinia ullamcorper. Duis id orci a nisi iaculis ornare ullamcorper vel libero. Maecenas leo sem, tempor eu laoreet vel, luctus ut est. Donec suscipit tristique luctus. In in efficitur libero, varius hendrerit mauris.
                    </p>
                </div>
            </div>
        </li>


    </ul>
</div>
<div class="container page-container">
    <a onclick="" class="page" style="display:none;">1</a>
</div>
</body>
    <script>
        getSearch(1)
    </script>

</html>
