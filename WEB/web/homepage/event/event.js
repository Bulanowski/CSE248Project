/**
 * Created by phil on 5/13/17.
 */

function showTicketInfo() {
    document.getElementById("buyTicketsbtn").style.display = "none";
    document.getElementById("ticketInfo").style.display = "grid";
    document.getElementById("buyBtn").style.display = "";
}

function parseURI() {
    var uri = document.documentURI;
    var eventID = uri.substring(uri.indexOf("?e=")+3);

    console.log(eventID);

    var request = new XMLHttpRequest();
    request.onload = displayData;
    request.open("POST", "/WEB_war_exploded/app/event/get", true);
    request.setRequestHeader("Content-type", "text/plain");
    request.send(eventID);
}

function displayData() {
    console.log("Status: " + this.status);
    if (this.status == 200 && this.responseText != null) {

        var event = JSON.parse(this.responseText);

        document.getElementById("eventName").innerHTML = event.name;
        document.getElementById("businessName").innerHTML = event.establishment;
        document.getElementById("description").innerHTML = event.description;
        document.getElementById("date").innerHTML = event.date;
        document.getElementById("time").innerHTML = event.time;
        document.getElementById("price").innerHTML = "$"+event.price;

    }
    console.log("Response '"+this.responseText+"'");

}