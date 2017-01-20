/**
 * Created by Administrator on 2017/1/19.
 */
$(document).ready(function () {
    // Creates the div for the toolbar
    var tbContainer = document.getElementById('toolbarContainer');
    if (mxClient.IS_QUIRKS) {
        new mxDivResizer(tbContainer);
    }
    // Creates new toolbar without event processing
    var toolbar = new mxToolbar(tbContainer);

    // toolbar.enabled = false
    wnd = new mxWindow('工具栏', tbContainer, 10, 10, 44, "auto", false);
    wnd.setMaximizable(false);
    wnd.setScrollable(false);
    wnd.setResizable(false);
    wnd.setVisible(true);

    toolbar.addItem('保存', mxBasePath + 'images/editors/save.gif', function (evt) {
        var encoder = new mxCodec();
        var node = encoder.encode(graph.getModel());
        mxUtils.popup(mxUtils.getPrettyXml(node), true);
    });
    toolbar.addItem('打印', mxBasePath + 'images/editors/print.gif', function (evt) {
        mxUtils.printScreen(graph);
    });
    toolbar.addItem('载入', mxBasePath + 'images/editors/open.gif', function (evt) {
        // Load cells and layouts the graph
        graph.getModel().beginUpdate();
        try {
            // Loads the custom file format (TXT file)
            // parse(graph, 'fileio.txt');

            // Loads the mxGraph file format (XML file)
            read(graph, 'data/fileio.xml');

        }
        finally {
            // Updates the display
            graph.getModel().endUpdate();
        }
    });
    toolbar.addLine()
    toolbar.addItem('刷新', mxBasePath + 'images/editors/refresh.gif', function (evt) {
        // XML is normally fetched from URL at server using mxUtils.get - this is a client-side
        // string with randomized states to demonstrate the idea of the workflow monitor
        var xml = '<process><update id="3" state="' + getState() + '"/><update id="4" state="' + getState() + '"/></process>';
        update(graph, xml);
    });
    toolbar.addLine()

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
    toolbar.addSwitchMode("选择", mxBasePath + 'images/editors/select.gif', function (evt, cell) {
        wireMode = false;
    });
    toolbar.addSwitchMode("画线", mxBasePath + 'images/editors/vertical.gif', function (evt, cell) {
        wireMode = true;
    });
    toolbar.addLine()
    // Undo/redo
    var undoManager = new mxUndoManager();
    var listener = function (sender, evt) {
        undoManager.undoableEditHappened(evt.getProperty('edit'));
    };
    graph.getModel().addListener(mxEvent.UNDO, listener);
    graph.getView().addListener(mxEvent.UNDO, listener);

    toolbar.addItem('撤销', mxBasePath + 'images/editors/undo.gif', function (evt) {
        undoManager.undo();
    });

    toolbar.addItem('恢复', mxBasePath + 'images/editors/redo.gif', function (evt) {
        undoManager.undo();
    });
    toolbar.addLine()
    toolbar.addItem('剪切', mxBasePath + 'images/editors/cut.gif', function (evt) {
        mxClipboard.cut(graph);
    });
    toolbar.addItem('复制', mxBasePath + 'images/editors/copy.gif', function (evt) {
        mxClipboard.copy(graph);
    });
    toolbar.addItem('粘贴', mxBasePath + 'images/editors/paste.gif', function (evt) {
        mxClipboard.paste(graph);
    });
    toolbar.addItem('删除', mxBasePath + 'images/editors/delete.gif', function (evt) {
        graph.removeCells();
    });
    toolbar.addLine()
    addVertex("", mxBasePath + 'images/editors/rectangle.gif', 100, 40, '');
    // addVertex(mxBasePath + 'images/editors/rounded.gif', 100, 40, 'shape=rounded');
    addVertex("", mxBasePath + 'images/editors/ellipse.gif', 40, 40, 'shape=ellipse');
    addVertex("", mxBasePath + 'images/editors/rhombus.gif', 40, 40, 'shape=rhombus');
    addVertex("", mxBasePath + 'images/editors/triangle.gif', 40, 40, 'shape=triangle');
    addVertex("", mxBasePath + 'images/editors/cylinder.gif', 40, 40, 'shape=cylinder');
    addVertex("", mxBasePath + 'images/editors/actor.gif', 30, 40, 'shape=actor');
    toolbar.addLine()
    addVertex("", mxBasePath + 'images/editors/triangle.gif', 100, 55, 'triangle');
    addVertex("", mxBasePath + 'images/editors/image.gif', 100, 40, 'earth');
    addVertex("", mxBasePath + 'images/editors/text.gif', 150, 30, 'text');
    toolbar.addLine()
    addVertex("两相电流", mxBasePath + 'images/editors/italic.gif', 80, 60, 'current;phase=2');
    addVertex("三相电流", mxBasePath + 'images/editors/italic.gif', 80, 60, 'current;phase=3');
    toolbar.addLine()
    toolbar.addItem('放大', mxBasePath + 'images/editors/zoomin.gif', function (evt) {
        graph.zoomIn();
    });

    toolbar.addItem('缩小', mxBasePath + 'images/editors/zoomout.gif', function (evt) {
        graph.zoomOut();
    });

    toolbar.addItem('实际大小', mxBasePath + 'images/editors/zoomactual.gif', function (evt) {
        graph.zoomActual();
    });
    toolbar.addLine()
    toolbar.addItem('控制台', mxBasePath + 'images/editors/console.gif', function (evt) {
        mxLog.setVisible(!mxLog.isVisible());
    });
    toolbar.addItem('轮廓', mxBasePath + 'images/editors/outline.gif', function (evt) {
        if (editor.outline == null) {
            editor.showOutline();
        }
        else {
            editor.outline.setVisible(!editor.outline.isVisible());
        }
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
    };

    function addToolbarItem(title, icon, graph, toolbar, prototype) {
        // Function that is executed when the icon is dropped on
        // the graph. The cell argument points to the cell under
        // the mousepointer if there is one.
        var funct = function (graph, evt, cell, x, y) {
            graph.stopEditing(false);


            var vertex = graph.getModel().cloneCell(prototype);
            // console.log(vertex.value.nodeName.toLowerCase() == 'person')

            if (isValidStyle(vertex.style, "current")) {
                var parent = graph.getDefaultParent();
                graph.getModel().beginUpdate();
                try {
                    if (isValidStyle(vertex.style, "phase=2")) {
                        var v1 = graph.insertVertex(parent, null, twoPhaseCurrent, x, y, vertex.geometry.width, vertex.geometry.height, vertex.style);
                    }
                    if (isValidStyle(vertex.style, "phase=3")) {
                        var v1 = graph.insertVertex(parent, null, threePhaseCurrent, x, y, vertex.geometry.width, vertex.geometry.height, vertex.style);
                    }
                }
                finally {
                    graph.getModel().endUpdate();
                }
            } else {
                vertex.geometry.x = x;
                vertex.geometry.y = y;
                graph.addCell(vertex);
                graph.setSelectionCell(vertex);
            }


        }

        // Creates the icon which is used as the drag icon (preview)
        // var img = toolbar.addMode(null, icon, function (evt, cell) {
        var img = toolbar.addItem(title, icon, function (evt, cell) {
            var pt = this.graph.getPointForEvent(evt);
            funct(graph, evt, cell, pt.x, pt.y);
        });

        // Disables dragging if element is disabled. This is a workaround
        // for wrong event order in IE. Following is a dummy listener that
        // is invoked as the last listener in IE.
        mxEvent.addListener(img, 'mousedown', function (evt) {
            // do nothing
        });

        // This listener is always called first before any other listener
        // in all browsers.
        mxEvent.addListener(img, 'mousedown', function (evt) {
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
            mxUtils.alert(tooltip + '\nLast update: ' + new Date());
        });

        return overlay;
    }

    /**
     * Returns a random state.
     */
    function getState() {
        var state = 'Init';
        var rnd = Math.random() * 4;

        if (rnd > 3) {
            state = 'Completed';
        }
        else if (rnd > 2) {
            state = 'Running';
        }
        else if (rnd > 1) {
            state = 'Waiting';
        }

        return state;
    }

    /**
     * Updates the display of the given graph using the XML data
     */
    function update(graph, xml) {
        if (xml != null &&
            xml.length > 0) {
            var doc = mxUtils.parseXml(xml);

            if (doc != null &&
                doc.documentElement != null) {
                var model = graph.getModel();
                var nodes = doc.documentElement.getElementsByTagName('update');

                if (nodes != null &&
                    nodes.length > 0) {

                    model.beginUpdate();
                    try {
                        for (var i = 0; i < nodes.length; i++) {
                            // Processes the activity nodes inside the process node
                            var id = nodes[i].getAttribute('id');
                            var state = nodes[i].getAttribute('state');

                            // Gets the cell for the given activity name from the model
                            var cell = model.getCell(id);

                            // Updates the cell color and adds some tooltip information
                            if (cell != null) {
                                // Resets the fillcolor and the overlay
                                graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, 'white', [cell]);
                                graph.removeCellOverlays(cell);

                                // Changes the cell color for the known states
                                if (state == 'Running') {
                                    graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, '#f8cecc', [cell]);
                                }
                                else if (state == 'Waiting') {
                                    graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, '#fff2cc', [cell]);
                                }
                                else if (state == 'Completed') {
                                    graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, '#d4e1f5', [cell]);
                                }

                                // Adds tooltip information using an overlay icon
                                if (state != 'Init') {
                                    // Sets the overlay for the cell in the graph
                                    graph.addCellOverlay(cell, createOverlay(graph.warningImage, 'State: ' + state));
                                }
                            }
                        } // for
                    }
                    finally {
                        model.endUpdate();
                    }
                }
            }
        }
    }

    // Custom parser for simple file format
    function parse(graph, filename) {
        var model = graph.getModel();

        // Gets the default parent for inserting new cells. This
        // is normally the first child of the root (ie. layer 0).
        var parent = graph.getDefaultParent();

        var req = mxUtils.load(filename);
        var text = req.getText();

        var lines = text.split('\n');

        // Creates the lookup table for the vertices
        var vertices = [];

        // Parses all lines (vertices must be first in the file)
        graph.getModel().beginUpdate();
        try {
            for (var i = 0; i < lines.length; i++) {
                // Ignores comments (starting with #)
                var colon = lines[i].indexOf(':');

                if (lines[i].substring(0, 1) != "#" ||
                    colon == -1) {
                    var comma = lines[i].indexOf(',');
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
                            if (value.indexOf('2-Way') >= 0) {
                                e.style = '2way';
                            }
                        }
                    }
                }
            }
        }
        finally {
            graph.getModel().endUpdate();
        }
    };

    // Parses the mxGraph XML file format
    function read(graph, filename) {
        var req = mxUtils.load(filename);
        var root = req.getDocumentElement();
        var dec = new mxCodec(root.ownerDocument);

        dec.decode(root, graph.getModel());
    };

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