/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var pStart = $("#datebox-time-start").datebox("panel"); //日期选择对象
    var tdsStart = false;//日期选择对象中月份
    var yearIptStart = pStart.find("input.calendar-menu-year");//年份输入框
    var spanStart = pStart.find("span.calendar-text"); //显示月份层的触发控件
    console.log(yearIptStart)

    $("#datebox-time-start").datebox({
        required: true,
        onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
            spanStart.trigger("click"); //触发click事件弹出月份层
            if (!tdsStart) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                tdsStart = pStart.find("div.calendar-menu-month-inner td");
                tdsStart.click(function (e) {
                    e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                    var year = /\d{4}/.exec(spanStart.html())[0];//得到年份
                    var month = parseInt($(this).attr("abbr"), 10); //月份，这里不需要+1
                    $("#datebox-time-start").datebox("hidePanel")//隐藏日期对象
                        .datebox("setValue", year + "-" + month); //设置日期的值
                });
            }, 0);
            yearIptStart.unbind();//解绑年份输入框中任何事件
        },
        parser: function (s) {
            if (!s) return new Date();
            var arr = s.split("-");
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
        },
        formatter: function (d) {
            var a = parseInt(d.getMonth()) < parseInt("9") ? "0" + parseInt(d.getMonth() + 1) : d.getMonth() + 1;
            return d.getFullYear() + "-" + a;
        }
    });

    var pEnd = $("#datebox-time-end").datebox("panel"); //日期选择对象
    var tdsEnd = false;//日期选择对象中月份
    var yearIptEnd = pEnd.find("input.calendar-menu-year");//年份输入框
    var spanEnd = pEnd.find("span.calendar-text"); //显示月份层的触发控件
    console.log(yearIptEnd)

    $("#datebox-time-end").datebox({
        required: true,
        onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
            spanEnd.trigger("click"); //触发click事件弹出月份层
            if (!tdsEnd) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                tdsEnd = pEnd.find("div.calendar-menu-month-inner td");
                tdsEnd.click(function (e) {
                    e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                    var year = /\d{4}/.exec(spanEnd.html())[0];//得到年份
                    var month = parseInt($(this).attr("abbr"), 10); //月份，这里不需要+1
                    $("#datebox-time-end").datebox("hidePanel")//隐藏日期对象
                        .datebox("setValue", year + "-" + month); //设置日期的值
                });
            }, 0);
            yearIptEnd.unbind();//解绑年份输入框中任何事件
        },
        parser: function (s) {
            if (!s) return new Date();
            var arr = s.split("-");
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
        },
        formatter: function (d) {
            var a = parseInt(d.getMonth()) < parseInt("9") ? "0" + parseInt(d.getMonth() + 1) : d.getMonth() + 1;
            return d.getFullYear() + "-" + a;
        }
    });

});