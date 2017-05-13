/**
 * Created by phil on 5/10/17.
 */

function getResponse() {
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

            var first = event1.getElementsByClassName("first");
            var title = first[0].getElementsByClassName("searchTitle");
            var img = first[0].getElementsByClassName("searchEventImg");

            var second = event1.getElementsByClassName("second");
            var description = second[0].getElementsByClassName("searchDescription");
            title[0].innerHTML = eventArray[0].name;
            img[0].src = eventArray[0].imageSrc;
            description[0].innerHTML = eventArray[0].description;

            for ( i = 1; i < eventArray.length; i++) {
                var clone = event1.cloneNode(true);
                clone.id = "event"+ (i+1);

                first = clone.getElementsByClassName("first");
                title = first[0].getElementsByClassName("searchTitle");
                img = first[0].getElementsByClassName("searchEventImg");

                second = clone.getElementsByClassName("second");
                description = second[0].getElementsByClassName("searchDescription");
                title[0].innerHTML = eventArray[i].name;
                img[0].src = eventArray[i].imageSrc;
                description[0].innerHTML = eventArray[i].description;

                list.appendChild(clone);
            }
            document.body.style.height ="100%";
        }
    }
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
    var query = uri.substring(uri.indexOf("?q=")+3, uri.indexOf("&p="));
    uri = uri.substring(0,uri.indexOf("?q="));
    window.location.href = uri + "?q="+query+"&p="+pageNum;
}

function getSearch() {
    var uri = document.documentURI;
    var query = uri.substring(uri.indexOf("?q=")+3, uri.indexOf("&p="));
    var pageNum = uri.substring(uri.indexOf("&p=")+3);
    console.log("page num: '"+pageNum+ "'");
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
        request.onload = getResponse;
        request.open("POST", "/WEB_war_exploded/app/event/search", true);
        request.setRequestHeader("Content-type", "text/plain");
        request.send(JSON.stringify(search));

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
        uri = uri.substring(0,uri.indexOf("?q="));
        window.location.href = uri + "?q="+searchQuery+"&p=1";
    } else {
        window.location.href = document.documentURI + "search/?q="+searchQuery+"&p=1";
    }

}
