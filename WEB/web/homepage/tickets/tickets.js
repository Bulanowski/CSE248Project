/**
 * Created by phil on 5/13/17.
 */


function populateTickets() {

    // console.log(this.responseText);

    if(this.status == 200 && this.responseText != null) {

        var response = this.responseText;
        if(response != "Invalid token" || response != "No tickets found" || response != "Failed to retrieve tickets") {
            var ticketJsonArray = JSON.parse(response);
            var ticket = ticketJsonArray[0];
            var eventInfo = ticket.event;

            var ticket1 = document.getElementById("ticket1");
            ticket1.id = "ticket"+ticket.ticketID;
            ticket1.style.display = "";
            ticket1.getElementsByClassName("event")[0].innerHTML = eventInfo.name;
            ticket1.getElementsByClassName("amount")[0].innerHTML = "Amount Bought: "+ticket.amount;
            ticket1.getElementsByClassName("date")[0].innerHTML = "Date of Event: "+eventInfo.date;
            ticket1.getElementsByClassName("time")[0].innerHTML = "Time of Event: "+eventInfo.time;
            ticket1.getElementsByClassName("ticketID")[0].innerHTML = "Ticket I.D. #"+ticket.ticketID;
            ticket1.getElementsByClassName("cancelBtn")[0].setAttribute("onclick", "switchBtns(ticket"+ticket.ticketID+')');
            ticket1.getElementsByClassName("yes")[0].setAttribute("onclick", "cancelTickets("+ticket.ticketID+')');
            ticket1.getElementsByClassName("no")[0].setAttribute("onclick", "hideYesNo(ticket"+ticket.ticketID+')');


            for(var i = 1; i < ticketJsonArray.length; i++) {
                var clone = ticket1.cloneNode(true);

                var ticket = ticketJsonArray[i];
                eventInfo = ticket.event;
                clone.id = "ticket"+ticket.ticketID;

                clone.getElementsByClassName("event")[0].innerHTML = eventInfo.name;
                clone.getElementsByClassName("amount")[0].innerHTML = "Amount Bought: "+ticket.amount;
                clone.getElementsByClassName("date")[0].innerHTML = "Date of Event: "+eventInfo.date;
                clone.getElementsByClassName("time")[0].innerHTML = "Time of Event: "+eventInfo.time;
                clone.getElementsByClassName("ticketID")[0].innerHTML = "Ticket I.D. #"+ticket.ticketID;
                clone.getElementsByClassName("cancelBtn")[0].setAttribute("onclick", "switchBtns(ticket"+ticket.ticketID+')');
                clone.getElementsByClassName("yes")[0].setAttribute("onclick", "cancelTickets("+ticket.ticketID+')');
                clone.getElementsByClassName("no")[0].setAttribute("onclick", "hideYesNo(ticket"+ticket.ticketID+')');

                document.getElementById("ticketList").appendChild(clone);
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
    btnID.getElementsByClassName("yes")[0].style.display = "";
    btnID.getElementsByClassName("no")[0].style.display = "";
}

function hideYesNo(btnID) {
    btnID.getElementsByClassName("cancelBtn")[0].style.display = "";
    btnID.getElementsByClassName("yes")[0].style.display = "none";
    btnID.getElementsByClassName("no")[0].style.display = "none";
}

function removeTicket() {
    console.log(this.responseText);
    if(this.status == 200 && this.responseText == "Ticket canceled successfully") {
        var ticket = document.getElementById("ticket"+ticketIDToRemove);
        ticket.parentNode.removeChild(ticket);
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