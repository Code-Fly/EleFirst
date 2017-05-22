/**
 * Created by Administrator on 2017/1/19.
 */
$(document).ready(function () {
    // Creates the div for the toolbar
    var tbContainer = document.getElementById("toolbarContainer");
    if (mxClient.IS_QUIRKS) {
        new mxDivResizer(tbContainer);
    }
    // Creates new toolbar without event processing
    var toolbar = new mxToolbar(tbContainer);

    // toolbar.enabled = false
    var wnd = new mxWindow("工具栏", tbContainer, 10, 50, 44);
    wnd.setMaximizable(false);
    wnd.setMinimizable(false);
    wnd.setScrollable(false);
    wnd.setResizable(false);
    wnd.setVisible(true);

    // toolbar.addItem("保存", mxBasePath + "images/editors/save.gif", function (evt) {
    //     var encoder = new mxCodec();
    //     var node = encoder.encode(graph.getModel());
    //     mxUtils.popup(mxUtils.getPrettyXml(node), true);
    // });
    toolbar.addItem("打印", mxBasePath + "images/editors/print.gif", function (evt) {
        mxUtils.printScreen(graph);
    });
    // toolbar.addItem("载入", mxBasePath + "images/editors/open.gif", function (evt) {
    //     // Load cells and layouts the graph
    //     graph.getModel().beginUpdate();
    //     try {
    //         // Loads the custom file format (TXT file)
    //         // parse(graph, "fileio.txt");
    //
    //         // Loads the mxGraph file format (XML file)
    //         read(graph, "data/fileio.xml");
    //
    //     }
    //     finally {
    //         // Updates the display
    //         graph.getModel().endUpdate();
    //     }
    // });
    toolbar.addLine();

    var wireMode = false;
    // Starts connections on the background in wire-mode
    var connectionHandlerIsStartEvent = graph.connectionHandler.isStartEvent;
    graph.connectionHandler.isStartEvent = function (me) {
        return wireMode || connectionHandlerIsStartEvent.apply(this, arguments);
    };

    // Avoids any connections for gestures within tolerance except when in wire-mode
    // or when over a port
    var connectionHandlerMouseUp = graph.connectionHandler.mouseUp;
    graph.connectionHandler.mouseUp = function (sender, me) {
        if (this.first != null && this.previous != null) {
            var point = mxUtils.convertPoint(this.graph.container, me.getX(), me.getY());
            var dx = Math.abs(point.x - this.first.x);
            var dy = Math.abs(point.y - this.first.y);

            if (dx < this.graph.tolerance && dy < this.graph.tolerance) {
                // Selects edges in non-wire mode for single clicks, but starts
                // connecting for non-edges regardless of wire-mode
                if (!wireMode && this.graph.getModel().isEdge(this.previous.cell)) {
                    this.reset();
                }

                return;
            }
        }

        connectionHandlerMouseUp.apply(this, arguments);
    };

    if (_editable) {
        toolbar.addSwitchMode("选择", mxBasePath + "images/editors/select.gif", function (evt, cell) {
            wireMode = false;
        });
        toolbar.addSwitchMode("画线", mxBasePath + "images/editors/vertical.gif", function (evt, cell) {
            wireMode = true;
        });
        toolbar.addLine();
        // Undo/redo
        var undoManager = new mxUndoManager();
        var listener = function (sender, evt) {
            undoManager.undoableEditHappened(evt.getProperty("edit"));
        };
        graph.getModel().addListener(mxEvent.UNDO, listener);
        graph.getView().addListener(mxEvent.UNDO, listener);

        toolbar.addItem("撤销", mxBasePath + "images/editors/undo.gif", function (evt) {
            undoManager.undo();
        });

        toolbar.addItem("恢复", mxBasePath + "images/editors/redo.gif", function (evt) {
            undoManager.undo();
        });
        toolbar.addLine();
        toolbar.addItem("剪切", mxBasePath + "images/editors/cut.gif", function (evt) {
            mxClipboard.cut(graph);
        });
        toolbar.addItem("复制", mxBasePath + "images/editors/copy.gif", function (evt) {
            mxClipboard.copy(graph);
        });
        toolbar.addItem("粘贴", mxBasePath + "images/editors/paste.gif", function (evt) {
            mxClipboard.paste(graph);
        });
        toolbar.addItem("删除", mxBasePath + "images/editors/delete.gif", function (evt) {
            graph.removeCells();
        });
        toolbar.addLine();
        // addVertex("", mxBasePath + "images/editors/rectangle.gif", 100, 40, "");
        // addVertex("", mxBasePath + "images/editors/ellipse.gif", 40, 40, "shape=ellipse");
        // addVertex("", mxBasePath + "images/editors/rhombus.gif", 40, 40, "shape=rhombus");
        // addVertex("", mxBasePath + "images/editors/triangle.gif", 40, 40, "shape=triangle");
        // addVertex("", mxBasePath + "images/editors/cylinder.gif", 40, 40, "shape=cylinder");
        // addVertex("", mxBasePath + "images/editors/actor.gif", 30, 40, "shape=actor");
        addVertex("", mxBasePath + "images/editors/text.gif", 150, 30, "text");
        toolbar.addLine();
        var customToolbarInfo = getCustomToolbarInfo();
        for (var i = 0; i < customToolbarInfo.length; i++) {
            var name = customToolbarInfo[i].name;
            var width = customToolbarInfo[i].width;
            var height = customToolbarInfo[i].height;
            var iconPath = BASE_PATH + customToolbarInfo[i].iconPath;
            var imgPath = BASE_PATH + customToolbarInfo[i].imgPath;

            addVertex(name, iconPath, width, height, graphConstants.USER_OBJECT_CUSTOM_IMG + ";image=" + imgPath);
        }
        toolbar.addLine();
        addVertex("三相电流", mxBasePath + "images/editors/italic.gif", 50, 80, graphConstants.USER_OBJECT_CURRENT + ";phase=3");
        addVertex("开关状态", BASE_PATH + "Content/images/graph/toolbar/custom/small-rectangle.gif", 30, 20, graphConstants.USER_OBJECT_SWITCH_STATE);
        toolbar.addLine();
    }
    toolbar.addItem("放大", mxBasePath + "images/editors/zoomin.gif", function (evt) {
        graph.zoomIn();
    });

    toolbar.addItem("缩小", mxBasePath + "images/editors/zoomout.gif", function (evt) {
        graph.zoomOut();
    });

    toolbar.addItem("实际大小", mxBasePath + "images/editors/zoomactual.gif", function (evt) {
        graph.zoomActual();
    });

    function addVertex(title, icon, w, h, style) {
        var vertex = new mxCell(null, new mxGeometry(0, 0, w, h), style);
        vertex.setVertex(true);

        var img = addToolbarItem(title, icon, graph, toolbar, vertex);
        img.enabled = true;

        graph.getSelectionModel().addListener(mxEvent.CHANGE, function () {
            var tmp = graph.isSelectionEmpty();
            mxUtils.setOpacity(img, (tmp) ? 100 : 20);
            img.enabled = tmp;
        });
    }

    function addToolbarItem(title, icon, graph, toolbar, prototype) {
        // Function that is executed when the icon is dropped on
        // the graph. The cell argument points to the cell under
        // the mousepointer if there is one.
        var funct = function (graph, evt, cell, x, y) {
            graph.stopEditing(false);


            var vertex = graph.getModel().cloneCell(prototype);

            if (graphUtils.isValidStyle(vertex.style, graphConstants.USER_OBJECT_CURRENT)) {
                if (graphUtils.isValidStyle(vertex.style, "phase=2")) {
                    vertex.setValue(getTwoPhaseCurrentLabel(0, 0));
                }
                if (graphUtils.isValidStyle(vertex.style, "phase=3")) {
                    vertex.setValue(getThreePhaseCurrentLabel(0, 0, 0));
                }
            }

            vertex.geometry.x = x;
            vertex.geometry.y = y;
            graph.addCell(vertex);
            graph.setSelectionCell(vertex);
        };

        // Creates the icon which is used as the drag icon (preview)
        // var img = toolbar.addMode(null, icon, function (evt, cell) {
        var img = toolbar.addItem(title, icon, function (evt, cell) {
            var pt = this.graph.getPointForEvent(evt);
            funct(graph, evt, cell, pt.x, pt.y);
        });

        // Disables dragging if element is disabled. This is a workaround
        // for wrong event order in IE. Following is a dummy listener that
        // is invoked as the last listener in IE.
        mxEvent.addListener(img, "mousedown", function (evt) {
            // do nothing
        });

        // This listener is always called first before any other listener
        // in all browsers.
        mxEvent.addListener(img, "mousedown", function (evt) {
            if (img.enabled == false) {
                mxEvent.consume(evt);
            }
        });

        mxUtils.makeDraggable(img, graph, funct);

        return img;
    }

    /**
     * Creates an overlay object using the given tooltip and text for the alert window
     * which is being displayed on click.
     */
    function createOverlay(image, tooltip) {
        var overlay = new mxCellOverlay(image, tooltip);

        // Installs a handler for clicks on the overlay
        overlay.addListener(mxEvent.CLICK, function (sender, evt) {
            mxUtils.alert(tooltip + "\nLast update: " + new Date());
        });

        return overlay;
    }

    /**
     * Returns a random state.
     */
    function getState() {
        var state = "Init";
        var rnd = Math.random() * 4;

        if (rnd > 3) {
            state = "Completed";
        }
        else if (rnd > 2) {
            state = "Running";
        }
        else if (rnd > 1) {
            state = "Waiting";
        }

        return state;
    }

    /**
     * Updates the display of the given graph using the XML data
     */
    function update(graph, nodes) {
        var model = graph.getModel();

        var currentNodes = nodes[graphConstants.USER_OBJECT_CURRENT];

        for (var i = 0; i < currentNodes.length; i++) {
            // Processes the activity nodes inside the process node
            var id = currentNodes[i].cellId;
            // Gets the cell for the given activity name from the model
            var cell = model.getCell(id);
            // Updates the cell color and adds some tooltip information
            if (cell != null) {
                model.beginUpdate();
                try {
                    if (graphUtils.isValidStyle(cell.style, "phase=2")) {
                        var aCurrent = currentNodes[i].aCurrent;
                        var bCurrent = currentNodes[i].bCurrent;

                        var content = getTwoPhaseCurrentLabel(aCurrent, bCurrent);
                        var edit = new mxValueChange(model, cell, content);
                        model.execute(edit);
                    }
                    else if (graphUtils.isValidStyle(cell.style, "phase=3")) {
                        var aCurrent = currentNodes[i].aCurrent;
                        var bCurrent = currentNodes[i].bCurrent;
                        var cCurrent = currentNodes[i].cCurrent;

                        var content = getThreePhaseCurrentLabel(aCurrent, bCurrent, cCurrent);
                        var edit = new mxValueChange(model, cell, content);
                        model.execute(edit);
                    }
                }
                finally {
                    model.endUpdate();
                }
            }
        } // for

        var switchStateNodes = nodes[graphConstants.USER_OBJECT_SWITCH_STATE];

        for (var i = 0; i < switchStateNodes.length; i++) {
            // Processes the activity nodes inside the process node
            var id = switchStateNodes[i].cellId;
            // Gets the cell for the given activity name from the model
            var cell = model.getCell(id);
            // Updates the cell color and adds some tooltip information
            if (cell != null) {
                model.beginUpdate();
                try {
                    if (switchStateNodes[i].attributes.state == graphConstants.SWITCH_STATE_ON) {
                        graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "lime", [cell]);
                    }
                    else if (switchStateNodes[i].attributes.state == graphConstants.SWITCH_STATE_OFF) {
                        graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "red", [cell]);
                    }
                }
                finally {
                    model.endUpdate();
                }
            }
        } // for
    }


    function getCustomToolbarInfo() {
        var customToolbarInfo = $.parseJSON($.ajax({
            url: _ctx + "system/graph/toolbar/list.do",
            type: "POST",
            async: false
        }).responseText);

        if ("0" == customToolbarInfo.errcode) {
            return customToolbarInfo.data;
        } else {
            $.messager.alert("操作提示", "请求失败！" + ErrUtils.getMsg(customToolbarInfo.errcode), "info");
            return [];
        }
    }

    function getTwoPhaseCurrentLabel(a, b) {
        return '<p style="color: orange">' + '<span class="a">' + a + '</span>' + ' (A)</p>' + '<p style="color: green">' + '<span class="b">' + b + '</span>' + ' (A)</p>';
    }

    function getThreePhaseCurrentLabel(a, b, c) {
        return '<p style="color: orange">' + '<span class="a">' + a + '</span>' + ' (A)</p>' + '<p style="color: green">' + '<span class="b">' + b + '</span>' + ' (A)</p>' + '<p style="color: red">' + '<span class="c">' + c + '</span>' + ' (A)</p>';
    }
});