/**
 * Created by VM on 1/26/2017.
 */

var treeType = GetQueryString("type");

function getSelectedNodeInfo() {
    var node = $("#dTree").tree("getSelected");
    return node;
}

function findNode(id) {
    var node = $("#dTree").tree("find", id);
    initInfo();
    traverse(node);
    return info;
}

function traverse(tree) {
    var nodes = tree.children;

    if ("concentrator" == tree.attributes.type) {
        if (!isExist(info.concentrators, tree.attributes.concentratorId)) {
            info.concentrators.push({
                concentratorId: tree.attributes.concentratorId,
                pns: []
            });
        }
    }
    else if ("leaf" == tree.attributes.type) {
        $.merge(info.concentrators, tree.attributes.concentrators);
    }
    if (nodes != null) {
        for (var i = 0; i < nodes.length; i++) {
            if ("concentrator" == nodes[i].attributes.type) {
                if (!isExist(info.concentrators, nodes[i].attributes.concentratorId)) {
                    info.concentrators.push({
                        concentratorId: nodes[i].attributes.concentratorId,
                        pns: []
                    });
                }
            }
            else if ("leaf" == nodes[i].attributes.type) {
                $.merge(info.concentrators, nodes[i].attributes.concentrators);
            }
            if (nodes[i].children) { //递归调用自己，以实现遍历
                traverse(nodes[i]);
            }
        }
    }
}

function isExist(data, id) {
    for (var i = 0; i < data.length; i++) {
        if (data[i].concentratorId == id) {
            return true;
        }
    }
    return false;
}

function initInfo() {
    info = {
        areaId: _areaId,
        concentrators: [],
        type: "physical"
    };
}

var info = {
    areaId: _areaId,
    concentrators: [],
    type: "physical"
};

$(document).ready(function () {


    $("#dTree").tree({
        // url: "data/test.json",
        // method: "get",
        url: _ctx + "system/tree/info/node.do",
        queryParams: {
            treeId: _treeId
        },
        fit: true,
        animate: true,
        lines: true,
        onSelect: function (node) {
            initInfo();
            if ("master" == treeType) {
                if ($.isArray(node.attributes.master) && node.attributes.master.length > 0) {
                    var masters = node.attributes.master;
                    for (var i = 0; i < masters.length; i++) {
                        var pns = [];
                        pns.push(masters[i].pn);
                        info.concentrators.push({
                            concentratorId: masters[i].concentratorId,
                            pns: pns
                        });
                    }
                } else {
                    info = null;
                }
            } else {
                traverse(node);
            }

            console.log(info)
            parent.saveTreeInfo(info);
        },
        onBeforeSelect: function (node) {
            info.concentrators = [];
        },
        onLoadSuccess: function (node, data) {
            var root = $(this).tree("getRoot");
            $(this).tree("select", root.target);
        }
    });

    var root = $("#dTree").tree("getRoot");
    if (root != undefined && root != null) {
        $("#dTree").tree("select", root.target);
    }


});