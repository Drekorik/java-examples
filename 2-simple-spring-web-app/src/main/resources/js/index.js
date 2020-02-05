/**
 * Created by cloudjumper on 6/20/17.
 */
$(document).ready(function () {
    $("#send").on('click', function () {
        window.location = "/" + $("#data").val().toString();
    })
});