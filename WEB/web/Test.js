    /**
     * Created by phil on 4/20/17.
     */

function getCookie(cname) {
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