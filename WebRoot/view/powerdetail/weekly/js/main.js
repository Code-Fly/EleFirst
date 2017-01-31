/**
 * Created by barrie on 17/1/26.
 */
function saveTreeInfo(info) {
    var params = {
        data: encodeURIComponent($.base64.btoa(JSON.stringify(info)))
    };
    var str = $.param(params);
    $("#centerFrame").attr("src", $("#centerFrame").attr("ref") + "?" + str)
}

$(document).ready(function () {
    $("#btn-tree-tool-refresh").click(function () {
        $("#frame-tree").attr("src", $("#frame-tree").attr("src"));
    });
});