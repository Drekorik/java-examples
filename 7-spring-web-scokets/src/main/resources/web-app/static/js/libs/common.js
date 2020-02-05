/**
 * Created by cloudjumper on 8/21/17.
 */
String.format = function () {
    var s = arguments[0];
    for (var i = 1; i < arguments.length; i++) {
        var reg = new RegExp("\\{" + (i - 1) + "\\}", "gm");
        s = s.replace(reg, arguments[i]);
    }

    return s;
};