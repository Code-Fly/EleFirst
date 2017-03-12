/**
 * Created by Administrator on 2016/9/20.
 */
var DashboardUtils = {
    build: function (target, params) {
        var hash = Math.ceil(Math.random() * 100000);
        var window = HtmlElm.Div();
        window.addClass("easyui-window");
        window.css("padding", "3px");
        window.attr("id", "window-" + hash + "-" + params.chartId);
        var windowType = "window";
        if ("custom" != params.typeset) {
            windowType = "panel";
        }
        var option = {
            loading: 0
        };
        window.append("<input class='hid-chart-info' type='hidden' value='" + JSON.stringify(option) + "'>");
        $(target).append(window);

        return $("#window-" + hash + "-" + params.chartId)[windowType]({
            collapsible: true,
            minimizable: false,
            maximizable: false,
            draggable: params.draggable,
            resizable: params.resizable,
            closable: params.closable,
            inline: true,
            title: params.chartName,
            border: "thin",
            width: params.width,
            height: params.height,
            cls: "custom" == params.typeset ? "draggable-window" : "fixable-window",
            tools: [
                {
                    iconCls: "icon-arrow_refresh btn-chart-refresh",
                    handler: function () {
                        // $(this).toggleClass("icon-arrow_refresh_small");
                        var option = $("#window-" + hash + "-" + params.chartId).find("input[type=hidden]").val();
                        $("#window-" + hash + "-" + params.chartId)[windowType]("resize");
                    }
                }],
            onOpen: function () {
                if ("custom" == params.typeset) {
                    $(this).window("move", {
                        top: params.top,
                        left: params.left
                    });
                }
            },
            onResize: function (width, height) {
                // var option = $.parseJSON($(this).find("input[type=hidden]").val());
                if (DashboardUtils.repair(width) != width || DashboardUtils.repair(height) != height) {
                    $(this).window("resize", {
                        width: DashboardUtils.repair(width),
                        height: DashboardUtils.repair(height)
                    });
                    return;
                }

                var cId = $(this).attr("id");

                var target = this;

                $.ajax({
                    url: _ctx + "ace/datasource/data/query.do",
                    type: "POST",
                    cache: false,
                    data: {
                        sql: SQLUtils.build(params.dbName, params.tbName, params.itemColumns, params.itemLimit)
                    },
                    success: function (r) {
                        if (r.hasOwnProperty("errcode")) {
                            if ("0" == r.errcode) {
                                var oldOpt = $.parseJSON($(target).find("input[type=hidden]").val());

                                if (undefined != ChartUtils[params.itemType] && undefined != ChartUtils[params.itemType][params.itemName]) {
                                    ChartUtils[params.itemType][params.itemName].load($(target), true, {
                                        id: params.chartId,
                                        name: params.chartName == undefined ? "未命名图表" : params.chartName,
                                        width: $(target).width(),
                                        height: $(target).height()
                                    }, params.itemColumns, r.data);


                                    $(target).append("<input type='hidden' class='hid-chart-info' value='{}'>");
                                    // option.width = width;
                                    // option.height = height;
                                    // if (!option.hasOwnProperty("top")) {
                                    //     option.left = params.left;
                                    //     option.top = params.top;
                                    // }
                                    // option.draggable = params.draggable;
                                    // option.resizable = params.resizable;
                                    // option.itemType = params.itemType;
                                    // option.itemName = params.itemName;
                                    // option.itemColumns = params.itemColumns;
                                    // option.chartId = params.chartId;
                                    // option.chartName = params.chartName;
                                    // option.dbName = params.dbName;
                                    // option.tbName = params.tbName;

                                    var option = clone(params);
                                    option.top = oldOpt.top;
                                    option.left = oldOpt.left;
                                    option.width = width;
                                    option.height = height;
                                    option.loading = oldOpt.loading;
                                    $(target).find("input[type=hidden]").val(JSON.stringify(option));
                                }
                            } else {
                                jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                            }
                        } else {
                            jError("请求失败！" + ErrUtils.getMsg("2"));
                        }
                    },
                    beforeSend: function (XMLHttpRequest) {
                        var option = $.parseJSON($(target).find("input[type=hidden]").val());
                        option.loading++;
                        $(target).empty();
                        $(target).append("<div class='datagrid-loading' style='margin: " + height * 0.3 + "px auto'>处理中，请稍待。。。</div>");
                        $(target).append("<input class='hid-chart-info' type='hidden' value='{}'>");
                        $(target).find("input[type=hidden]").val(JSON.stringify(option));

                        // loading

                        // var $maskMsg = $("<div class='datagrid-mask-msg' style='display: block; z-index: 10000;left: 30%'>处理中，请稍后。。。</div>").appendTo($(target));
                        // var scrollTop = $(target).scrollTop();
                        // $maskMsg.css({
                        //     left: ( $(target).outerWidth(true) - $maskMsg.outerWidth(true) ) / 2,
                        //     top: ( ($(window).height() - $maskMsg.outerHeight(true)) / 2 ) + scrollTop
                        // });
                    },
                    error: function (request) {
                        jError("请求失败！" + ErrUtils.getMsg("3"));
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        var option = $.parseJSON($(target).find("input[type=hidden]").val());
                        option.loading--;
                        $(target).find("input[type=hidden]").val(JSON.stringify(option));
                    }
                });
            },
            onMove: function (left, top) {
                if (left < 0) {
                    $(this).window("move", {
                        left: 0,
                    });
                    return;
                }
                if (top < 0) {
                    $(this).window("move", {
                        top: 0,
                    });
                    return;
                }
                if (DashboardUtils.repair(left) != left || DashboardUtils.repair(top) != top) {
                    $(this).window("move", {
                        left: DashboardUtils.repair(left),
                        top: DashboardUtils.repair(top)
                    });
                    return;
                }
                var option = $.parseJSON($(this).find("input[type=hidden]").val());
                option.left = left;
                option.top = top;
                $(this).find("input[type=hidden]").val(JSON.stringify(option));
            },
            onClose: function () {
                $(this)[windowType]("destroy");
                // alert($(this).find("input[type=hidden]").val());
            }
        })
        // .resizable({
        //     onResize:function(){
        //         // $(this).panel('resize');
        //     },
        //     onStopResize:function(e){
        //         var d = e.data;
        //         $(this).panel("resize",{
        //             width: $(d.target).width(),
        //             height: $(d.target).height()
        //         });
        //     }
        // });
    },
    build2: function (target, params) {
        var hash = Math.ceil(Math.random() * 100000);
        var chartId = params.chartId;
        var chartParam = params.chartParam;
        var chartUrl = _ctx + params.chartUrl;
        var configUrl = _ctx + params.configUrl;

        var width = params.width;
        var height = params.height;
        var top = params.top;
        var left = params.left;

        if (params.fit) {
            width = "100%";
            height = "100%";
            top = 0;
            left = 0;
        }

        var draggable = params.draggable;
        var resizable = params.resizable;
        var closable = params.closable;
        var editable = params.editable;
        var tool = null;
        if (editable) {
            tool = [
                {
                    iconCls: "icon-pencil",
                    handler: function () {
                        var winOpt = $("#window-" + chartId + "-" + hash).window("options");
                        params.width = winOpt.width;
                        params.height = winOpt.height;
                        params.top = winOpt.top;
                        params.left = winOpt.left;
                        params.id = "window-" + chartId + "-" + hash;
                        $("#frame-chart-config").attr("src", configUrl + "?data=" + encodeURIComponent($.base64.btoa(JSON.stringify(params))));
                        $("#dialog-chart-config").dialog("open");
                        // $("#window-" + chartId + "-" + hash).window("close");
                    }
                },
                {
                    iconCls: "icon-arrow_refresh btn-chart-refresh",
                    handler: function () {
                        var frame = $(this).parent().parent().parent().find("iframe");
                        $(frame).attr("src", $(frame).attr("src"));
                    }
                }
            ]
        } else {
            tool = [
                {
                    iconCls: "icon-arrow_refresh btn-chart-refresh",
                    handler: function () {
                        var frame = $(this).parent().parent().parent().find("iframe");
                        $(frame).attr("src", $(frame).attr("src"));
                    }
                }
            ]
        }

        $(target).append('<div id="window-' + chartId + "-" + hash + '" app-params="' + $.base64.btoa(JSON.stringify(params)) + '" class="chart-item easyui-window" style="padding: 3px;"/>');

        $("#window-" + chartId + "-" + hash).window({
            title: chartParam.title,
            content: '<iframe src="' + chartUrl + '?data=' + encodeURIComponent($.base64.btoa(JSON.stringify(chartParam))) + '" data-url="' + chartUrl + '" style="width: 100%;height: 100%;" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>',
            width: width,
            height: height,
            border: "thin",
            inline: true,
            draggable: draggable,
            resizable: resizable,
            closable: closable,
            collapsible: true,
            minimizable: false,
            maximizable: false,
            tools: tool,
            onOpen: function () {
                // var frame = $(this).find("iframe");
                // $(frame).attr("src", _ctx + $(frame).attr("data-url") + "?data=" + encodeURIComponent($.base64.btoa(JSON.stringify(chartParam))));
            },
            onResize: function (width, height) {
                if (DashboardUtils.repair(width) != width || DashboardUtils.repair(height) != height) {
                    $(this).window("resize", {
                        width: DashboardUtils.repair(width),
                        height: DashboardUtils.repair(height)
                    });
                    return;
                }
                var frame = $(this).find("iframe");
                $(frame).attr("src", $(frame).attr("src"));
            },
            onMove: function (left, top) {
                if (left < 0) {
                    $(this).window("move", {
                        left: 0,
                    });
                    return;
                }
                if (top < 0) {
                    $(this).window("move", {
                        top: 0,
                    });
                    return;
                }

                if (DashboardUtils.repair(left) != left || DashboardUtils.repair(top) != top) {
                    $(this).window("move", {
                        left: DashboardUtils.repair(left),
                        top: DashboardUtils.repair(top)
                    });
                    return;
                }
            },
            onClose: function () {
                $(this).window("destroy");
            },
        });

        $("#window-" + chartId + "-" + hash).window("move", {
            left: DashboardUtils.repair(left),
            top: DashboardUtils.repair(top)
        });
    },
    repair: function (v) {
        if (!(v + "").endsWith("%")) {
            var r = parseInt(v / 20) * 20;
            if (Math.abs(v % 20) > 10) {
                r += v > 0 ? 20 : -20;
            }
            return r;
        }
        return v;
    }
}
