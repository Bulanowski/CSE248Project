/**
 * Created by phil on 5/15/17.
 */
function showChangeDescription() {
    document.getElementsByClassName("changeDescription")[0].style.display = "block";
    document.getElementById("showChangeDescriptionButton").style.display = "none";
    document.getElementById("changeDesc").value = document.getElementById("description").innerHTML;
    console.log(document.getElementsByClassName("hours")[0].value);
}

function changeDescription() {
    var json = {};
    json.description = document.getElementById("changeDesc").value;
    console.log(json.description);
    var client = new XMLHttpRequest()
    // client.onload

}

function showChangeHours() {
    document.getElementById("showChangeHoursButton").style.display = "none";
    var divs = document.getElementsByClassName("changeHours");
    divs[0].style.display = "";
    divs[1].style.display = "";
}

function changeHours() {

}


function displayData() {
    console.log("Status: " + this.status);
    if (this.status == 200 && this.responseText != null) {
        var response = this.response;
        console.log(response);
        if(response != "Invalid token" && response != "Business not found"){
            var json = JSON.parse(this.responseText);
            var business = json.establishment;
            var events = json.events;
            document.getElementById("businessName").innerHTML = business.name;
            document.getElementById("description").innerHTML = business.description;
            document.getElementById("businessPage").style.display = "";
            console.log("hours" + business.hours);
            document.getElementById("mon").innerHTML = business.hours[0];
            document.getElementById("tues").innerHTML = business.hours[1];
            document.getElementById("wed").innerHTML = business.hours[2];
            document.getElementById("thurs").innerHTML = business.hours[3];
            document.getElementById("fri").innerHTML = business.hours[4];
            document.getElementById("sat").innerHTML = business.hours[5];
            document.getElementById("sun").innerHTML = business.hours[6];

            var event;
            var initial = document.getElementById("event");

            for(var i = 0; i < events.length && i < 5; i++){
                var eventElement;
                event = events[i];
                if(i == 0){
                    eventElement = initial;
                } else {
                    eventElement = initial.cloneNode(true);
                }

                eventElement.id = "event" + event.eventID;

                eventElement.getElementsByClassName("eventName")[0].innerHTML = "Event Name:" + event.name;
                eventElement.getElementsByClassName("date")[0].innerHTML = "Date: " + event.date;

                if(i != 0){
                    document.getElementById("eventList").appendChild(eventElement);
                }
            }
        }
    }
    console.log("Response '"+this.responseText+"'");
}

function getEstablishmentFromServer() {
    var token = getCookie("token");
    var request = new XMLHttpRequest;
    request.onload = displayData;
    request.open("POST" , "/WEB_war_exploded/app/account/establishment/get/editable", true);
    request.setRequestHeader("Content-type", "text/plain");
    request.send(token);
}