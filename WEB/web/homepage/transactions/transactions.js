/**
 * Created by phil on 5/13/17.
 */


function populateTransactions() {

    // console.log(this.responseText);

    if(this.status == 200 && this.responseText != null) {

        var response = this.responseText;
        if(response != "Invalid token" && response != "No tickets found" && response != "Failed to retrieve tickets") {
            var transactionJsonArray = JSON.parse(response);
            document.getElementsByClassName("noTransactions")[0].style.display = "none";

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
                var eventInfo = transaction.event;
                transactionElement.id = "transaction"+transaction.ticketID;

                transactionElement.getElementsByClassName("event")[0].innerHTML = eventInfo.name;
                transactionElement.getElementsByClassName("amount")[0].innerHTML = "Payment: "+transaction.amount;
                transactionElement.getElementsByClassName("date")[0].innerHTML = "Date of Transaction: "+eventInfo.date;
                transactionElement.getElementsByClassName("time")[0].innerHTML = "Time of Transaction: "+eventInfo.time;
                transactionElement.getElementsByClassName("transactionID")[0].innerHTML = "transaction I.D. #"+transaction.ticketID;

                document.getElementById("ticketList").appendChild(transactionElement);
            }

        }

    }
}


function getTransactions() {

    var token = getCookie("token");
    var request = new XMLHttpRequest();
    request.onload = populateTransactions;
    request.open("POST", "/WEB_war_exploded/app/event/getTransactions", true);
    request.setRequestHeader("Content-type", "text/plain");
    request.send(token);
}


