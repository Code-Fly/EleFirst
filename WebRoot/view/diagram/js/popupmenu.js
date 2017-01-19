/**
 * Created by barrie on 17/1/17.
 */
$(document).ready(function () {
    // Installs a popupmenu handler using local function (see below).
    graph.popupMenuHandler.factoryMethod = function (menu, cell, evt) {
        return createPopupMenu(graph, menu, cell, evt);
    };
    function createPopupMenu(graph, menu, cell, evt) {
        if (cell != null) {
            if (cell.edge == false && isValidStyle(cell.style, "current")) {
                menu.addItem('配置', mxBasePath + 'images/editors/properties.gif', function () {
                    var num = prompt("请输入设备地址", "192.168.10.1:8888");
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
// //                            graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, 'green', [cell]);
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
            if (cell.edge == false && isValidStyle(cell.style, "text")) {
                menu.addItem('颜色', mxBasePath + 'images/editors/fontcolor.gif', function () {
                    var r = prompt("请输入颜色", "red");
                    // Resets the fillcolor and the overlay
                    graph.setCellStyles(mxConstants.STYLE_FONTCOLOR, r, [cell]);

                });
                menu.addItem('大小', mxBasePath + 'images/editors/plain.gif', function () {
                    var r = prompt("请输入大小", "12");
                    // Resets the fillcolor and the overlay
                    graph.setCellStyles(mxConstants.STYLE_FONTSIZE, r, [cell]);

                });
                menu.addItem('加粗', mxBasePath + 'images/editors/bold.gif', function () {
                    graph.toggleCellStyleFlags(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD, [cell]);
                });

                menu.addItem('下划线', mxBasePath + 'images/editors/underline.gif', function () {
                    graph.toggleCellStyleFlags(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_UNDERLINE, [cell]);
                });
                menu.addItem('倾斜', mxBasePath + 'images/editors/italic.gif', function () {
                    graph.toggleCellStyleFlags(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_ITALIC, [cell]);
                });
                menu.addSeparator();
            }
            if (cell.edge == false && !isValidStyle(cell.style, "current") && !isValidStyle(cell.style, "text")) {
                menu.addItem('旋转', mxBasePath + 'images/editors/redo.gif', function () {
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
                                    var dir = state.style[mxConstants.STYLE_DIRECTION] || 'east'/*default*/;

                                    if (dir == 'east') {
                                        dir = 'south';
                                    }
                                    else if (dir == 'south') {
                                        dir = 'west';
                                    }
                                    else if (dir == 'west') {
                                        dir = 'north';
                                    }
                                    else if (dir == 'north') {
                                        dir = 'east';
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
                menu.addItem('水平翻转', mxBasePath + 'images/editors/redo.gif', function () {
                    graph.toggleCellStyles(mxConstants.STYLE_FLIPH);
                });
                menu.addItem('垂直翻转', mxBasePath + 'images/editors/redo.gif', function () {
                    graph.toggleCellStyles(mxConstants.STYLE_FLIPV);
                });
                menu.addSeparator();
            }

            menu.addItem('删除', mxBasePath + 'images/editors/delete.gif', function () {
                var model = graph.getModel();

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
//                menu.addItem('No-Cell Item', mxBasePath + 'images/editors/image.gif', function () {
//                    mxUtils.alert('MenuItem2');
//                });
//            }
//            menu.addSeparator();
//            menu.addItem('MenuItem3', mxBasePath + 'images/warning.gif', function () {
//                mxUtils.alert('MenuItem3: ' + graph.getSelectionCount() + ' selected');
//            });
        }

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
