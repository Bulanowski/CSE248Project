/**
 * Created by david on 4/28/17.
 */

function sendRegisterRequest() {
    var json = {};
    json.username = document.getElementById("username").value;
    json.password = document.getElementById("password").value;
    json.email = document.getElementById("email").value;
    // json.address = document.getElementById("address").value;
    // json.zip = document.getElementById("zip").value;
    // json.birthday = document.getElementById("birthday").value;
    // json.phone = document.getElementById("phone").value;
    if(document.getElementById("radio-customer").checked){
        json.accountType = "Customer"
    } else {
        json.accountType = "Establishment"
    }
    console.log("Collected:\nUsername: " + json.username +"\nPassword: " + json.password + "\nEmail: " + json.email
        + "\nAddress: " + json.address + "\nZip: " + json.zip + "\nPhone Number: " + json.phone + "\nBirthday: " + json.birthday
        + "\nAccount Type: " + json.accountType);
    if(json.password != document.getElementById("password-confirm").value){
        console.log("BAD\n" + json.password + '\n' + document.getElementById("password-confirm").value);
    } else {
        console.log("GOOD\n" + json.password + '\n' + document.getElementById("password-confirm").value);
    }

    var client = new XMLHttpRequest();
    client.onload = handleRegisterResponse;
    client.open("POST", "app/account/register", true);
    client.setRequestHeader("Content-type", "text/plain");
    client.send(JSON.stringify(json));
}

function handleRegisterResponse() {
    var response = this.responseText;
    console.log(response);
    if(response == "Register Successful") {
        window.location.href = "/WEB_war_exploded/login/";
    } else {
        var json = JSON.parse(response);
        if (json.hasAttribute("username")) {

        }
        if (json.hasAttribute("email")) {

        }
        // if (json.hasAttribute("address")) {
        //
        // }
        // if (json.hasAttribute("zip")) {
        //
        // }
        // if (json.hasAttribute("birthday")) {
        //
        // }
        // if (json.hasAttribute("phone")) {
        //
        // }
    }
}