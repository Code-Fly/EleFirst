/**
 * Created by barrie on 17/1/23.
 */
$(document).ready(function () {
    if (!mxClient.isBrowserSupported()) {
        // Displays an error message if the browser is not supported.
        mxUtils.error('Browser is not supported!', 200, false);
    } else {
        require([_ctx + "view/diagram/js/graph.js"], function () {
            require([_ctx + "view/diagram/js/popupmenu.js"]);
            require([_ctx + "view/diagram/js/toolbar.js"]);
            require([_ctx + "view/diagram/js/tooltips.js"]);
            require([_ctx + "view/diagram/js/wire.js"]);
            require([_ctx + "view/diagram/js/style.js"]);
            require([_ctx + "view/diagram/js/main.js"]);
        });
    }
});