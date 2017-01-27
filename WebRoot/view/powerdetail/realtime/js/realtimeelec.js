/**
 * Created by barrie on 17/1/27.
 */
$(document).ready(function () {
    var data = $.parseJSON($.base64.atob(decodeURIComponent(GetQueryString("data")), true));

    $.messager.alert("信息提示", JSON.stringify(data), "info");

    $("#tt1").datagrid({
        url: _ctx + 'powerdetail/listCurrentDetailPower.do',
        //pagination : true,
        //rownumbers : true,
        pageSize: 2,
        pageList: [2, 20, 30],
        singleSelect: false,
        fit: true,
        loadMsg: '正在处理,请稍后....',
        queryParams: {
            jasonStr: '<%=powerDetailJason%>'
        },
        onLoadError: function () {
            jError("查询监测点信息错误！", {
                VerticalPosition: 'center',
                HorizontalPosition: 'center',
                ShowOverlay: false
            });
        }
    });
});