/**
 * Created by Alex on 5/6/2017.
 */



function sendLoginRequest(username, password) {
    var form = document.getElementsByClassName("form-control");
    var blankFields = 0;
    for(var i = 0; i<form.length; i++) {
        if(form[i].value == null || form[i].value == '') {
            form[i].style.borderColor = "red";
            form[i].style.zIndex = "2";
            blankFields = 1;
        } else {
            form[i].style.borderColor = "";
        }
    }
    if(blankFields == 1) {
        return
    }

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
        // console.log(response);
        if (response == "Login Failed") {
            document.getElementById("signInFailed").style.display = "";
        } else {
            document.getElementById("signInFailed").style.display = "none";
            var inComingJson = JSON.parse(response);
            window.location.href = inComingJson.url;
            document.cookie = "token=" + inComingJson.token + ";path=/";
            console.log("done");
        }
    } else {
        console.log("An error occurred");
    }
}

function  focusFix() {
    document.getElementById("username").onfocus = function e() {
        document.getElementById("username").style.zIndex = "2";
        document.getElementById("password").style.zIndex = "1";
    }
    document.getElementById("password").onfocus = function e() {
        document.getElementById("password").style.zIndex = "2";
        document.getElementById("username").style.zIndex = "1";
    }
}
