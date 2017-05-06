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
    <base href="../">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%--bootstrap core--%>
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <%--bootstrap theme--%>
    <link href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet">

    <link href="w3.css" rel="stylesheet">
    <link href="theme.css?v=0.0.7" rel="stylesheet">
    <link href="homepage/homepage.css" rel="stylesheet">


    <script src="homepage/homepage.js"></script>


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
                            <input placeholder="Search" class=" Testy form-control searchbar">
                            <button type="submit" class="Testy btn btnDark">Submit</button>
                        </div>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="slideshow-container" onmouseenter="onImg()" onmouseleave="offImg()">
    <div class=" mySlides w3-animate-opacity">
        <img class="img-rounded"  src="images/img1.jpg" style="width:100%">
        <div class="text imgOverlayColor" style="display: none" >Caption Text</div>
    </div>

    <div class=" mySlides w3-animate-opacity">
        <img class="img-rounded"  src="images/img2.jpg" style="width:100%">
        <div class="text imgOverlayColor" style="display: none" >Caption Two</div>
    </div>

    <div class=" mySlides w3-animate-opacity">
        <img  class="img-rounded" src="images/img3.jpg" style="width:100%">
        <div class="text imgOverlayColor" style="display: none" >Caption Three</div>
    </div>
    <div class="w3-center w3-display-bottommiddle" style="width:50%">
        <span class="dot imgOverlayBorderColor" style="display: none" onclick="currentSlide(1)"></span>
        <span class="dot imgOverlayBorderColor" style="display: none" onclick="currentSlide(2)"></span>
        <span class="dot imgOverlayBorderColor" style="display: none" onclick="currentSlide(3)"></span>
    </div>
    <button class="myArrows imgOverlayColor imgOverlayBorderColor w3-display-left w3-hover-text-k" style="display: none"  onclick="plusSlides(-1)">&#10094;</button>
    <button class="myArrows imgOverlayColor imgOverlayBorderColor w3-display-right w3-hover-text-khaki" style="display: none"  onclick="plusSlides(1)">&#10095;</button>
</div>
<br>
</body>
    <script>
        showSlides(slideIndex);
    </script>
</html>
