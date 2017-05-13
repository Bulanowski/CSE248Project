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
            pagebtn[0].href = uri+1;
            for(var i = 0; i < pages-1; i++) {
                var pageClone = pagebtn[0].cloneNode(true);
                pageClone.innerHTML = i+2;
                pageClone.href =  uri+(i+2);
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

function getSearch() {
    var uri = document.documentURI;
    var pageNum = uri.substring(uri.indexOf("?=")+2);
    console.log("page num: '"+pageNum+ "'");
    if(getCookie("search") != null && document.getElementById("event1") != null) {
        document.getElementById("searchField").value = getCookie("search");
        var request = new XMLHttpRequest();
        var search = {};
        search.query = getCookie("search");
        search.page = pageNum;
        request.onload = getResponse;
        request.open("POST", "/WEB_war_exploded/app/event/search", true);
        request.setRequestHeader("Content-type", "text/plain");
        request.send(JSON.stringify(search));

    }

}


function search() {

    var searchQuery = document.getElementById("searchField").value;
    setCookie("search", searchQuery, 1);
    var uri = document.documentURI;
    if (uri.includes("/search/")) {
        location.reload();
    } else {
        window.location.href = document.documentURI + "search/?=1";
    }

}
