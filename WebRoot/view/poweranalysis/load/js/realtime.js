/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var data = $.base64.atob(decodeURIComponent(GetQueryString("data")), true);

    $("#datebox-time").datebox({
        required: true,
        editable: false
    });
});