/**
 * Created by phil on 5/13/17.
 */

var eventID;

function showTicketInfo() {
    document.getElementById("buyTicketsbtn").style.display = "none";
    document.getElementById("ticketInfo").style.display = "grid";
    document.getElementById("buyBtn").style.display = "";
    window.scrollBy(0,500);
}

function parseURI() {
    var uri = document.documentURI;
    eventID = uri.substring(uri.indexOf("?e=")+3);

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
        document.getElementById("date").innerHTML = "Date: "+event.date;
        document.getElementById("time").innerHTML = "Time: "+event.time;
        document.getElementById("price").innerHTML = "$"+((event.price).toFixed(2));

    }
    console.log("Response '"+this.responseText+"'");

}

function assurance() {
    document.getElementById("ticketInfo").style.display = "none";
    document.getElementById("buyBtn").style.display = "none";
    document.getElementById("assurance").style.display = "";
}

function no() {
    document.getElementById("ticketInfo").style.display = "";
    document.getElementById("buyBtn").style.display = "";
    document.getElementById("assurance").style.display = "none";
}

function purchaseTicket() {
    var token = getCookie("token");
    var tickets = document.getElementById("maxTickets").value;

    var json = {};
    json.token = token;
    json.amount = parseInt(tickets);
    json.eventID = parseInt(eventID);

    var request = new XMLHttpRequest();
    request.onload = purchaseSuccessful;
    request.open("POST", "/WEB_war_exploded/app/event/buyTicket", true);
    request.setRequestHeader("Content-type", "text/plain");
    request.send(JSON.stringify(json));
}

function purchaseSuccessful() {
    console.log("Status: " + this.status);
    if (this.status == 200 && this.responseText != null) {
        console.log(this.responseText);

        if(this.responseText == "Tickets purchase failed") {
            document.getElementById("ticketInfo").style.display = "none";
            document.getElementById("buyBtn").style.display = "none";
            document.getElementById("assurance").style.display = "none";
            document.getElementById("purchaseMessage").style.display = "";
            document.getElementById("purchaseMessage").innerHTML = "Purchase Failed."
            setTimeout(function () {
                document.getElementById("purchaseMessage").style.display = "none";
                document.getElementById("ticketInfo").style.display = "";
                document.getElementById("buyBtn").style.display = "";
            } ,1000);
        } else {
            document.getElementById("ticketInfo").style.display = "none";
            document.getElementById("buyBtn").style.display = "none";
            document.getElementById("assurance").style.display = "none";
            document.getElementById("purchaseMessage").innerHTML = "Purchase Successful!"
            document.getElementById("purchaseMessage").style.display = "";
        }
    }
}

function priceCalculator() {
    var tickets = document.getElementById("maxTickets").value;
    var price = document.getElementById("price").innerHTML.replace("$"," ");

    document.getElementById("ticketAmount").innerHTML = "= $"+((tickets*price).toFixed(2));
}