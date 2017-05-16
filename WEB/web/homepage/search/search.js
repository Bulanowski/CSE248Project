/**
 * Created by phil on 5/10/17.
 */


function getBusinessResponse() {
    console.log(2);
    console.log(this.status);
    console.log(this.responseText);
    if (this.status === 200 && this.responseText !== null) {
        var response = this.responseText;
        if (response === "No results found") {
            document.getElementById("noResults").style.display = "";
            return;
        }
        document.getElementById("business").style.display = "";

        var eventJson = JSON.parse(response);
        var eventArray = eventJson.result;
        var pages = eventJson.pages;
        var pagebtn = document.getElementsByClassName("page");

        for(var i = 0; i < pages.length; i++) {
            var pageElement;
            if(i === 0) {
                pageElement = pagebtn;
            } else {
                pageElement = pagebtn.cloneNode(true);
            }

            pageElement.innerHTML = i+1;
            pageElement.href = '#';
            pageElement.setAttribute("onclick", "changePage("+(i+1)+"); return false;");
            document.getElementsByClassName("page-container")[0].appendChild(pageElement);
        }

        var business = document.getElementById("business");

        for(var i = 0; i < eventArray.length; i++) {

            var element;

            if(i == 0) {
                element = business;
            } else {
                element = business.cloneNode(true);
            }

            element.getElementsByClassName("img2")[0].src = eventArray[i].imageSrc;
            element.getElementsByClassName("title")[0].innerHTML = eventArray[i].name;
            element.getElementsByClassName("description")[0].innerHTML = eventArray[i].description;
            element.getElementsByClassName("events")[0].innerHTML = "Available Events: "+eventArray[i].events.length;

        }
    }
}


function getEventResponse() {
    if (this.status == 200 && this.responseText != null) {
        var response = this.responseText;
        if(response == "No results found") {
            var noResults = document.getElementById("noResults");
            noResults.style.display = "";

        } else {
            var eventJson = JSON.parse(response);
            var eventArray = eventJson.result;
            var pages = eventJson.pages;
            var pagebtn = document.getElementsByClassName("page");
            pagebtn[0].style.display = "";
            var pageContainer = document.getElementsByClassName("page-container");
            var uri = document.documentURI;
            uri = uri.substring(0,uri.indexOf("?=")+2);
            pagebtn[0].href = '#';
            pagebtn[0].setAttribute("onclick", "changePage("+1+");return false;");

            for(var i = 0; i < pages-1; i++) {
                var pageClone = pagebtn[0].cloneNode(true);
                pageClone.innerHTML = i+2;
                pageClone.href = '#';
                pageClone.setAttribute("onclick", "changePage("+(i+2)+"); return false;");
                pageContainer[0].appendChild(pageClone);
            }

            var event1 = document.getElementById("event1");
            event1.style.display = "";
            var list = document.getElementById("eventList");

            var mouseOver = event1.getElementsByClassName("searchEventBorder");
            mouseOver[0].setAttribute("onclick","goToEventPage("+eventArray[0].eventID+')');

            var first = event1.getElementsByClassName("first");
            var title = first[0].getElementsByClassName("searchTitle");
            var img = first[0].getElementsByClassName("searchEventImg");

            var second = event1.getElementsByClassName("second");
            var description = second[0].getElementsByClassName("searchDescription");
            var businessName = second[0].getElementsByClassName("otherTitle");
            var tickets = second[0].getElementsByClassName("tickets");
            var date = second[0].getElementsByClassName("date");
            var time = second[0].getElementsByClassName("time");
            title[0].innerHTML = eventArray[0].name;
            img[0].src = eventArray[0].imageSrc;
            description[0].innerHTML = eventArray[0].description;
            businessName[0].innerHTML = eventArray[0].establishment;
            tickets[0].innerHTML = "Available Tickets: "+ (eventArray[0].maxTickets - eventArray[0].purchasedTickets);
            date[0].innerHTML = "Date: "+ eventArray[0].date;
            time[0].innerHTML = "Time: "+ eventArray[0].time;

            for ( i = 1; i < eventArray.length; i++) {
                var clone = event1.cloneNode(true);
                clone.id = "event"+ (i+1);

                first = clone.getElementsByClassName("first");
                title = first[0].getElementsByClassName("searchTitle");
                img = first[0].getElementsByClassName("searchEventImg");

                second = clone.getElementsByClassName("second");
                description = second[0].getElementsByClassName("searchDescription");
                var businessName = second[0].getElementsByClassName("otherTitle");
                var tickets = second[0].getElementsByClassName("tickets");
                var date = second[0].getElementsByClassName("date");
                var time = second[0].getElementsByClassName("time");
                title[0].innerHTML = eventArray[i].name;
                img[0].src = eventArray[i].imageSrc;
                description[0].innerHTML = eventArray[i].description;
                businessName[0].innerHTML = eventArray[i].establishment;
                tickets[0].innerHTML = "Available Tickets: "+(eventArray[i].maxTickets - eventArray[i].purchasedTickets);
                date[0].innerHTML = "Date: "+ eventArray[i].date;
                time[0].innerHTML = "Time: "+ eventArray[i].time;


                var mouseOver = clone.getElementsByClassName("searchEventBorder");
                mouseOver[0].setAttribute("onclick","goToEventPage("+eventArray[i].eventID+')');

                list.appendChild(clone);
            }
            document.body.style.height ="100%";
        }
    }
}

function goToEventPage(eventID) {
    var uri = document.documentURI;
    uri = uri.substring(0,uri.indexOf("/search"));
    uri = uri+"/event/?e="+eventID;
    window.location.href = uri;
}


function populateTest() {
    var event1 = document.getElementById("event1");
    event1.style.display = "";
    var list = document.getElementById("eventList");

    for (var i = 1; i < 9; i++) {
        var clone = event1.cloneNode(true);
        clone.id = "event" + (i + 1);

        var first = clone.getElementsByClassName("first");
        var title = first[0].getElementsByClassName("searchTitle");
        title[0].innerHTML = "Title " + clone.id;
        var img = first[0].getElementsByClassName("searchEventImg");
        img[0].src = "https://s-media-cache-ak0.pinimg.com/736x/02/94/b5/0294b5cf2112c63a0bc88645332dbd0a.jpg";

        var second = clone.getElementsByClassName("second");
        var description = second[0].getElementsByClassName("searchDescription");
        description[0].innerHTML = "This is " + clone.id + " description.";

        list.appendChild(clone);
    }
}


function changePage(pageNum) {
    var uri = document.documentURI;
    var type = uri.substring(uri.indexOf("&t=")+3);
    var query = uri.substring(uri.indexOf("?q=")+3, uri.indexOf("&p="));
    uri = uri.substring(0,uri.indexOf("?q="));
    window.location.href = uri + "?q="+query+"&p="+pageNum+"&t="+type;
}

function getSearch() {
    var uri = document.documentURI;
    var query = uri.substring(uri.indexOf("?q=")+3, uri.indexOf("&p="));
    var pageNum = uri.substring(uri.indexOf("&p=")+3,uri.indexOf("&t="));
    var type = uri.substring(uri.indexOf("&t=")+3);
    console.log("Type: '"+type+ "'");
    if(query != null && document.getElementById("event1") != null) {
        query = query.replace(/(\%3F)/g, "?");
        query = query.replace(/(\%3D)/g, "=");
        query = query.replace(/(\%26)/g, "&");
        query = query.replace(/(\%2B)/g, "+");
        document.getElementById("searchField").value = query.replace(/\++/g, " ");

        var request = new XMLHttpRequest();
        var search = {};
        search.query = query;
        search.page = pageNum;
        if(type === 'p') {
            request.onload = getEventResponse;
            request.open("POST", "/WEB_war_exploded/app/event/search", true);
        } else if(type === 'e') {
            console.log(1);
            request.onload = getBusinessResponse;
            request.open("POST", "/WEB_war_exploded/app/account/establishment/search", true);
        } else {
            request.onload = getEventResponse;
            search.token = getCookie("token");
            request.open("POST", "/WEB_war_exploded/app/event/search/recommended", true);
        }
        request.setRequestHeader("Content-type", "text/plain");
        request.send(JSON.stringify(search));

    }

}

function homepageSearch() {
    var request = new XMLHttpRequest();
    var search = {};
    search.query = "";
    search.page = "1";
    search.token = getCookie("token");
    request.onload = showOnHomepage;
    request.open("POST", "/WEB_war_exploded/app/event/search/recommended", true);
    request.setRequestHeader("Content-type", "text/plain");
    request.send(JSON.stringify(search));
}

function  showOnHomepage() {
    var uri = document.documentURI;
    console.log(this.status);
    console.log(this.responseText);
    if (this.status === 200 && this.responseText !== null) {
        var response = this.responseText;
        if (response === "No results found") {
            return;
        }

        var eventJson = JSON.parse(response);
        var eventArray = eventJson.result;

        document.getElementById("img1").src = eventArray[0].imageSrc;
        document.getElementById("img2").src = eventArray[1].imageSrc;
        document.getElementById("img3").src = eventArray[2].imageSrc;

        document.getElementById("txt1").innerHTML = eventArray[0].name;
        document.getElementById("txt2").innerHTML = eventArray[1].name;
        document.getElementById("txt3").innerHTML = eventArray[2].name;

        document.getElementById("txt1").href = uri+"event/?e="+eventArray[0].eventID;
        document.getElementById("txt2").href = uri+"event/?e="+eventArray[1].eventID;
        document.getElementById("txt3").href = uri+"event/?e="+eventArray[2].eventID;

    }

}


function search() {

    var searchQuery = document.getElementById("searchField").value.replace(/\s+/g, "+");
    searchQuery = searchQuery.replace(/\?/g, "%3F");
    searchQuery = searchQuery.replace(/\=/g, "%3D");
    searchQuery = searchQuery.replace(/\&/g, "%26");
    searchQuery = searchQuery.replace(/\+/g, "%2B");
    setCookie("search", searchQuery, 1);
    var uri = document.documentURI;
    if (uri.includes("/search")) {
        var type = document.getElementById("searchType").value;
        uri = uri.substring(0,uri.indexOf("?q="));
        if(type === "p") {
            window.location.href = uri + "?q=" + searchQuery + "&p=1&t=p";
        } else if(type === "r"){
            window.location.href = uri + "?q=" + searchQuery + "&p=1&t=r";
        } else {
            window.location.href = uri + "?q=" + searchQuery + "&p=1&t=e";
        }
    } else {
        uri = document.documentURI;
        uri = uri.substring(0,uri.indexOf("homepage/")+9);
        console.log(uri);
            window.location.href = uri + "search/?q=" + searchQuery + "&p=1&t=p";
    }

}
