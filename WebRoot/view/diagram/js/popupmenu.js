/**
 * Created by barrie on 17/1/17.
 */
$(document).ready(function () {
    var BLACK_LIST_ROTATE = [
        graphConstants.USER_OBJECT_CURRENT,
        graphConstants.USER_OBJECT_TEXT,
        graphConstants.USER_OBJECT_SWITCH_STATE
    ];

    var WHITE_LIST_FONT = [
        graphConstants.USER_OBJECT_TEXT
    ];

    var WHITE_LIST_USEROBJ_SWITCH_STATE = [
        graphConstants.USER_OBJECT_SWITCH_STATE
    ];

    var WHITE_LIST_USEROBJ_CURRENT = [
        graphConstants.USER_OBJECT_CURRENT
    ];

    // Installs a popupmenu handler using local function (see below).
    graph.popupMenuHandler.factoryMethod = function (menu, cell, evt) {
        return createPopupMenu(graph, menu, cell, evt);
    };
    function createPopupMenu(graph, menu, cell, evt) {
        if (cell != null) {
            if (cell.edge == false && inList(cell.style, WHITE_LIST_USEROBJ_CURRENT)) {
                menu.addItem("配置", mxBasePath + "images/editors/properties.gif", function () {
                    var node = {
                        cellId: cell.id,
                        cellType: graphConstants.USER_OBJECT_CURRENT
                    }
                    $("#hid-selected-node-info").val(JSON.stringify(node));
                    $("#dlg-bound-pn").dialog("open");
//             var model = graph.getModel();
//
//             model.beginUpdate();
//             try {
//                 var newCell = model.getCell(cell.id);
//
//                 // Updates the cell color and adds some tooltip information
//                 if (newCell != null) {
//                     graph.cellsRemoved(cell.children);
//
//                     var num = prompt("请输入设备地址", "192.168.10.1:8888");
//                     newCell.setAttribute("xxxx", num);
//
//                     // Resets the fillcolor and the overlay
// //                            graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "green", [cell]);
//                     var v11 = graph.insertVertex(newCell, null, '<p style="color: red">1 (A)</p><p style="color: green">3 (A)</p><p style="color: orange">2 (A)</p>', 1, 1, 0, 0, 'align=left;verticalAlign=top;', true);
//
//
//                 } // for
//             }
//             finally {
//                 model.endUpdate();
//             }

                });
                menu.addSeparator();
            }
            if (cell.edge == false && inList(cell.style, WHITE_LIST_USEROBJ_SWITCH_STATE)) {
                menu.addItem("配置", mxBasePath + "images/editors/properties.gif", function () {
                    var node = {
                        cellId: cell.id,
                        cellType: graphConstants.USER_OBJECT_SWITCH_STATE
                    }
                    $("#hid-selected-node-info").val(JSON.stringify(node));
                    $("#dlg-bound-pn").dialog("open");
                });
                menu.addSeparator();
            }
            if (cell.edge == false && inList(cell.style, WHITE_LIST_FONT)) {
                menu.addItem("颜色", mxBasePath + "images/editors/fontcolor.gif", function () {
                    var style = graph.getCellStyle(cell);
                    _colorPickerCell = {
                        cell: cell,
                        style: mxConstants.STYLE_FONTCOLOR,
                        value: style[mxConstants.STYLE_FONTCOLOR]
                    };

                    $("#dlg-color-picker").dialog("open");

                    // Resets the fillcolor and the overlay
                    // graph.setCellStyles(mxConstants.STYLE_FONTCOLOR, r, [cell]);

                });
                menu.addItem("大小", mxBasePath + "images/editors/plain.gif", function () {
                    var style = graph.getCellStyle(cell);
                    _sizeCell = {
                        cell: cell,
                        style: mxConstants.STYLE_FONTSIZE,
                        value: style[mxConstants.STYLE_FONTSIZE]
                    };

                    $("#dlg-size-picker").dialog("open");

                    // Resets the fillcolor and the overlay
                    // graph.setCellStyles(mxConstants.STYLE_FONTSIZE, r, [cell]);

                });
                menu.addItem("加粗", mxBasePath + "images/editors/bold.gif", function () {
                    graph.toggleCellStyleFlags(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD, [cell]);
                });

                menu.addItem("下划线", mxBasePath + "images/editors/underline.gif", function () {
                    graph.toggleCellStyleFlags(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_UNDERLINE, [cell]);
                });
                menu.addItem("倾斜", mxBasePath + "images/editors/italic.gif", function () {
                    graph.toggleCellStyleFlags(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_ITALIC, [cell]);
                });
                menu.addSeparator();
            }

            if (cell.edge == false && !inList(cell.style, BLACK_LIST_ROTATE)) {
                menu.addItem("旋转", mxBasePath + "images/editors/redo.gif", function () {
                    if (cell != null) {
                        var geo = graph.getCellGeometry(cell);

                        if (geo != null) {
                            graph.getModel().beginUpdate();
                            try {
                                // Rotates the size and position in the geometry
                                geo = geo.clone();
                                geo.x += geo.width / 2 - geo.height / 2;
                                geo.y += geo.height / 2 - geo.width / 2;
                                var tmp = geo.width;
                                geo.width = geo.height;
                                geo.height = tmp;
                                graph.getModel().setGeometry(cell, geo);

                                // Reads the current direction and advances by 90 degrees
                                var state = graph.view.getState(cell);

                                if (state != null) {
                                    var dir = state.style[mxConstants.STYLE_DIRECTION] || "east"/*default*/;

                                    if (dir == "east") {
                                        dir = "south";
                                    }
                                    else if (dir == "south") {
                                        dir = "west";
                                    }
                                    else if (dir == "west") {
                                        dir = "north";
                                    }
                                    else if (dir == "north") {
                                        dir = "east";
                                    }

                                    graph.setCellStyles(mxConstants.STYLE_DIRECTION, dir, [cell]);
                                }
                            }
                            finally {
                                graph.getModel().endUpdate();
                            }
                        }
                    }
                });
                menu.addItem("水平翻转", mxBasePath + "images/editors/redo.gif", function () {
                    graph.toggleCellStyles(mxConstants.STYLE_FLIPH);
                });
                menu.addItem("垂直翻转", mxBasePath + "images/editors/redo.gif", function () {
                    graph.toggleCellStyles(mxConstants.STYLE_FLIPV);
                });
                menu.addSeparator();
            }
            if (cell.edge == true) {
                menu.addItem("颜色", mxBasePath + "images/editors/fontcolor.gif", function () {
                    var style = graph.getCellStyle(cell);
                    _colorPickerCell = {
                        cell: cell,
                        style: mxConstants.STYLE_STROKECOLOR,
                        value: style[mxConstants.STYLE_STROKECOLOR]
                    };

                    $("#dlg-color-picker").dialog("open");
                    // Resets the fillcolor and the overlay
                    // graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, r, [cell]);

                });
                menu.addItem("大小", mxBasePath + "images/editors/plain.gif", function () {
                    var style = graph.getCellStyle(cell);
                    _sizeCell = {
                        cell: cell,
                        style: mxConstants.STYLE_STROKEWIDTH,
                        value: style[mxConstants.STYLE_STROKEWIDTH]
                    };

                    $("#dlg-size-picker").dialog("open");

                    // Resets the fillcolor and the overlay
                    // graph.setCellStyles(mxConstants.STYLE_STROKEWIDTH, r, [cell]);

                });
            }
            menu.addItem("删除", mxBasePath + "images/editors/delete.gif", function () {
                var model = graph.getModel();

                if (cell.edge == false && inList(cell.style, WHITE_LIST_USEROBJ_CURRENT)) {
                    var node = {
                        cellId: cell.id,
                        cellType: graphConstants.USER_OBJECT_CURRENT
                    };
                    graphConfig.deleteConfig(node, $("#hid-config"));
                }
                else if (cell.edge == false && inList(cell.style, WHITE_LIST_USEROBJ_SWITCH_STATE)) {
                    var node = {
                        cellId: cell.id,
                        cellType: graphConstants.USER_OBJECT_SWITCH_STATE
                    };
                    graphConfig.deleteConfig(node, $("#hid-config"));
                }

                model.beginUpdate();
                try {
                    var newCell = model.getCell(cell.id);

                    // Updates the cell color and adds some tooltip information
                    if (newCell != null) {
                        graph.removeCells([newCell]);
                    } // for
                }
                finally {
                    model.endUpdate();
                }
            });
//            else {
//                menu.addItem("No-Cell Item", mxBasePath + "images/editors/image.gif", function () {
//                    mxUtils.alert("MenuItem2");
//                });
//            }
//            menu.addSeparator();
//            menu.addItem("MenuItem3", mxBasePath + "images/warning.gif", function () {
//                mxUtils.alert("MenuItem3: " + graph.getSelectionCount() + " selected");
//            });
        }

    }

    function inList(style, list) {
        for (var i = 0; i < list.length; i++) {
            if (isValidStyle(style, list[i])) {
                return true;
            }
        }
        return false;
    }

    function isValidStyle(style, name) {
        var styles = (style + "").split(";");
        for (var i = 0; i < styles.length; i++) {
            if (styles[i] == name) {
                return true;
            }
        }
        return false;
    }
});

// Function to create the entries in the popupmenu
