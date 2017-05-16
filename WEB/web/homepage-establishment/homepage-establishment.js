/**
 * Created by david on 5/15/17.
 */

function loadPlannedEvents() {
    if(this.status == 200 && this.responseText != null){
        var response = this.responseText;
        // console.log(this.responseText);
        if(response != "Invalid token" && response != "No events found" && response != "Failed to retrieve events"){

            var json = JSON.parse(response);
            var eventJsonArray = json.events;
            document.getElementsByClassName("noUpcomingEvents")[0].style.display = "none";
            var event1 = document.getElementById("event1");
            event1.style.display = "";



            for(var i = 0; i < eventJsonArray.length && i < 7; i++){



                var eventElement;
                if(i == 0){
                    eventElement = event1;
                } else {
                    eventElement = event1.cloneNode(true);
                }
                var event = eventJsonArray[i];

                eventElement.id = "event" + event.eventID;

                eventElement.getElementsByClassName("event")[0].innerHTML = event.name;
                eventElement.getElementsByClassName("date")[0].innerHTML = "Date: " + event.date;
                eventElement.getElementsByClassName("time")[0].innerHTML = "Time: " + event.time;
                eventElement.getElementsByClassName("ticketsSold")[0].innerHTML = "Tickets sold: " + event.purchasedTickets;
                eventElement.getElementsByClassName("ticketsLeft")[0].innerHTML = "Tickets Left: " + (event.maxTickets - event.purchasedTickets);

                if(i !== 0){
                    document.getElementById("eventList").appendChild(eventElement);
                }
            }
        }
    }
}

function getEventsFromServer() {
    var token = getCookie("token");
    var request = new XMLHttpRequest()

    request.onload = loadPlannedEvents;
    request.open("POST", "/WEB_war_exploded/app/account/establishment/get/editable", true);
    request.setRequestHeader("Content-type", "text/plain");
    request.send(token);
}