/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var data = GetQueryString("data");

    $("#tab-electricity").tabs({
        border: false,
        onSelect: function (title, index) {
            var panel = $(this).tabs("getTab", index);
            var frame = $(panel).find("iframe");
            if ($(frame).attr("src") == undefined || $(frame).attr("src") == "") {
                $(frame).attr("src", $(frame).attr("ref") + "?data=" + data);
            }
        }
    });
    $("#tab-electricity").tabs("unselect", 0);
    $("#tab-electricity").tabs("select", 0);
});