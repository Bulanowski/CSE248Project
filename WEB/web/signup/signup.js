/**
 * Created by david on 4/28/17.
 */

function CreateAccount() {
    var json = {};
    json.username = document.getElementById("username").value;
    json.password = document.getElementById("password").value;
    json.email = document.getElementById("email").value;
    json.address = document.getElementById("address").value;
    json.zip = document.getElementById("zip").value;
    json.birthday = document.getElementById("birthday").value;
    json.phone = document.getElementById("phone").value;
    if(document.getElementById("radio-customer").checked){
        json.account = "Customer"
    } else {
        json.account = "Establishment"
    }
    console.log("Collected:\nUsername: " + json.username +"\nPassword: " + json.password + "\nEmail: " + json.email
        + "\nAddress: " + json.address + "\nZip: " + json.zip + "\nPhone Number: " + json.phone + "\nBirthday: " + json.birthday
        + "\nAccount Type: " + json.account);
    if(json.password != document.getElementById("password-confirm").value){
        console.log("BAD\n" + json.password + '\n' + document.getElementById("password-confirm").value)

    }else {
        console.log("GOOD\n" + json.password + '\n' + document.getElementById("password-confirm").value)
    }


}