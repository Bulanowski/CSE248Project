/**
 * Created by phil on 5/15/17.
 */

function displayData() {
    console.log("Status: " + this.status);
    console.log("Response: "+this.responseText);
    if (this.status == 200 && this.responseText != null) {

        var json = JSON.parse(this.responseText);
        var business = json.establishment;
        var uri = document.documentURI;
        uri = uri.substring(0, uri.indexOf("page")+4);

        document.getElementById("businessName").innerHTML = business.name;
        document.getElementById("description").innerHTML = business.description;
        document.getElementById("businessPage").style.display = "";

        var events = json.events;

        var event1 = document.getElementById("event");

        for(var i = 0; i < events.length; i++) {

            var eventElement;

            if(i == 0) {
                eventElement = event1;
            } else {
                eventElement = event1.cloneNode(true);
            }

            var eventInfo = events[i];

            eventElement.getElementsByClassName("eventName")[0].innerHTML = eventInfo.name;
            eventElement.getElementsByClassName("eventName")[0].href = uri+"/event?e="+eventInfo.eventID;
            eventElement.getElementsByClassName("date")[0].innerHTML = "Date: " +eventInfo.date;

            document.getElementById("eventList").appendChild(eventElement);
        }
    }
}



function parseURI() {
    var uri = document.documentURI;
    var business;
    if(uri.indexOf("?b=") != -1) {
        business = uri.substring(uri.indexOf("?b=") + 3);
    }

    console.log('"'+business+'"');

    // if(business != undefined && business != "") {

        document.getElementById("noBusiness").style.display = "none";

        var request = new XMLHttpRequest();
        request.onload = displayData;

        request.open("POST", "/WEB_war_exploded/app/account/establishment/get", true);
        request.setRequestHeader("Content-type", "text/plain");
        request.send(business);

    // }
}