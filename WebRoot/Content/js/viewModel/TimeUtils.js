/**
 * Created by Administrator on 2016/8/31.
 */
/**
 * 时间工具类
 * @type {{toDateString, toGMTString, toISOString, toJSON, toLocaleDateString, toLocaleString, toLocaleTimeString, toString, toTimeString, toUTCString}}
 */
var TimeUtils = (function () {
    return {
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
    }
}());