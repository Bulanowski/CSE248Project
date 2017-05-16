/**
 * Created by phil on 5/15/17.
 */

function displayData() {
    console.log("Status: " + this.status);
    if (this.status == 200 && this.responseText != null) {

        var event = JSON.parse(this.responseText);

        document.getElementById("businessName").innerHTML = event.establishment;
        document.getElementById("description").innerHTML = event.description;
        document.getElementById("businessPage").style.display = "";

        priceCalculator();

    }
    console.log("Response '"+this.responseText+"'");
}



function parseURI() {
    var uri = document.documentURI;
    if(uri.indexOf("?e=") != -1) {
        eventID = uri.substring(uri.indexOf("?e=") + 3);
    }

    console.log('"'+eventID+'"');

    if(eventID != undefined && eventID != "") {

        document.getElementById("noEvent").style.display = "none";

        var request = new XMLHttpRequest();
        request.onload = displayData;



        //IDK YETTT
        request.open("POST", "/WEB_war_exploded/app/", true);
        request.setRequestHeader("Content-type", "text/plain");
        request.send(eventID);

    }
}