/**
 * Created by Alex on 5/8/2017.
 */

function sendRegisterEventRequest() {
    var json = {};
    json.token = getCookie("token");
    json.name = document.getElementById("name").value;
    json.description = document.getElementById("description").value;
    json.date = document.getElementById("date").value;
    json.time = document.getElementById("time").value;
    json.price = document.getElementById("price").value;
    json.tickets = document.getElementById("tickets").value;
    console.log("Collected:\nName: " + json.name +"\nDescription: " + json.description + "\nDate: " + json.date
        + "\nTime: " + json.time + "\nPrice: " + json.price + "\nTickets: " + json.tickets
        + "\n LOCKS NEED TO BE IMPLEMENTED");

    var client = new XMLHttpRequest();
    client.onload = handleRegisterEventResponse;
    client.open("POST", "/WEB_war_exploded/app/event/register", true);
    client.setRequestHeader("Content-type", "text/plain");
    client.send(JSON.stringify(json));
}

function handleRegisterEventResponse() {
    if (this.status == 200 && this.responseText != null) {
        var response = this.responseText;
        console.log(response);
        if (response == "Event created successfully") {
            window.location.href = "/WEB_war_exploded/homepage-establishment/";
        }
    } else {
        console.log("An error occurred");
    }
}