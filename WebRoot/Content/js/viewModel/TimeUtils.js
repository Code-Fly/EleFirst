/**
 * Created by Administrator on 2016/8/31.
 */
/**
 * 时间工具类
 * @type {{toDateString, toGMTString, toISOString, toJSON, toLocaleDateString, toLocaleString, toLocaleTimeString, toString, toTimeString, toUTCString}}
 */
var TimeUtils = {
    weekFromODBC: function (i) {
        var map = {
            1: 0,
            2: 1,
            3: 2,
            4: 3,
            5: 4,
            6: 5,
            0: 6
        };
        return map[i];
    },
    toDateString: function (timestamp) {
        var newDate = new Date();
        newDate.setTime(timestamp);
        return newDate.toDateString();
    },
    toGMTString: function (timestamp) {
        var newDate = new Date();
        newDate.setTime(timestamp);
        return newDate.toGMTString();
    },
    toISOString: function (timestamp) {
        var newDate = new Date();
        newDate.setTime(timestamp);
        return newDate.toISOString();
    },
    toJSON: function (timestamp) {
        var newDate = new Date();
        newDate.setTime(timestamp);
        return newDate.toJSON();
    },
    toLocaleDateString: function (timestamp) {
        var newDate = new Date();
        newDate.setTime(timestamp);
        return newDate.toLocaleDateString();
    },
    toLocaleString: function (timestamp) {
        var newDate = new Date();
        newDate.setTime(timestamp);
        return newDate.toLocaleString();
    },
    toLocaleTimeString: function (timestamp) {
        var newDate = new Date();
        newDate.setTime(timestamp);
        return newDate.toLocaleTimeString();
    },
    toString: function (timestamp) {
        var newDate = new Date();
        newDate.setTime(timestamp);
        return newDate.toString();
    },
    toTimeString: function (timestamp) {
        var newDate = new Date();
        newDate.setTime(timestamp);
        return newDate.toTimeString();
    },
    toUTCString: function (timestamp) {
        var newDate = new Date();
        newDate.setTime(timestamp);
        return newDate.toUTCString();
    },
    getMonthDays: function (curDate) {
        /* 获取当前月份 */
        var curMonth = curDate.getMonth();
        /*  生成实际的月份: 由于curMonth会比实际月份小1, 故需加1 */
        curDate.setMonth(curMonth + 1);

        curDate.setDate(0);
        /* 返回当月的天数 */
        return curDate.getDate();
    }
};