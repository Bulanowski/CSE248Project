/**
 * Created by Alex on 5/8/2017.
 */

function sendRegisterEventRequest() {

    var form = document.getElementsByClassName("form-control");
    var blankFields = 0;
    for(var i = 0; i<form.length; i++) {
        if(form[i].value == null || form[i].value == '') {
            form[i].style.borderColor = "red";
            blankFields = 1;
        } else {
            form[i].style.borderColor = "";
        }
    }
    if(blankFields == 1) {
        return
    }

    var json = {};
    json.token = getCookie("token");
    json.name = document.getElementById("name").value;
    json.description = document.getElementById("description").value;
    json.imageSrc = document.getElementById("imageSrc").value;
    json.date = document.getElementById("date").value;
    json.time = document.getElementById("time").value;
    json.price = parseFloat(document.getElementById("price").value);
    json.maxTickets = parseInt(document.getElementById("maxTickets").value);
    console.log("Collected:\nName: " + json.name + "\nDescription: " + json.description + "\nImage Src: " + json.imageSrc + "\nDate: " + json.date
        + "\nTime: " + json.time + "\nPrice: " + json.price + "\nMax Tickets: " + json.maxTickets);

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