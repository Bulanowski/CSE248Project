    /**
        * Created by phil on 4/20/17.
        */
    var response;


function UserAction() {
    var json = {};
    json.username = document.getElementById("username").value;
    json.password = document.getElementById("password").value;
    console.log("Sent: " + json.username +" "+ json.password);
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "app/loginJava", false);
    xhttp.setRequestHeader("Content-type", "text/plain");
    xhttp.send(JSON.stringify(json));
    response = xhttp.responseText;
    console.log(response);
    if(response == "Login Failed") {

    } else {
        var inComingJson = JSON.parse(response);
        window.location.href = inComingJson.url;
        document.cookie = "username="+inComingJson.username+";path=/";
        console.log(inComingJson.username);
        console.log("done");
    }

}

function  getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}


