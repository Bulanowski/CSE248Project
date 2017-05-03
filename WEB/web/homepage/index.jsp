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
    <base href="/WEB_war_exploded/">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%--bootstrap core--%>
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <%--bootstrap theme--%>
    <link href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet">

    <link href="theme.css?v=0.0.7" rel="stylesheet">
    <link href="slideshow.css?v=0.0.3" rel="stylesheet">


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
<div class="slideshow-container">
    <div class="mySlides">
        <img class="img-rounded slide" src="Resources/img1.jpg" style="width:100%">
        <div class="text">Caption Text</div>
    </div>

    <div class="mySlides">
        <img class="img-rounded slide" src="Resources/img2.jpg" style="width:100%">
        <div class="text">Caption Two</div>
    </div>

    <div class="mySlides">
        <img class="img-rounded slide" src="Resources/img3.jpg" style="width:100%">
        <div class="text">Caption Three</div>
    </div>

    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>
<br>

<div style="text-align:center">
    <span class="dot" onclick="currentSlide(1)"></span>
    <span class="dot" onclick="currentSlide(2)"></span>
    <span class="dot" onclick="currentSlide(3)"></span>
</div>
</body>
    <script>
        showSlides(slideIndex);
    </script>
</html>
