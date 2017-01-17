/**
 * Created by barrie on 17/1/16.
 */
$(document).ready(function () {
    var container = document.getElementById('graphContainer');
    var graph = new mxGraph(container);
    graph.view.scale = 1;
    graph.setPanning(true);
    graph.setConnectable(true);
    graph.setConnectableEdges(true);
    graph.setDisconnectOnMove(false);
    graph.foldingEnabled = false;

    graph.isHtmlLabel = function (cell) {
        return true;
    };

    // Installs a popupmenu handler using local function (see below).
    graph.popupMenuHandler.factoryMethod = function (menu, cell, evt) {
        return createPopupMenu(graph, menu, cell, evt);
    };

    //Maximum size
    graph.maximumGraphBounds = new mxRectangle(0, 0, 800, 600)
    graph.border = 50;

    // Panning handler consumed right click so this must be
    // disabled if right click should stop connection handler.
    graph.panningHandler.isPopupTrigger = function () {
        return false;
    };

    // Enables return key to stop editing (use shift-enter for newlines)
    graph.setEnterStopsCellEditing(true);


    // Adds rubberband selection
    new mxRubberband(graph);

    // Alternative solution for implementing connection points without child cells.
    // This can be extended as shown in portrefs.html example to allow for per-port
    // incoming/outgoing direction.
    graph.getAllConnectionConstraints = function (terminal) {
        var geo = (terminal != null) ? this.getCellGeometry(terminal.cell) : null;

        if ((geo != null ? !geo.relative : false) &&
            this.getModel().isVertex(terminal.cell) &&
            this.getModel().getChildCount(terminal.cell) == 0) {
            return [new mxConnectionConstraint(new mxPoint(0, 0.5), false),
                new mxConnectionConstraint(new mxPoint(1, 0.5), false)];
        }

        return null;
    };

    // Makes sure non-relative cells can only be connected via constraints
    graph.connectionHandler.isConnectableCell = function (cell) {
        if (this.graph.getModel().isEdge(cell)) {
            return true;
        }
        else {
            var geo = (cell != null) ? this.graph.getCellGeometry(cell) : null;

            return (geo != null) ? geo.relative : false;
        }
    };
    mxEdgeHandler.prototype.isConnectableCell = function (cell) {
        return graph.connectionHandler.isConnectableCell(cell);
    };

    // Creates the div for the toolbar
    var tbContainer = document.createElement('div');
    tbContainer.style.position = 'absolute';
    tbContainer.style.overflow = 'hidden';
    tbContainer.style.padding = '2px';
    tbContainer.style.left = '0px';
    tbContainer.style.top = '0px';
    tbContainer.style.width = '24px';
    tbContainer.style.bottom = '0px';

    document.body.appendChild(tbContainer);

    // Creates new toolbar without event processing
    var toolbar = new mxToolbar(tbContainer);
    toolbar.enabled = false


    // Workaround for Internet Explorer ignoring certain styles
    if (mxClient.IS_QUIRKS) {
        document.body.style.overflow = 'hidden';
        new mxDivResizer(tbContainer);
        new mxDivResizer(container);
    }

    // Creates the model and the graph inside the container
    // using the fastest rendering available on the browser
    var model = new mxGraphModel();

    // Enables new connections in the graph
    graph.setConnectable(true);
    graph.setMultigraph(false);

    // Stops editing on enter or escape keypress
    var keyHandler = new mxKeyHandler(graph);
    var rubberband = new mxRubberband(graph);

    var addVertex = function (icon, w, h, style) {
        var vertex = new mxCell(null, new mxGeometry(0, 0, w, h), style);
        vertex.setVertex(true);

        var img = addToolbarItem(graph, toolbar, vertex, icon);
        img.enabled = true;

        graph.getSelectionModel().addListener(mxEvent.CHANGE, function () {
            var tmp = graph.isSelectionEmpty();
            mxUtils.setOpacity(img, (tmp) ? 100 : 20);
            img.enabled = tmp;
        });
    };

    addVertex(mxBasePath + 'images/editors/rectangle.gif', 100, 40, '');
    addVertex(mxBasePath + 'images/editors/rounded.gif', 100, 40, 'shape=rounded');
    addVertex(mxBasePath + 'images/editors/ellipse.gif', 40, 40, 'shape=ellipse');
    addVertex(mxBasePath + 'images/editors/rhombus.gif', 40, 40, 'shape=rhombus');
    addVertex(mxBasePath + 'images/editors/triangle.gif', 40, 40, 'shape=triangle');
    addVertex(mxBasePath + 'images/editors/cylinder.gif', 40, 40, 'shape=cylinder');
    addVertex(mxBasePath + 'images/editors/actor.gif', 30, 40, 'shape=actor');
    addVertex(mxBasePath + 'images/editors/triangle.gif', 100, 55, 'triangle');
    addVertex(mxBasePath + 'images/editors/image.gif', 100, 40, 'earth');
    addVertex(mxBasePath + 'images/editors/text.gif', 150, 30, 'text');
    addVertex(mxBasePath + 'images/editors/italic.gif', 80, 60, 'current');
    // Adds a special tooltip for edges
    graph.setTooltips(true);

    var getTooltipForCell = graph.getTooltipForCell;
    graph.getTooltipForCell = function (cell) {
        var tip = '';

        if (cell != null) {
            var src = this.getModel().getTerminal(cell, true);

            if (src != null) {
                tip += this.getTooltipForCell(src) + ' ';
            }

            var parent = this.getModel().getParent(cell);

            if (this.getModel().isVertex(parent)) {
                tip += this.getTooltipForCell(parent) + '.';
            }

            tip += getTooltipForCell.apply(this, arguments);

            var trg = this.getModel().getTerminal(cell, false);

            if (trg != null) {
                tip += ' ' + this.getTooltipForCell(trg);
            }
        }

        return tip;
    };

    // Switch for black background and bright styles
    var invert = false;

    if (invert) {
        container.style.backgroundColor = 'black';

        // White in-place editor text color
        mxCellEditorStartEditing = mxCellEditor.prototype.startEditing;
        mxCellEditor.prototype.startEditing = function (cell, trigger) {
            mxCellEditorStartEditing.apply(this, arguments);

            if (this.textarea != null) {
                this.textarea.style.color = '#FFFFFF';
            }
        };

        mxGraphHandler.prototype.previewColor = 'white';
    }
    var style = new Object();
    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
    style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
    style[mxConstants.STYLE_IMAGE] = mxBasePath + 'images/components/demo.png';
    style[mxConstants.STYLE_FONTCOLOR] = '#000000';
    graph.getStylesheet().putCellStyle('triangle', style);

    style = mxUtils.clone(style);
    style[mxConstants.STYLE_IMAGE] = mxBasePath + 'images/examples/icons48/earth.png';
    graph.getStylesheet().putCellStyle('earth', style);

    style = new Object();
    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RECTANGLE;
    style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
    style[mxConstants.STYLE_FONTCOLOR] = '#000000';
    style[mxConstants.STYLE_STROKECOLOR] = 'none';
    style[mxConstants.STYLE_FILLCOLOR] = 'none';
    style[mxConstants.STYLE_GRADIENTCOLOR] = 'none';
    graph.getStylesheet().putCellStyle('text', style);

    style = new Object();
    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RECTANGLE;
    style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
    style[mxConstants.STYLE_FONTCOLOR] = '#000000';
    style[mxConstants.STYLE_STROKECOLOR] = 'none';
    style[mxConstants.STYLE_FILLCOLOR] = 'none';
    style[mxConstants.STYLE_GRADIENTCOLOR] = 'none';
    graph.getStylesheet().putCellStyle('current', style);


    var labelBackground = (invert) ? '#000000' : '#FFFFFF';
    var fontColor = (invert) ? '#FFFFFF' : '#000000';
    var strokeColor = (invert) ? '#C0C0C0' : '#000000';
    var fillColor = (invert) ? 'none' : '#FFFFFF';

    var style = graph.getStylesheet().getDefaultEdgeStyle();
    delete style['endArrow'];
    style['strokeColor'] = strokeColor;
    style['labelBackgroundColor'] = labelBackground;
    style['edgeStyle'] = 'wireEdgeStyle';
    style['fontColor'] = fontColor;
    style['fontSize'] = '9';
    style['movable'] = '0';
    style['strokeWidth'] = strokeWidth;
    //style['rounded'] = '1';

    // Sets join node size
    style['startSize'] = joinNodeSize;
    style['endSize'] = joinNodeSize;

    style = graph.getStylesheet().getDefaultVertexStyle();
    style['gradientDirection'] = 'south';
    //style['gradientColor'] = '#909090';
    style['strokeColor'] = strokeColor;
    //style['fillColor'] = '#e0e0e0';
    style['fillColor'] = 'none';
    style['fontColor'] = fontColor;
    style['fontStyle'] = '1';
    style['fontSize'] = '12';
    style['resizable'] = '0';
//            style['rounded'] = '1';
    style['strokeWidth'] = strokeWidth;


//            var parent = graph.getDefaultParent();
//
//            graph.getModel().beginUpdate();
//            try {
//                var bar01 = graph.insertVertex(parent, null, '', 0, 0, 30, 8,
//                    'whiteSpace=wrap;html=1;rounded=0;');
//                var bar01_left = graph.insertVertex(bar01, null, '', 0, 0, 10, 16,
//                    'shape=line;align=left;verticalAlign=middle;fontSize=10;routingCenterX=-0.5;' +
//                    'spacingLeft=12;fontColor=' + fontColor + ';strokeColor=' + strokeColor);
//                bar01_left.geometry.relative = true;
//                bar01_left.geometry.offset = new mxPoint(-bar01_left.geometry.width, 0);
//                bar01.insert(bar01_left);
//                var bar01_right = bar01_left.clone();
//                bar01_right.geometry.x = 1;
//                bar01_right.style = 'shape=line;align=right;verticalAlign=middle;fontSize=10;routingCenterX=0.5;' +
//                    'spacingRight=12;fontColor=' + fontColor + ';strokeColor=' + strokeColor;
//                bar01_right.geometry.offset = new mxPoint(0, 0);
//                bar01.insert(bar01_right);
//
//                var v1 = graph.insertVertex(parent, null, '', 230, 10, 100, 100, 'image');
//
//
//                var v1 = graph.insertVertex(parent, null, 'J1', 80, 40, 40, 80,
//                    'verticalLabelPosition=top;verticalAlign=bottom;shadow=1;fillColor=' + fillColor);
//                v1.setConnectable(false);
//
//                var v11 = graph.insertVertex(v1, null, '1', 0, 0, 10, 16,
//                    'shape=line;align=left;verticalAlign=middle;fontSize=10;routingCenterX=-0.5;' +
//                    'spacingLeft=12;fontColor=' + fontColor + ';strokeColor=' + strokeColor);
//                v11.geometry.relative = true;
//                v11.geometry.offset = new mxPoint(-v11.geometry.width, 2);
//                var v12 = v11.clone();
//                v12.value = '2';
//                v12.geometry.offset = new mxPoint(-v11.geometry.width, 22);
//                v1.insert(v12);
//                var v13 = v11.clone();
//                v13.value = '3';
//                v13.geometry.offset = new mxPoint(-v11.geometry.width, 42);
//                v1.insert(v13);
//                var v14 = v11.clone();
//                v14.value = '4';
//                v14.geometry.offset = new mxPoint(-v11.geometry.width, 62);
//                v1.insert(v14);
//
//                var v15 = v11.clone();
//                v15.value = '5';
//                v15.geometry.x = 1;
//                v15.style = 'shape=line;align=right;verticalAlign=middle;fontSize=10;routingCenterX=0.5;' +
//                    'spacingRight=12;fontColor=' + fontColor + ';strokeColor=' + strokeColor;
//                v15.geometry.offset = new mxPoint(0, 2);
//                v1.insert(v15);
//                var v16 = v15.clone();
//                v16.value = '6';
//                v16.geometry.offset = new mxPoint(0, 22);
//                v1.insert(v16);
//                var v17 = v15.clone();
//                v17.value = '7';
//                v17.geometry.offset = new mxPoint(0, 42);
//                v1.insert(v17);
//                var v18 = v15.clone();
//                v18.value = '8';
//                v18.geometry.offset = new mxPoint(0, 62);
//                v1.insert(v18);
//
//                var v19 = v15.clone();
//                v19.value = 'clk';
//                v19.geometry.x = 0.5;
//                v19.geometry.y = 1;
//                v19.geometry.width = 10;
//                v19.geometry.height = 4;
//                // NOTE: portConstraint is defined for east direction, so must be inverted here
//                v19.style = 'shape=triangle;direction=north;spacingBottom=12;align=center;portConstraint=horizontal;' +
//                    'fontSize=8;strokeColor=' + strokeColor + ';routingCenterY=0.5;';
//                v19.geometry.offset = new mxPoint(-4, -4);
//                v1.insert(v19);
//
//                var v2 = graph.insertVertex(parent, null, 'R1', 220, 220, 80, 20,
//                    'shape=resistor;verticalLabelPosition=top;verticalAlign=bottom;');
//
//                // Uses implementation of connection points via constraints (see above)
//                //v2.setConnectable(false);
//
//                /*var v21 = graph.insertVertex(v2, null, 'A', 0, 0.5, 10, 1,
//                 'shape=none;spacingBottom=11;spacingLeft=1;align=left;fontSize=8;'+
//                 'fontColor=#4c4c4c;strokeColor=#909090;');
//                 v21.geometry.relative = true;
//                 v21.geometry.offset = new mxPoint(0, -1);
//
//                 var v22 = graph.insertVertex(v2, null, 'B', 1, 0.5, 10, 1,
//                 'spacingBottom=11;spacingLeft=1;align=left;fontSize=8;'+
//                 'fontColor=#4c4c4c;strokeColor=#909090;');
//                 v22.geometry.relative = true;
//                 v22.geometry.offset = new mxPoint(-10, -1);*/
//
//                var v3 = graph.addCell(graph.getModel().cloneCell(v1));
//                v3.value = 'J3';
//                v3.geometry.x = 420;
//                v3.geometry.y = 340;
//
//                // Connection constraints implemented in edges, alternatively this
//                // can be implemented using references, see: portrefs.html
//                var e1 = graph.insertEdge(parent, null, 'e1', v1.getChildAt(7), v2,
//                    'entryX=0;entryY=0.5;entryPerimeter=0;');
//                e1.geometry.points = [new mxPoint(180, 110)];
//
//                var e2 = graph.insertEdge(parent, null, 'e2', v1.getChildAt(4), v2,
//                    'entryX=1;entryY=0.5;entryPerimeter=0;');
//                e2.geometry.points = [new mxPoint(320, 50), new mxPoint(320, 230)];
//
//                var e3 = graph.insertEdge(parent, null, 'crossover', e1, e2);
//                e3.geometry.setTerminalPoint(new mxPoint(180, 140), true);
//                e3.geometry.setTerminalPoint(new mxPoint(320, 140), false);
//
//// 				var e1 = graph.insertEdge(parent, null, 'e1', v1.getChildAt(7), v2.getChildAt(0));
//// 				e1.geometry.points = [new mxPoint(180, 140)];
//
//// 				var e2 = graph.insertEdge(parent, null, '', v1.getChildAt(4), v2.getChildAt(1));
//// 				e2.geometry.points = [new mxPoint(320, 80)];
//
//// 				var e3 = graph.insertEdge(parent, null, 'crossover', e1, e2);
//// 				e3.geometry.setTerminalPoint(new mxPoint(180, 160), true);
//// 				e3.geometry.setTerminalPoint(new mxPoint(320, 160), false);
//
//                var e4 = graph.insertEdge(parent, null, 'e4', v2, v3.getChildAt(0),
//                    'exitX=1;exitY=0.5;entryPerimeter=0;');
//                e4.geometry.points = [new mxPoint(380, 230)];
//
//                var e5 = graph.insertEdge(parent, null, 'e5', v3.getChildAt(5), v1.getChildAt(0));
//                e5.geometry.points = [new mxPoint(500, 310), new mxPoint(500, 20), new mxPoint(50, 20)];
//
//                var e6 = graph.insertEdge(parent, null, '');
//                e6.geometry.setTerminalPoint(new mxPoint(100, 500), true);
//                e6.geometry.setTerminalPoint(new mxPoint(600, 500), false);
//
//                var e7 = graph.insertEdge(parent, null, 'e7', v3.getChildAt(7), e6);
//                e7.geometry.setTerminalPoint(new mxPoint(500, 500), false);
//                e7.geometry.points = [new mxPoint(500, 350)];
//
//
//            }
//            finally {
//                graph.getModel().endUpdate();
//            }


    document.body.appendChild(mxUtils.button('Zoom In', function () {
        graph.zoomIn();
    }));

    document.body.appendChild(mxUtils.button('Zoom Out', function () {
        graph.zoomOut();
    }));

    // Undo/redo
    var undoManager = new mxUndoManager();
    var listener = function (sender, evt) {
        undoManager.undoableEditHappened(evt.getProperty('edit'));
    };
    graph.getModel().addListener(mxEvent.UNDO, listener);
    graph.getView().addListener(mxEvent.UNDO, listener);

    document.body.appendChild(mxUtils.button('Undo', function () {
        undoManager.undo();
    }));

    document.body.appendChild(mxUtils.button('Redo', function () {
        undoManager.redo();
    }));

    // Shows XML for debugging the actual model
    document.body.appendChild(mxUtils.button('Delete', function () {
        graph.removeCells();
    }));

    // Rotate
    document.body.appendChild(mxUtils.button('Rotate', function () {
        var cell = graph.getSelectionCell();

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
    }));

    // Creates a button to invoke the refresh function
    document.body.appendChild(mxUtils.button('Update', function (evt) {
        // XML is normally fetched from URL at server using mxUtils.get - this is a client-side
        // string with randomized states to demonstrate the idea of the workflow monitor
        var xml = '<process><update id="3" state="' + getState() + '"/><update id="4" state="' + getState() + '"/></process>';
        update(graph, xml);
    }));

    // Wire-mode
    var checkbox = document.createElement('input');
    checkbox.setAttribute('type', 'checkbox');

    document.body.appendChild(checkbox);
    mxUtils.write(document.body, 'Wire Mode');

    // Adds an option to view the XML of the graph
    document.body.appendChild(mxUtils.button('View XML', function () {
        var encoder = new mxCodec();
        var node = encoder.encode(graph.getModel());
        mxUtils.popup(mxUtils.getPrettyXml(node), true);
    }));

    document.body.appendChild(mxUtils.button('Read XML', function () {
        // Load cells and layouts the graph
        graph.getModel().beginUpdate();
        try {
            // Loads the custom file format (TXT file)
            // parse(graph, 'fileio.txt');

            // Loads the mxGraph file format (XML file)
            read(graph, 'data/fileio.xml');

            // Gets the default parent for inserting new cells. This
            // is normally the first child of the root (ie. layer 0).
            var parent = graph.getDefaultParent();

            // Executes the layout
            layout.execute(parent);
        }
        finally {
            // Updates the display
            graph.getModel().endUpdate();
        }
    }));

    // Starts connections on the background in wire-mode
    var connectionHandlerIsStartEvent = graph.connectionHandler.isStartEvent;
    graph.connectionHandler.isStartEvent = function (me) {
        return checkbox.checked || connectionHandlerIsStartEvent.apply(this, arguments);
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
                if (!checkbox.checked && this.graph.getModel().isEdge(this.previous.cell)) {
                    this.reset();
                }

                return;
            }
        }

        connectionHandlerMouseUp.apply(this, arguments);
    };

    // Grid
    var checkbox2 = document.createElement('input');
    checkbox2.setAttribute('type', 'checkbox');
    checkbox2.setAttribute('checked', 'true');

    document.body.appendChild(checkbox2);
    mxUtils.write(document.body, 'Grid');

    mxEvent.addListener(checkbox2, 'click', function (evt) {
        if (checkbox2.checked) {
            container.style.background = "url('" + mxBasePath + "images/examples/wires-grid.gif')";
        }
        else {
            container.style.background = '';
        }

        container.style.backgroundColor = (invert) ? 'black' : 'white';
    });

    mxEvent.disableContextMenu(container);
});

// If connect preview is not moved away then getCellAt is used to detect the cell under
// the mouse if the mouse is over the preview shape in IE (no event transparency), ie.
// the built-in hit-detection of the HTML document will not be used in this case.
mxConnectionHandler.prototype.movePreviewAway = false;
mxConnectionHandler.prototype.waypointsEnabled = true;
mxGraph.prototype.resetEdgesOnConnect = false;
mxConstants.SHADOWCOLOR = '#C0C0C0';
var joinNodeSize = 7;
var strokeWidth = 2;

// Replaces the port image
mxConstraintHandler.prototype.pointImage = new mxImage(mxBasePath + "images/examples/dot.gif", 10, 10);

// Enables guides
mxGraphHandler.prototype.guidesEnabled = true;

// Alt disables guides
mxGuide.prototype.isEnabledForEvent = function (evt) {
    return !mxEvent.isAltDown(evt);
};

// Enables snapping waypoints to terminals
mxEdgeHandler.prototype.snapToTerminals = true;

function addToolbarItem(graph, toolbar, prototype, image) {
    // Function that is executed when the image is dropped on
    // the graph. The cell argument points to the cell under
    // the mousepointer if there is one.
    var funct = function (graph, evt, cell, x, y) {
        graph.stopEditing(false);

        var vertex = graph.getModel().cloneCell(prototype);
        if (vertex.style == "current") {
            var parent = graph.getDefaultParent();
            graph.getModel().beginUpdate();
            try {
                var current = graph.insertVertex(parent, null, '<div class="current"><p style="color: red">1 (A)</p><p style="color: green">1.5 (A)</p><p style="color: orange">0 (A)</p></div>', x, y, vertex.geometry.width, vertex.geometry.height, vertex.style);
            }
            finally {
                graph.getModel().endUpdate();
            }
        } else {
            vertex.geometry.x = x;
            vertex.geometry.y = y;
            // vertex.setAttribute("xxx","xxxxx");
//                vertex.style += ';align=right;verticalAlign=middle;fontSize=10;routingCenterX=0.5;';
//                vertex.value = '<img src="' + mxBasePath + 'images/editors/overlays/user3.png" width="16" height="16"><br><br>'+new Date().getTime()+'<br>';
//                alert(JSON.stringify(vertex))
//                console.log(prototype)
            graph.addCell(vertex);
            graph.setSelectionCell(vertex);
        }


    }

    // Creates the image which is used as the drag icon (preview)
    var img = toolbar.addMode(null, image, function (evt, cell) {
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

// Custom parser for simple file format
function parse(graph, filename)
{
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
    try
    {
        for (var i=0; i<lines.length; i++)
        {
            // Ignores comments (starting with #)
            var colon = lines[i].indexOf(':');

            if (lines[i].substring(0, 1) != "#" ||
                colon == -1)
            {
                var comma = lines[i].indexOf(',');
                var value = lines[i].substring(colon+2, lines[i].length);

                if (comma == -1 || comma > colon)
                {
                    var key = lines[i].substring(0, colon);

                    if (key.length > 0)
                    {
                        vertices[key] = graph.insertVertex(parent, null, value, 0, 0, 80, 70);
                    }
                }
                else if (comma < colon)
                {
                    // Looks up the vertices in the lookup table
                    var source = vertices[lines[i].substring(0, comma)];
                    var target = vertices[lines[i].substring(comma+1, colon)];

                    if (source != null && target != null)
                    {
                        var e = graph.insertEdge(parent, null, value, source, target);

                        // Uses the special 2-way style for 2-way labels
                        if (value.indexOf('2-Way') >= 0)
                        {
                            e.style = '2way';
                        }
                    }
                }
            }
        }
    }
    finally
    {
        graph.getModel().endUpdate();
    }
};

// Parses the mxGraph XML file format
function read(graph, filename)
{
    var req = mxUtils.load(filename);
    var root = req.getDocumentElement();
    var dec = new mxCodec(root.ownerDocument);

    dec.decode(root, graph.getModel());
};