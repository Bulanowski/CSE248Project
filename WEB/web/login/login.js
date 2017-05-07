/**
 * Created by Alex on 5/6/2017.
 */

function attemptLogin(username, password) {
    var json = {};
    json.username = username;
    json.password = password;
    console.log("Sent: " + json.username +" "+ json.password);
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/WEB_war_exploded/app/account/login", false);
    xhttp.setRequestHeader("Content-type", "text/plain");
    xhttp.send(JSON.stringify(json));
    var response = xhttp.responseText;
    console.log(response);
    if(response == "Login Failed") {

    } else {
        var inComingJson = JSON.parse(response);
        window.location.href = inComingJson.url;
        document.cookie = "token="+inComingJson.token+";path=/";
        console.log("done");
    }

}
