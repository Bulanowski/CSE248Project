<%--
  Created by IntelliJ IDEA.
  User: phil
  Date: 5/9/17
  Time: 10:27 AM
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
    <link href="homepage/homepage.css?v=0.0.7" rel="stylesheet">

    <script src="main.js"></script>
    <script src="homepage/settings/settings.js"></script>
    <script src="homepage/search/search.js"></script>
    <title>Settings</title>
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
                <li><a href="homepage/transactions"><span class=""></span>Transactions</a></li>
                <li><a disabled="true"><span class=" active glyphicon glyphicon-user"></span> Account Settings</a></li>
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
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <form action="" method="post" class="form-signin">
                <h2>Change Password</h2>
                <input type="password" id="old-password" class="form-control" placeholder="Old Password" name="password">
                <input type="password" id="password" class="form-control" placeholder="New Password" name="password">
                <input type="password" id="password-confirm" class="form-control" placeholder="Retype new Password" name="password-confirm">
                <button type="button" class="btn btnDark" onclick="sendChangePasswordRequest()">Change Password</button>
            </form>
        </div>
        <div class="col-sm-4">
            <form action="" method="post" class="form-signin">
                <h2>User Info</h2>
                <p>Name</p>
                <input type="text" id="name" name="name" class="form-control" placeholder="Name">
                <p>Gender</p>
                <input type="color" id="gender" name="gender">
                <p>Birth Date</p>
                <input type="date" id="birthday" name ="birthday" class="form-control">
                <p>Phone Number</p>
                <input type="number" id="phone" name="phone" class="form-control" placeholder="Phone Number">
                <p>Address</p>
                <input type="text" id="address" name="address" class="form-control" placeholder="Street Address">
                <input type="number" id="zip" name="zip" class="form-control" placeholder="Zip Code">
                <button type="button" class="btn btnDark" onclick="sendSetSettingsRequest()">Update Info</button>
            </form>
        </div>
        <div class="col-sm-4">
            <form action="" method="post" class="form-signin">
                <h2>Preferences</h2>
                <p>Favorite Music Genre</p>
                <select class="selection" id="music-type" name="music-type">
                    <option value="hip-hop">Hip Hop</option>
                    <option value="rock">Rock 'N Roll</option>
                    <option value="jazz">Jazz</option>
                    <option value="dance">Dance</option>
                    <option value="metal">Metal</option>
                </select>

                <p>Favorite Foods</p>
                <select class="selection" id="food-type" name="food-type">
                    <option value="spanish">Spanish Food</option>
                    <option value="italian">Italian Food</option>
                    <option value="greek">Greek Food</option>
                    <option value="pizza">Pizza</option>
                    <option value="fast-food">Fast Food</option>
                    <option value="persian-food">Persian Food</option>
                </select>
                <br>
                <button type="button" class="btn btnDark" onclick="">Submit Preferences</button>
            </form>
        </div>
    </div>

</div>
</body>
</html>
