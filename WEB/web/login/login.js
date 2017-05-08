/**
 * Created by Alex on 5/6/2017.
 */

function sendLoginRequest(username, password) {
    var json = {};
    json.username = username;
    json.password = password;
    console.log("Sent: " + json.username +" "+ json.password);
    var client = new XMLHttpRequest();
    client.onload = handleLoginResponse;
    client.open("POST", "/WEB_war_exploded/app/account/login", true);
    client.setRequestHeader("Content-type", "text/plain");
    client.send(JSON.stringify(json));
}

function handleLoginResponse() {
    console.log("Status: " + this.status);
    if (this.status == 200 && this.responseText != null) {
        var response = this.responseText;
        console.log(response);
        if (response == "Login Failed") {

        } else {
            var inComingJson = JSON.parse(response);
            window.location.href = inComingJson.url;
            document.cookie = "token=" + inComingJson.token + ";path=/";
            console.log("done");
        }
    } else {
        console.log("An error occurred");
    }
}