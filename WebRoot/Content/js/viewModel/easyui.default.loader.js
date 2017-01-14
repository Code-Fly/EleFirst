/**
 * Created by 123 on 2016/5/12.
 */
$.fn.datagrid.defaults.loader = function (param, success, error) {
    var opts = $(this).datagrid("options");
    if (!opts.url)
        return false;
    $.ajax({
        type: opts.method,
        url: opts.url,
        data: param,
        dataType: "json",
        success: function (data) {
            // load from local data
            if (isArray(data)) {
                success(data);
                return;
            }
            // load from remote data
            if (data.hasOwnProperty("errcode")) {
                if ("0" == data.errcode) {
                    success(data.data);
                } else {
                    error.apply(this, arguments);
                }
            } else {
                error({
                    errcode: "2",
                    errmsg: "failed"
                });
            }
        },
        error: function () {
            error({
                errcode: "3",
                errmsg: "failed"
            });
        }
    });
};

$.fn.datalist.defaults.loader = function (param, success, error) {
    var opts = $(this).datalist("options");
    if (!opts.url)
        return false;
    $.ajax({
        type: opts.method,
        url: opts.url,
        data: param,
        dataType: "json",
        success: function (data) {
            // load from local data
            if (isArray(data)) {
                success(data);
                return;
            }
            // load from remote data
            if (data.hasOwnProperty("errcode")) {
                if ("0" == data.errcode) {
                    success(data.data);
                } else {
                    error.apply(this, arguments);
                }
            } else {
                error({
                    errcode: "2",
                    errmsg: "failed"
                });
            }
        },
        error: function () {
            error({
                errcode: "3",
                errmsg: "failed"
            });
        }
    });
};

$.fn.combobox.defaults.loader = function (param, success, error) {
    var opts = $(this).combobox("options");
    if (!opts.url)
        return false;
    $.ajax({
        type: opts.method,
        url: opts.url,
        data: param,
        dataType: "json",
        success: function (data) {
            // load from local data
            if (isArray(data)) {
                success(data);
                return;
            }
            // load from remote data
            if (data.hasOwnProperty("errcode")) {
                if ("0" == data.errcode) {
                    success(data.data);
                } else if ("1" == data.errcode) {
                    var p = window;
                    while (p != p.parent) {
                        p = p.parent;
                    }
                    p.location.href = _ctx + "login.jsp?sessionstatus=timeout";
                } else {
                    error.apply(this, arguments);
                }
            } else {
                error({
                    errcode: "2",
                    errmsg: "failed"
                });
            }
        },
        error: function () {
            error({
                errcode: "3",
                errmsg: "failed"
            });
        }
    });
};

$.fn.tree.defaults.loader = function (param, success, error) {
    var opts = $(this).tree("options");
    if (!opts.url)
        return false;
    $.ajax({
        type: opts.method,
        url: opts.url,
        data: param,
        dataType: "json",
        success: function (data) {
            // load from local data
            if (isArray(data)) {
                success(data);
                return;
            }
            // load from remote data
            if (data.hasOwnProperty("errcode")) {
                if ("0" == data.errcode) {
                    success(data.data);
                } else if ("1" == data.errcode) {
                    var p = window;
                    while (p != p.parent) {
                        p = p.parent;
                    }
                    p.location.href = _ctx + "login.jsp?sessionstatus=timeout";
                } else {
                    error.apply(this, arguments);
                }
            } else {
                error({
                    errcode: "2",
                    errmsg: "failed"
                });
            }
        },
        error: function () {
            error({
                errcode: "3",
                errmsg: "failed"
            });
        }
    });
};

$.extend($.fn.tree.methods, {
    unselect: function (jq, target) {
        return jq.each(function () {
            var opts = $(this).tree('options');
            $(target).removeClass('tree-node-selected');
            if (opts.onUnselect) {
                opts.onUnselect.call(this, $(this).tree('getNode', target));
            }
        });
    }
});

// $.fn.datagrid.defaults.loader = function(param, success, error){
// var target = this;
// var opts = $(target).datagrid('options');
// if (!opts.url) return false;
// $.ajax({
// type: opts.method,
// url: opts.url,
// data: param,
// dataType: 'json',
// success: function(data){
// var onLoadSuccess = opts.onLoadSuccess;
// opts.onLoadSuccess = function(){};
// success(data);
// opts.onLoadSuccess = onLoadSuccess;
// opts.onLoadSuccess.call(target, data);
// },
// error: function(){
// error.apply(this, arguments);
// }
// });
// };
