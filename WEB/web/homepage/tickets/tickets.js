/**
 * Created by phil on 5/13/17.
 */


function populateTickets() {

    // console.log(this.responseText);

    if(this.status == 200 && this.responseText != null) {

        var response = this.responseText;
        if(response != "Invalid token" && response != "No tickets found" && response != "Failed to retrieve tickets") {
            var ticketJsonArray = JSON.parse(response);
            document.getElementsByClassName("noTickets")[0].style.display = "none";

            var ticket1 = document.getElementById("ticket1");
            ticket1.style.display = "";

            for(var i = 0; i < ticketJsonArray.length; i++) {

                var ticketElement;

                if(i == 0) {

                    ticketElement = ticket1;

                } else {

                    var ticketElement = ticket1.cloneNode(true);

                }



                var ticket = ticketJsonArray[i];
                var eventInfo = ticket.event;
                ticketElement.id = "ticket"+ticket.ticketID;

                ticketElement.getElementsByClassName("event")[0].innerHTML = eventInfo.name;
                ticketElement.getElementsByClassName("amount")[0].innerHTML = "Amount Bought: "+ticket.amount;
                ticketElement.getElementsByClassName("date")[0].innerHTML = "Date of Event: "+eventInfo.date;
                ticketElement.getElementsByClassName("time")[0].innerHTML = "Time of Event: "+eventInfo.time;
                ticketElement.getElementsByClassName("ticketID")[0].innerHTML = "Ticket I.D. #"+ticket.ticketID;
                ticketElement.getElementsByClassName("cancelBtn")[0].setAttribute("onclick", "switchBtns(ticket"+ticket.ticketID+')');
                ticketElement.getElementsByClassName("yes")[0].setAttribute("onclick", "cancelTickets("+ticket.ticketID+')');
                ticketElement.getElementsByClassName("no")[0].setAttribute("onclick", "hideYesNo(ticket"+ticket.ticketID+')');

                document.getElementById("ticketList").appendChild(ticketElement);
            }

        }

    }
}


function getTickets() {

    var token = getCookie("token");
    var request = new XMLHttpRequest();
    request.onload = populateTickets;
    request.open("POST", "/WEB_war_exploded/app/event/getTickets", true);
    request.setRequestHeader("Content-type", "text/plain");
    request.send(token);
}

function switchBtns(btnID) {
    btnID.getElementsByClassName("cancelBtn")[0].style.display = "none";
    btnID.getElementsByClassName("assuranceText")[0].style.display = "";
}

function hideYesNo(btnID) {
    btnID.getElementsByClassName("cancelBtn")[0].style.display = "";
    btnID.getElementsByClassName("assuranceText")[0].style.display = "none";
}

function removeTicket() {
    console.log(this.responseText);
    if(this.status == 200 && this.responseText == "Ticket canceled successfully") {
        var ticket = document.getElementById("ticket"+ticketIDToRemove);
        ticket.className += " animate";
        setTimeout(function e() {
            ticket.parentNode.removeChild(ticket);
            if(document.getElementsByClassName("info").length == 0) {
                document.getElementsByClassName("noTickets")[0].style.display = "";
                document.getElementsByClassName("noTickets")[0].className += " w3-animate-right";

            }
        },490);

    }
}

var ticketIDToRemove;

function cancelTickets(ticketID) {
    var json = {};
    var token = getCookie("token");
    json.token = token;
    json.ticketID = ticketID;
    ticketIDToRemove = ticketID;
    var request = new XMLHttpRequest();
    request.onload = removeTicket;
    request.open("POST", "/WEB_war_exploded/app/event/cancelTicket", true);
    request.setRequestHeader("Content-type", "text/plain");
    request.send(JSON.stringify(json));
}