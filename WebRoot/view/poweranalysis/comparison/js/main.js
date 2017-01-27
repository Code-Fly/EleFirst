/**
 * Created by barrie on 17/1/27.
 */
$(document).ready(function () {
    $("#input-pn").tagbox({
        buttonAlign: "right",
        buttonIcon: "icon-calendar",
        editable: false,
        onClickButton: function () {
            alert("click button");
        }
    });

    $("#input-time").tagbox({
        buttonAlign: "right",
        buttonIcon: "icon-lightning",
        editable: false,
        onClickButton: function () {
            alert("click button");
        }
    });
});