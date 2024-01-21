const div = document.getElementById('postForm');

function showDiv() {
    if (div.style.display == "block") {
        div.style.display = "none";
        document.body.style.overflow = 'visible';
    } else {
        document.body.style.overflow = 'hidden';
        div.style.display = "block";
    }
}