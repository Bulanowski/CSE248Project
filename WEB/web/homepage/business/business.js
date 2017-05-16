/**
 * Created by phil on 5/15/17.
 */

function displayData() {
    console.log("Status: " + this.status);
    if (this.status == 200 && this.responseText != null) {

        var business = JSON.parse(this.responseText);

        document.getElementById("businessName").innerHTML = business.name;
        document.getElementById("description").innerHTML = business.description;
        document.getElementById("businessPage").style.display = "";

    }
    console.log("Response '"+this.responseText+"'");
}



function parseURI() {
    var uri = document.documentURI;
    var business;
    if(uri.indexOf("?e=") != -1) {
        business = uri.substring(uri.indexOf("?e=") + 3);
    }

    console.log('"'+business+'"');

    if(business != undefined && business != "") {

        document.getElementById("noEvent").style.display = "none";

        var request = new XMLHttpRequest();
        request.onload = displayData;



        //IDK YETTT
        request.open("POST", "/WEB_war_exploded/app/", true);
        request.setRequestHeader("Content-type", "text/plain");
        request.send(business);

    }
}