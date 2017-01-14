/**
 * Created by 123 on 2016/6/27.
 */
var DSM_ERROR_MAP
var DsmErrUtils
$(document).ready(function () {
    DSM_ERROR_MAP = {
        "-1": null,
        "0": $.i18n.prop("error.success"),
        "1": $.i18n.prop("error.exception.session.invalid"),
        // "2": $.i18n.prop("error.exception.data.parse.failed"),
        // "3": null,
        "4": null,
        "5": $.i18n.prop("error.exception.sql"),
        "6": $.i18n.prop("error.exception.connection.failed"),
        "7": $.i18n.prop("error.exception.upload.failed"),
        "8": null,
        "9": $.i18n.prop("error.exception.charset.failed"),
        "10": null,
        "11": null,
        "12": $.i18n.prop("error.exception.file.charset.invalid"),
        "13": $.i18n.prop("error.exception.file.parse.failed"),
        "14": $.i18n.prop("error.exception.migration.invalid"),
        "15": null,
        "16": $.i18n.prop("error.exception.cron.invalid"),
        "17": $.i18n.prop("error.exception.scheduler.failed"),
        "20": $.i18n.prop("error.exception.data.key.duplicate"),
        "21": $.i18n.prop("error.exception.data.invalid"),
        "25": $.i18n.prop("error.exception.connection.timeout"),
        //
        "111": $.i18n.prop("error.exception.directory.notExist"),
        "202": $.i18n.prop("error.exception.file.notExist")

    }

    DsmErrUtils = (function () {
        return {
            getMsg: function (value, param) {
                if (!DSM_ERROR_MAP.hasOwnProperty(value)) {
                    return "";
                }
                if (null == DSM_ERROR_MAP[value]) {
                    return "(" + value + ")";
                }
                if ("14" == value) {
                    return "(" + DSM_ERROR_MAP[value].replace("{src}", param.srcType).replace("{dst}", param.dstType) + ")";
                } else {
                    return "(" + DSM_ERROR_MAP[value] + ")";
                }
            }
        }

    }());
});
