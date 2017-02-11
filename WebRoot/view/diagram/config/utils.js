/**
 * Created by barrie on 17/2/2.
 */
var graphUtils = {
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
        var newConfig = this.initConfig($.parseJSON($(config).val()));

        var list = newConfig[node.cellType];
        for (var i = 0; i < list.length; i++) {
            if (list[i].cellId == node.cellId) {
                return list[i];
            }
        }

        return null;
    },
    isValidStyle: function (style, name) {
        var styles = (style + "").split(";");
        for (var i = 0; i < styles.length; i++) {
            if (styles[i] == name) {
                return true;
            }
        }
        return false;
    },
    // Custom parser for simple file format
    // Loads the custom file format (TXT file)
    parseSimpleFile: function (graph, filename) {
        var model = graph.getModel();

        // Gets the default parent for inserting new cells. This
        // is normally the first child of the root (ie. layer 0).
        var parent = graph.getDefaultParent();

        var req = mxUtils.load(filename);
        var text = req.getText();

        var lines = text.split("\n");

        // Creates the lookup table for the vertices
        var vertices = [];

        // Parses all lines (vertices must be first in the file)
        graph.getModel().beginUpdate();
        try {
            for (var i = 0; i < lines.length; i++) {
                // Ignores comments (starting with #)
                var colon = lines[i].indexOf(":");

                if (lines[i].substring(0, 1) != "#" ||
                    colon == -1) {
                    var comma = lines[i].indexOf(",");
                    var value = lines[i].substring(colon + 2, lines[i].length);

                    if (comma == -1 || comma > colon) {
                        var key = lines[i].substring(0, colon);

                        if (key.length > 0) {
                            vertices[key] = graph.insertVertex(parent, null, value, 0, 0, 80, 70);
                        }
                    }
                    else if (comma < colon) {
                        // Looks up the vertices in the lookup table
                        var source = vertices[lines[i].substring(0, comma)];
                        var target = vertices[lines[i].substring(comma + 1, colon)];

                        if (source != null && target != null) {
                            var e = graph.insertEdge(parent, null, value, source, target);

                            // Uses the special 2-way style for 2-way labels
                            if (value.indexOf("2-Way") >= 0) {
                                e.style = "2way";
                            }
                        }
                    }
                }
            }
        }
        finally {
            graph.getModel().endUpdate();
        }
    },
    // Parses the mxGraph XML file format
    // Loads the mxGraph file format (XML file)
    parseXMLFile: function (graph, filename) {
        var req = mxUtils.load(filename);
        var root = req.getDocumentElement();
        var dec = new mxCodec(root.ownerDocument);

        dec.decode(root, graph.getModel());
    },
    inList: function (style, list) {
        for (var i = 0; i < list.length; i++) {
            if (this.isValidStyle(style, list[i])) {
                return true;
            }
        }
        return false;
    }
};