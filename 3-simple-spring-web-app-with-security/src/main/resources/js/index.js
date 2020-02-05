/**
 * Created by cloudjumper on 6/20/17.
 */
$(document).ready(function () {
    $("#send").on('click', function () {
        window.location = "/?data=" + $("#data").val().toString();
    })
});

$(document).ready(function () {
    $("#log-in").on('click', function () {
        window.location = "/log-in";
    })
});

$(document).ready(function () {
    $("#secured1").on('click', function () {
        window.location = "/secured";
    })
});

$(document).ready(function () {
    $("#secured2").on('click', function () {
        window.location = "/secured_method";
    })
});