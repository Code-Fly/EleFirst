/**
 * Created by barrie on 17/2/2.
 */
var graphConfig = {
    initConfig: function (config) {
        var newConfig = clone(config);
        if (!newConfig.hasOwnProperty(graphConstants.USER_OBJECT_CURRENT)) {
            newConfig[graphConstants.USER_OBJECT_CURRENT] = [];
        }

        if (!newConfig.hasOwnProperty(graphConstants.USER_OBJECT_SWITCH_STATE)) {
            newConfig[graphConstants.USER_OBJECT_SWITCH_STATE] = [];
        }

        return newConfig;
    },
    addConfig: function (node, config) {
        var newConfig = this.initConfig($.parseJSON($(config).val()));

        var newList = [];
        var oldList = newConfig[node.cellType];
        for (var i = 0; i < oldList.length; i++) {
            if (oldList[i].cellId != node.cellId) {
                newList.push(oldList[i]);
            }
        }
        newList.push(node);
        newConfig[node.cellType] = newList;

        $(config).val(JSON.stringify(newConfig));

        return newConfig;
    },
    deleteConfig: function (node, config) {
        var newConfig = this.initConfig($.parseJSON($(config).val()));

        var newList = [];
        var oldList = newConfig[node.cellType];
        for (var i = 0; i < oldList.length; i++) {
            if (oldList[i].cellId != node.cellId) {
                newList.push(oldList[i]);
            }
        }
        newConfig[node.cellType] = newList;

        $(config).val(JSON.stringify(newConfig));

        return newConfig;
    },
    getConfig: function (node, config) {
        var config = this.initConfig($.parseJSON($(config).val()));

        var list = config[node.cellType];
        for (var i = 0; i < list.length; i++) {
            if (list[i].cellId == node.cellId) {
                return list[i];
            }
        }

        return null;
    }
};