var stompBroadcast = null;
var stompUser = null;
var user = 'anonymous';

function connectBroadCast() {
    var socket = new SockJS('/sock-end-point');
    stompBroadcast = Stomp.over(socket);
    stompBroadcast.debug = function () {
    };
}

function connectUser() {
    var socket = new SockJS('/sock-end-point');
    stompUser = Stomp.over(socket);
    stompUser.debug = function () {
    };
}

function subscribeToUser() {
    if (stompUser == null) {
        connectUser()
    }
    stompUser.connect({}, function (frame) {
        $("#connect").prop('disabled', true);
        $("#disconnect").prop('disabled', false);
        console.log('Connected: ' + frame);
        stompUser.subscribe('/user/' + frame.headers['user-name'] + '/subscribe/data', function (response) {
            writeResponse(response.body);
        });
    });
}

function subscribeToBroadcast() {
    if (stompBroadcast == null) {
        connectBroadCast()
    }
    stompBroadcast.connect({}, function (frame) {
        $("#connect_b").prop('disabled', true);
        $("#disconnect_b").prop('disabled', false);
        console.log('Connected: ' + frame);
        stompBroadcast.subscribe('/subscribe/data', function (response) {
            writeResponse(response.body);
        });
    });
}

function disconnectUser() {
    stompUser.disconnect();
    $("#connect").prop('disabled', false);
    $("#disconnect").prop('disabled', true);
    console.log("Disconnected");
}

function disconnectBroadcast() {
    stompBroadcast.disconnect();
    $("#connect_b").prop('disabled', false);
    $("#disconnect_b").prop('disabled', true);
    console.log("Disconnected");
}

function sendData() {
    var json = {'name': $("#data").val()};
    stompBroadcast.send("/sock-prefix/send", {}, JSON.stringify(json));
}

function writeResponse(message) {
    var response = $("<p></p>").text(message);
    $("#response").append(response);
}

$("#connect").on("click", function () {
    subscribeToUser();
});

$("#connect_b").on("click", function () {
    subscribeToBroadcast();
});

$("#disconnect").on("click", function () {
    disconnectUser();
});

$("#disconnect_b").on("click", function () {
    disconnectBroadcast();
});

$("#sendData").on("click", function () {
    sendData();
});

$("#push").on("click", function () {
    $.ajax({
        url: "/sock",
        method: "GET",
        headers: {},
        success: function (data, textStatus, jqXHR) {
            console.log(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {

        }
    });
});

$("#push_b").on("click", function () {
    $.ajax({
        url: "/sock-b",
        method: "GET",
        headers: {},
        success: function (data, textStatus, jqXHR) {
            console.log(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {

        }
    });
});

$("#log-in").on("click", function () {
    $.ajax({
        url: "/log-in",
        method: "POST",
        data: {
            "username": $("#username").val(),
            "password": $("#password").val()
        },
        headers: {},
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data, textStatus, jqXHR) {
            console.log("success");
            location.reload();
            // location.assign(data.page != null ? data.page : "/")
        },
        error: function (jqXHR, textStatus, errorThrown) {
            var response = $.parseJSON(jqXHR.responseText);
            console.error(response);
        }
    });
});