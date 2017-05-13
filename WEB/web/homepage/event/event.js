/**
 * Created by phil on 5/13/17.
 */

var request;

function showTicketInfo() {
    document.getElementById("buyTicketsbtn").style.display = "none";
    document.getElementById("ticketInfo").style.display = "grid";
    document.getElementById("buyBtn").style.display = "";
}

function parseURI() {
    var uri = document.documentURI;
    var eventID = uri.substring(uri.indexOf("?e=")+3);
    document.request = new XMLHttpRequest();

    console.log(eventID);

    document.request.onload = displayData();
    document.request.open("POST", "/WEB_war_exploded/app/event/get", true);
    document.request.setRequestHeader("Content-type", "text/plain");
    document.request.send(eventID);
}

function displayData() {
    console.log(1);
    if (document.request.status == 200 && document.request.responseText != null) {

        console.log(2);

        var event = this.responseText;

        document.getElementById("eventName").innerHTML = event.name;
        document.getElementById("businessName").innerHTML = event.establishment;
        document.getElementById("description").innerHTML = event.description;
        document.getElementById("date").innerHTML = event.date;
        document.getElementById("time").innerHTML = event.time;
        document.getElementById("price").innerHTML = "$"+event.price;

    }
    console.log("Response '"+document.request.responseText+"'");

}