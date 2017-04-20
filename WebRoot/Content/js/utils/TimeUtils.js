/**
 * Created by Administrator on 2016/8/31.
 */
/**
 * 时间工具类
 * @type {{weekFromODBC: TimeUtils.weekFromODBC, toDate: TimeUtils.toDate, dbTimeToUTC: TimeUtils.dbTimeToUTC, dbTimeToDate: TimeUtils.dbTimeToDate, dateToDbTime: TimeUtils.dateToDbTime, dataBoxDateToDate: TimeUtils.dataBoxDateToDate, dataBoxMonthToDate: TimeUtils.dataBoxMonthToDate, toDateString: TimeUtils.toDateString, toGMTString: TimeUtils.toGMTString, toISOString: TimeUtils.toISOString, toJSON: TimeUtils.toJSON, toLocaleDateString: TimeUtils.toLocaleDateString, toLocaleString: TimeUtils.toLocaleString, toLocaleTimeString: TimeUtils.toLocaleTimeString, toString: TimeUtils.toString, toTimeString: TimeUtils.toTimeString, toUTCString: TimeUtils.toUTCString, getMonthDays: TimeUtils.getMonthDays}}
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
    toDate: function (timestamp) {
        var newDate = new Date();
        newDate.setTime(timestamp);
        return newDate;
    },
    dbTimeToUTC: function (time) {
        var year = parseInt(time.substr(0, 4));
        var month = parseInt(time.substr(4, 2)) - 1;
        var day = parseInt(time.substr(6, 2))
        var hour = parseInt(time.substr(8, 2));
        var minute = parseInt(time.substr(10, 2));
        var second = parseInt(time.substr(12, 2));

        var d = Date.UTC(year, month, day, hour, minute, second);
        return d;
    },
    dbTimeToDate: function (time) {
        var year = parseInt(time.substr(0, 4));
        var month = parseInt(time.substr(4, 2)) - 1;
        var day = parseInt(time.substr(6, 2))
        var hour = parseInt(time.substr(8, 2));
        var minute = parseInt(time.substr(10, 2));
        var second = parseInt(time.substr(12, 2));

        var d = new Date(year, month, day, hour, minute, second);
        return d;
    },
    dateToDbTime: function (time) {
        return time.format("yyyyMMddhhmmss");
    },
    dataBoxDateToDate: function (time) {
        var ss = time.split('-');
        var year = parseInt(ss[0], 10);
        var month = parseInt(ss[1], 10) - 1;
        var day = parseInt(ss[2], 10);

        var d = new Date(year, month, day);
        return d;
    },
    dataBoxMonthToDate: function (time) {
        var ss = time.split('-');
        var year = parseInt(ss[0], 10);
        var month = parseInt(ss[1], 10) - 1;

        var d = new Date(year, month);
        return d;
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
    },
    getCurrentMonthFirst: function () {
        var date = new Date();
        date.setDate(1);
        return date;
    },

    getLastMonthFirst: function () {
        var date = new Date();
        date.setMonth(date.getMonth() - 1);
        date.setDate(1);
        return date;
    },
    getNextMonthFirst: function () {
        var date = new Date();
        date.setMonth(date.getMonth() + 1);
        date.setDate(1);
        return date;
    },
    getCurrentMonthLast: function () {
        var date = new Date();
        date.setDate(1);
        date.setMonth(date.getMonth() + 1);
        date.setDate(date.getDate() - 1);
        return date;
    },

    getLastMonthLast: function () {
        var date = new Date();
        date.setDate(1);
        date.setDate(date.getDate() - 1);
        return date;
    },
    getNextMonthLast: function () {
        var date = new Date();
        date.setMonth(date.getMonth() + 2);
        date.setDate(1);
        date.setDate(date.getDate() - 1);
        return date;
    },
    getDataBoxDateToDateInterval: function (time, yearInterval, monthInterval, dayInterval) {
        var newDate = this.dataBoxDateToDate(time);
        newDate.setDate(newDate.getDate() + dayInterval);
        newDate.setMonth(newDate.getMonth() + monthInterval);
        newDate.setFullYear(newDate.getFullYear() + yearInterval);

        return newDate;
    }
};