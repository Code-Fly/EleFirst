/**
 * Created by barrie on 2017/7/17.
 */
$(document).ready(function () {
    $("#dgList_1").datagrid({
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        data: []
    });

    $("#btn-pn-tool-search_1").linkbutton({
        onClick: function () {
            $("#cc1").layout("expand", "south")
        }
    });

    $("#btn-pn-tool-search_2").linkbutton({
        onClick: function () {
            $("#cc2").layout("expand", "south")
        }
    });

    $("#btn-pn-tool-search_3").linkbutton({
        onClick: function () {
            $("#cc3").layout("expand", "south")
        }
    });
});