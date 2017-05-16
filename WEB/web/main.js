/**
 * Created by phil on 4/20/17.
 */

var token = getCookie("token");
console.log("Sent: " + token);
var client = new XMLHttpRequest();
if (document.body !== null) {
    document.body.style.display = "none";
}
client.onload = handler;
client.open("POST", "/WEB_war_exploded/app/account/token", true);
client.setRequestHeader("Content-type", "text/plain");
client.send(token);

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function signOut() {
    var token = getCookie("token");
    console.log("Signout with token: " + token);
    var client = new XMLHttpRequest();
    client.open("POST", "/WEB_war_exploded/app/account/signOut", true);
    client.setRequestHeader("Content-type", "text/plain");
    client.send(token);
    document.cookie = "token=0;path=/";
    window.location.href = "login/";
}

function handler() {
    if (this.status === 200) {
        var response = this.responseText;
        // console.log(response);
        if(response === "Token Not Found" || response === "Username Not Found") {
            window.location.href = "login/";
        } else {
            if (document.documentURI === "http://localhost:8080/WEB_war_exploded/" || document.documentURI === "http://localhost:8080/WEB_war_exploded/index.jsp") {
                if (response === "Customer") {
                    window.location.href = "homepage/";
                } else if (response === "Establishment") {
                    window.location.href = "homepage-establishment/";
                } else {
                    window.location.href = "login/";
                }
            } else {
                document.body.style.display="";
            }
        }
    } else {
        console.log("An error occurred")
    }
}