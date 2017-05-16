/**
 * Created by david on 5/16/17.
 */

function displayData() {
    console.log("Status: " + this.status);
    if (this.status == 200 && this.responseText != null) {
        var response = this.response;
        console.log(response);
        if(response != "Invalid token" && response != "Business not found"){
            var json = JSON.parse(this.responseText);
            console.log("json: " + json);
            console.log("employees: " + json.employees);

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