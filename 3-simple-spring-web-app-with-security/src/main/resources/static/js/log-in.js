$(document).on("click", "#send", function () {
    $.ajax({
        url: "log-in",
        method: "POST",
        data: {
            username: $("#login").val(),
            password: $("#password").val()
        },
        headers: {},
        success: function (data, textStatus, jqXHR) {
            // window.location = "/secured";
        },
        error: function (jqXHR, textStatus, errorThrown) {
        }
    });
});