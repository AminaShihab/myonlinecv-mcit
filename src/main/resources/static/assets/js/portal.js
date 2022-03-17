$(document).ready(function() {
    var getUrl = window.location;
    var path = getUrl.pathname.split('/')[1];
    if (path == "") {
        $("#sidemenue").find(".home").addClass("active");
    } else {
        $("#sidemenue").find("a[href$=" + path + "]").addClass("active");
    }
});