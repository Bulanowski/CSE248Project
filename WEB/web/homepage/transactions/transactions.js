/**
 * Created by phil on 5/13/17.
 */


function populateTransactions() {

    // console.log(this.responseText);

    if(this.status == 200 && this.responseText != null) {

        var response = this.responseText;
        if(response != "Invalid token" && response != "No tickets found" && response != "Failed to retrieve tickets") {
            var transactionJsonArray = JSON.parse(response);

            if(transactionJsonArray.length != 0) {
                document.getElementsByClassName("noTransaction")[0].style.display = "none";
            } else {
                return;
            }

            var transaction1 = document.getElementById("transaction");
            transaction1.style.display = "";

            for(var i = 0; i < transactionJsonArray.length; i++) {

                var transactionElement;

                if(i == 0) {

                    transactionElement = transaction1;

                } else {

                    transactionElement = transaction1.cloneNode(true);

                }



                var transaction = transactionJsonArray[i];
                transactionElement.id = "transaction"+transaction.ticketID;

                transactionElement.getElementsByClassName("event")[0].innerHTML = transaction.eventName;
                transactionElement.getElementsByClassName("amount")[0].innerHTML = "Payment: $"+transaction.payment.toFixed(2);
                transactionElement.getElementsByClassName("date")[0].innerHTML = "Date of Transaction: "+transaction.date;
                transactionElement.getElementsByClassName("sender")[0].innerHTML = "Sender: "+transaction.sender;
                transactionElement.getElementsByClassName("recipient")[0].innerHTML = "Recipient: "+transaction.receiver;
                transactionElement.getElementsByClassName("transactionID")[0].innerHTML = "transaction I.D. #"+transaction.transactionID;

                if(i != 0) {
                    document.getElementById("transactionList").appendChild(transactionElement);
                }
            }

        }

    }
}


function getTransactions() {

    var token = getCookie("token");
    var json = {};
    json.token = token;
    var request = new XMLHttpRequest();
    request.onload = populateTransactions;
    request.open("POST", "/WEB_war_exploded/app/event/transaction/get", true);
    request.setRequestHeader("Content-type", "text/plain");
    request.send(JSON.stringify(json));
}


