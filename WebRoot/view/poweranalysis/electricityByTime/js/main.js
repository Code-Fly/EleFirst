/**
 * Created by barrie on 17/1/30.
 */
function saveTreeInfo(info) {
    var params = {
        data: encodeURIComponent($.base64.btoa(JSON.stringify(info)))
    };
    var str = $.param(params);
    $("#centerFrame").attr("src", $("#centerFrame").attr("ref") + "?" + str)
}

function getSelectedNodeInfo() {
    return $(document).contents().find("#frame-tree")[0].contentWindow.getSelectedNodeInfo();
}

function findNode(id) {
    return $(document).contents().find("#frame-tree")[0].contentWindow.findNode(id);
}

$(document).ready(function () {
    $("#btn-tree-tool-refresh").click(function () {
        $("#frame-tree").attr("src", $("#frame-tree").attr("src"));
    });
});