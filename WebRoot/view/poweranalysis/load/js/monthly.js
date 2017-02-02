/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var data = $.base64.atob(decodeURIComponent(GetQueryString("data")), true);

    DateBoxUtils.initMonthBox($("#datebox-time-start"));

    DateBoxUtils.initMonthBox($("#datebox-time-end"));

});