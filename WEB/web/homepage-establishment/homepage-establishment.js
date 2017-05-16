/**
 * Created by david on 5/15/17.
 */

function loadPlannedEvents() {
    if(this.status == 200 && this.responseText != null){
        var response = this.responseText;
        if(response != "Invalid token" && response != "No events found" && response != "Failed to retrieve events"){
            var eventJsonArray = JSON.parse(response);
            document.getElementsByClassName("noUpcomingEvents")[0].style.display = "none";
            var event1 = document.getElementById("event1");
            event1.style.display = "";

            for(var i = 0; i < eventJsonArray.length && i < 5; i++){
                var eventElement;
                if(i == 0){
                    eventElement = event1;
                } else {
                    eventElement = event1.cloneNode(true);
                }
                var ticket = eventJsonArray[i];
                var event = ticket.event;

                eventElement.id = "event" + event.eventID;

                eventElement.getElementsByClassName("date")[0].innerHTML = "Date: " + event.date;


                document.getElementById("eventList").appendChild(eventElement);
            }
        }
    }
}

function getEventsFromServer() {
    var token = getCookie("token");
    var request = new XMLHttpRequest()

    request.onload = loadPlannedEvents;
    request.open("POST", "/WEB_war_exploded/app/event/getTickets", true);
    request.setRequestHeader("Content-type", "text/plain");
    request.send(token);
}