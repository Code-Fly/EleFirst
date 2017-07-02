/**
 * Created by barrie on 2017/7/2.
 */
$(document).ready(function () {
    $("#dgList").datagrid({
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        data: [
            {
                userName: "魏嘉志",
                userCode: "1470901301",
                electricity: "201.3",
                water: "35.3",
                gas: "21.1",
                heat: "62.3",
                property: "15",
                total: "335",
                pay: "350",
                change: "15"
            },
            {
                userName: "班旦",
                userCode: "1470901302",
                electricity: "142.5",
                water: "22.1",
                gas: "0",
                heat: "55.2",
                property: "10",
                total: "229.8",
                pay: "230",
                change: "0.2"
            },
            {
                userName: "鄂听枫",
                userCode: "1470901303",
                electricity: "302.1",
                water: "44.9",
                gas: "40.2",
                heat: "77.1",
                property: "15",
                total: "479.3",
                pay: "-",
                change: "-"
            }
        ]
    });


    $("#btn-pn-tool-add").linkbutton({
        onClick: function () {
            $("#dlg-add-user").dialog("open");
        }
    });


    $("#btn-dlg-add-user-submit").linkbutton({
        onClick: function () {
            $("#dlg-add-user").dialog("close");
        }
    });

});