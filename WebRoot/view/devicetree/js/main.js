/**
 * Created by VM on 1/26/2017.
 */
$(document).ready(function () {
    var info = {
        areaId: "1",
        concentrators: [],
        type: "physical"
    }
    $("#dTree").tree({
        url: "data/physicalTree.json",
        method: "get",
        animate: true,
        onSelect: function (node) {
            traverse(node);
            parent.saveTreeInfo(info);
        },
        onBeforeSelect: function (node) {
            info.concentrators = [];
        }
    });


    function traverse(tree) {
        var nodes = tree.children;
        if ("concentrator" == tree.attributes.type) {
            info.concentrators.push({
                concentratorId: tree.attributes.concentratorId,
                pns: []
            });
        }
        if (nodes != null) {
            for (var i = 0; i < nodes.length; i++) {
                if ("concentrator" == nodes[i].attributes.type) {
                    info.concentrators.push({
                        concentratorId: nodes[i].attributes.concentratorId,
                        pns: []
                    });
                }
                if (nodes[i].children) { //递归调用自己，以实现遍历
                    traverse(nodes[i]);
                }
            }
        }
    }
});