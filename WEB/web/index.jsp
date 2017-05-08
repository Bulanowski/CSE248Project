<%--
  Created by IntelliJ IDEA.
  User: phil
  Date: 4/25/17
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="Test.js"></script>
    <script>
        var token = getCookie("token");
        console.log("Sent: " + token);
        var client = new XMLHttpRequest();
        client.onload = handler;
        client.open("POST", "/WEB_war_exploded/app/account/token", true);
        client.setRequestHeader("Content-type", "text/plain");
        client.send(token);

        function handler() {
            if (this.status == 200 && this.responseText != null) {
                var response = this.responseText;
                console.log(response);
                if(response == "Token Not Found" || response == "Username Not Found") {
                    window.location.href = "login/";
                } else {
                    var inComingJson = JSON.parse(response);
                    console.log("Going to " + inComingJson.homepage);
                    window.location.href = inComingJson.homepage;
                }
            } else {
                console.log("An error occurred")
            }
        }
    </script>
</head>
<body>

</body>
</html>
