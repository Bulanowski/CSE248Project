/**
 * Created by phil on 5/10/17.
 */

function getResponse() {
    if (this.status == 200 && this.responseText != null) {
        var response = this.responseText;
        response = "the";
        if(response == "No results found") {
            var noResults = document.getElementById("noResults");
            noResults.style.display = "";

        } else {
            // var eventJson = JSON.parse(response);
            // var eventArray = eventJson.result;
            var event1 = document.getElementById("event1");
            event1.style.display = "";
            var list = document.getElementById("eventList");

            for (var i = 1; i < 9; i++) {
                var clone = event1.cloneNode(true);
                clone.id = "event"+ (i+1);

                var first = clone.getElementsByClassName("first");
                var title = first[0].getElementsByClassName("searchTitle");
                title[0].innerHTML = "Title "+clone.id;
                var img = first[0].getElementsByClassName("searchEventImg");
                img[0].src = "https://s-media-cache-ak0.pinimg.com/736x/02/94/b5/0294b5cf2112c63a0bc88645332dbd0a.jpg";

                var second = clone.getElementsByClassName("second");
                var description = second[0].getElementsByClassName("searchDescription");
                description[0].innerHTML = "This is "+ clone.id + " description.";

                list.appendChild(clone);
            }
        }
    }
}

function getSearch(pageNum) {
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
    setCookie("search",searchQuery,1);
    var uri = document.documentURI;
    if(uri.includes("/search/")) {
        location.reload();
    } else {
        window.location.href = document.documentURI+"search/";
    }
}
