/**
 * Created by 123 on 2016/6/27.
 */
var ERROR_MAP = {
    "-1": null,
    "0": "正常结束",
    "1": "Session异常",
    // "2": "数据解析异常",
    // "3": null,
    "4": null,
    "5": "SQL执行失败",
    "6": "连接异常",
    "7": "上传异常",
    "8": null,
    "9": "编码异常",
    "10": null,
    "11": null,
    "12": "文件编码异常",
    "13": "文件解析异常",
    "15": null,
    "16": "定时表达式解析失败",
    "17": "任务执行失败",

}
var ErrUtils = {
    getMsg: function (value, param) {
        if (!ERROR_MAP.hasOwnProperty(value)) {
            return "";
        }
        if (null == ERROR_MAP[value]) {
            return "(" + value + ")";
        }
    }
}
