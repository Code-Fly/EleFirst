/**
 * Created by barrie on 17/1/23.
 */
$(document).ready(function () {
    BASE_PATH = "../../";

    if (!mxClient.isBrowserSupported()) {
        // Displays an error message if the browser is not supported.
        $.messager.alert("操作提示", "浏览器不支持！", "info");
    } else {
        // base js
        require(["config/constants.js", "config/utils.js", "base/graph.js"], function () {
            require([
                "base/wire.js",
                "base/style.js",

                // config file
                "config/object.js",
                "config/menu.js",
                "config/toolbar.js",
                "config/tooltips.js",
            ], function () {
                // main js
                require(["js/main.js"]);
            });
        });
    }
});