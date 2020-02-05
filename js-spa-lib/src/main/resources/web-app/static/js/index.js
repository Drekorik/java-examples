var nav;

$(document).ready(function () {
    nav = new Navigation("content-wrapper", "page-js", "css-js");
    nav.addNavData("page1", "/pages/page1", "/page1", "static/css/css1.css", "static/js/js1.js", "init1");
    nav.addNavData("page2", "/pages/page2", "/page2", "static/css/css2.css", "static/js/js2.js", "init2");
    nav.addNavData("indexPage", "/", "/", "", "");
    nav.initNavigation();
});