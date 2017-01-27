function saveTreeInfo(info) {
    var params = {
        data: encodeURIComponent($.base64.btoa(JSON.stringify(info)))
    };
    var str = $.param(params);
    $("#centerFrame").attr("src", $("#centerFrame").attr("ref") + "?" + str)
}

$(document).ready(function () {

});