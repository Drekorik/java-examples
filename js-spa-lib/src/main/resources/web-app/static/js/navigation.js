var Navigation = function (wrapperId, scriptClass, styleClass) {
    var _curPage = "main";
    var _navData = {};
    var _urlToPageName = {};

    var _wrapper = document.getElementById(wrapperId);
    var _script = scriptClass;
    var _style = styleClass;

    this.addNavData = function (name, pageRealUrl, pageUrl, cssUrl, jsUrl, initFunction) {
        _navData[name] = {};
        _navData[name]["page"] = pageRealUrl;
        _navData[name]["url"] = pageUrl;
        _navData[name]["style"] = cssUrl;
        _navData[name]["script"] = {};
        _navData[name]["script"]["url"] = jsUrl;
        if (initFunction !== undefined) {
            _navData[name]["script"]["init"] = initFunction;
        }

        _urlToPageName[pageUrl] = name;
    };

    this.initNavigation = function () {
        var urlToPageName = _urlToPageName;
        var popFunc = loadResources;
        window.onpopstate = function (event) {
            popFunc(
                urlToPageName[window.location.pathname] != null
                    ? urlToPageName[window.location.pathname]
                    : urlToPageName["/"]
            )
        };
        loadResources(
            urlToPageName[window.location.pathname] != null
                ? urlToPageName[window.location.pathname]
                : urlToPageName["/"]
        )
    };

    this.changePage = function (page) {
        var state = page;
        window.history.pushState(state, page, _navData[page].url);
        console.log("Pushing");
        loadResources(page);
    };

    var loadResources = function (page) {
        var navDataPage = _navData[page];
        var LocalLoadStyle = loadStyle;
        var localLoadSection = loadSection;
        var localLoadScript = loadScript;
        if (page != _curPage) {
            _wrapper.innerHTML = '';
            LocalLoadStyle(navDataPage.style)
                .then(function (value) {
                    localLoadSection(navDataPage.page)
                        .then(function (value) {
                            localLoadScript(navDataPage.script)
                        });
                });
        }
    };

    var loadSection = function (url) {
        var localWrapper = _wrapper;
        return new Promise(function (resolve, reject) {
            $.ajax({
                url: url,
                method: "GET",
                data: {},
                headers: {},
                success: function (data, textStatus, jqXHR) {
                    localWrapper.innerHTML = data;
                    resolve("HTML loaded");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }
            });
        });
    };

    var loadScript = function (js) {

        return new Promise(function (resolve, reject) {
            if (document.getElementsByClassName(_script).length > 0) {
                Array.from(document.getElementsByClassName(_script)).forEach(function (value) {
                    value.remove();
                })
            }
            var scriptTag = document.createElement('script');
            scriptTag.type = 'text/javascript';
            scriptTag.src = js.url;
            scriptTag.id = guid();
            scriptTag.className = _script;

            var callback = function () {
                var fn = window[js.init];
                if (typeof fn === 'function') {
                    fn();
                }
                resolve("JS loaded");
            };

            scriptTag.onreadystatechange = callback;
            scriptTag.onload = callback;
            document.body.appendChild(scriptTag);
        });
    };

    var loadStyle = function (url) {
        return new Promise(function (resolve, reject) {
            if (document.getElementsByClassName(_style).length > 0) {
                Array.from(document.getElementsByClassName(_style)).forEach(function (value) {
                    value.remove();
                })
            }
            var scriptTag = document.createElement('link');
            scriptTag.type = 'text/css';
            scriptTag.rel = 'stylesheet';
            scriptTag.href = url;
            scriptTag.id = guid();
            scriptTag.className = _style;

            scriptTag.onreadystatechange = resolve("CSS loaded");
            scriptTag.onload = resolve("CSS loaded");
            document.head.appendChild(scriptTag);
        });
    };

    Navigation.prototype.addNavData = this.addNavData;
    Navigation.prototype.initNavigation = this.initNavigation;
    Navigation.prototype.changePage = this.changePage;
};