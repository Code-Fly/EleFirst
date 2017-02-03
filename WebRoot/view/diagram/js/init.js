/**
 * Created by barrie on 17/1/23.
 */
$(document).ready(function () {
    BASE_PATH = "../../";

    require([_ctx + "view/diagram/config/constants.js"]);
    require([_ctx + "view/diagram/config/utils.js"]);


    if (!mxClient.isBrowserSupported()) {
        // Displays an error message if the browser is not supported.
        $.messager.alert("操作提示", "浏览器不支持！", "info");
    } else {
        // base js
        require([_ctx + "view/diagram/base/graph.js"], function () {
            require([_ctx + "view/diagram/base/wire.js"]);
            require([_ctx + "view/diagram/base/style.js"]);

            // config file
            require([_ctx + "view/diagram/config/object.js"]);
            require([_ctx + "view/diagram/config/menu.js"]);
            require([_ctx + "view/diagram/config/toolbar.js"]);
            require([_ctx + "view/diagram/config/tooltips.js"]);

            // main js
            require([_ctx + "view/diagram/js/main.js"]);
        });
    }
});