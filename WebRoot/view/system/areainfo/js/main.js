/**
 * Created by VM on 2/9/2017.
 */
$(document).ready(function () {
    $(".easyui-datebox").datebox("calendar").calendar({
        firstDay: 1
    });

    $("#btn-reset").linkbutton({
        onClick: function () {
            $("#ff").form('clear');
        }
    });

    $("#btn-submit").linkbutton({
        onClick: function () {
            alert(JSON.stringify(formToJson($("#ff"))))
        }
    });

});