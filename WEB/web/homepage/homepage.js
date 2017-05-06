/**
 * Created by phil on 5/6/17.
 */

var slideIndex = 1;
var mouseOnImg = false;

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("dot");
    if (n > slides.length) {slideIndex = 1}
    if (n < 1) {slideIndex = slides.length}
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" dotActive", "");
    }
    slides[slideIndex-1].style.display = "block";
    if(mouseOnImg) {
        dots[slideIndex - 1].className += " dotActive";
    }
}

function onImg() {
    console.log("on")
    mouseOnImg = true;
    var elems= document.getElementsByClassName("imgOverlayBorderColor");
    for(var i = 0; i < elems.length; i++) {
        console.log(elems)
        elems[i].style.display = "block";
    }
    elems = document.getElementsByClassName("imgOverlayColor");
    for(var i = 0; i < elems.length; i++) {
        console.log(elems)
        elems[i].style.display = "block";
    }
    elems = document.getElementsByClassName("dot");
    for(var i = 0; i < elems.length; i++) {
        console.log(elems)
        elems[i].style.display = "inline-flex";
    }

}


function offImg() {
    console.log("off")
    mouseOnImg = false;
    var elems= document.getElementsByClassName("imgOverlayBorderColor");
    for(var i = 0; i < elems.length; i++) {
        console.log(elems)
        elems[i].style.display = "none";
    }
    elems = document.getElementsByClassName("imgOverlayColor");
    for(var i = 0; i < elems.length; i++) {
        console.log(elems)
        elems[i].style.display = "none";
    }

}