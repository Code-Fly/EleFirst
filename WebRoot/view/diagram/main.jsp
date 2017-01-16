<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/16
  Time: 下午6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/commonMxGraph.jsp" %>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript">
        // Defines a subclass for mxVertexHandler that adds a set of clickable
        // icons to every selected vertex.
        function mxVertexToolHandler(state) {
            mxVertexHandler.apply(this, arguments);
        }
        ;

        mxVertexToolHandler.prototype = new mxVertexHandler();
        mxVertexToolHandler.prototype.constructor = mxVertexToolHandler;

        mxVertexToolHandler.prototype.domNode = null;

        mxVertexToolHandler.prototype.init = function () {
            mxVertexHandler.prototype.init.apply(this, arguments);

            // In this example we force the use of DIVs for images in IE. This
            // handles transparency in PNG images properly in IE and fixes the
            // problem that IE routes all mouse events for a gesture via the
            // initial IMG node, which means the target vertices
            this.domNode = document.createElement('div');
            this.domNode.style.position = 'absolute';
            this.domNode.style.whiteSpace = 'nowrap';

            // Workaround for event redirection via image tag in quirks and IE8
            function createImage(src) {
                if (mxClient.IS_IE && !mxClient.IS_SVG) {
                    var img = document.createElement('div');
                    img.style.backgroundImage = 'url(' + src + ')';
                    img.style.backgroundPosition = 'center';
                    img.style.backgroundRepeat = 'no-repeat';
                    img.style.display = (mxClient.IS_QUIRKS) ? 'inline' : 'inline-block';

                    return img;
                }
                else {
                    return mxUtils.createImage(src);
                }
            };


            // Delete
            var img = createImage(mxBasePath + 'images/examples/delete2.png');
            img.setAttribute('title', 'Delete');
            img.style.cursor = 'pointer';
            img.style.width = '16px';
            img.style.height = '16px';
            mxEvent.addGestureListeners(img,
                mxUtils.bind(this, function (evt) {
                    // Disables dragging the image
                    mxEvent.consume(evt);
                })
            );
            mxEvent.addListener(img, 'click',
                mxUtils.bind(this, function (evt) {
                    this.graph.removeCells([this.state.cell]);
                    mxEvent.consume(evt);
                })
            );
            this.domNode.appendChild(img);

            this.graph.container.appendChild(this.domNode);
            this.redrawTools();
        };

        mxVertexToolHandler.prototype.redraw = function () {
            mxVertexHandler.prototype.redraw.apply(this);
            this.redrawTools();
        };

        mxVertexToolHandler.prototype.redrawTools = function () {
            if (this.state != null && this.domNode != null) {
                var dy = (mxClient.IS_VML && document.compatMode == 'CSS1Compat') ? 20 : 4;
                this.domNode.style.left = (this.state.x + this.state.width - 56) + 'px';
                this.domNode.style.top = (this.state.y + this.state.height + dy) + 'px';
            }
        };

        mxVertexToolHandler.prototype.destroy = function (sender, me) {
            mxVertexHandler.prototype.destroy.apply(this, arguments);

            if (this.domNode != null) {
                this.domNode.parentNode.removeChild(this.domNode);
                this.domNode = null;
            }
        };

        // Function to create the entries in the popupmenu
        function createPopupMenu(graph, menu, cell, evt) {
            var id = cell.id;
            var children = cell.children;
            console.log(cell)
            if (cell != null && cell.edge == false && cell.style == "demo1") {
                menu.addItem('配置', mxBasePath + 'images/editors/image.gif', function () {
                    var model = graph.getModel();

                    model.beginUpdate();
                    try {
                        var cell = model.getCell(id);

                        // Updates the cell color and adds some tooltip information
                        if (cell != null) {
                            graph.cellsRemoved(children);

                            var num = prompt("请输入设备地址", "192.168.10.1:8888");
                            cell.setAttribute("xxxx", num);

                            // Resets the fillcolor and the overlay
//                            graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, 'green', [cell]);
                            var v11 = graph.insertVertex(cell, null, '<p style="color: red">1 (A)</p><p style="color: green">3 (A)</p><p style="color: orange">2 (A)</p>', 1, 1, 0, 0, 'align=left;verticalAlign=top;', true);


                        } // for
                    }
                    finally {
                        model.endUpdate();
                    }

//                    mxUtils.alert(id);
                });
//                menu.addItem('删除', mxBasePath + 'images/warning.gif', function () {
//                    var model = graph.getModel();
//
//                    model.beginUpdate();
//                    try {
//                        var cell = model.getCell(id);
//
//                        // Updates the cell color and adds some tooltip information
//                        if (cell != null) {
//                            graph.removeCells([cell]);
//                        } // for
//                    }
//                    finally {
//                        model.endUpdate();
//                    }
//                });
            }
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
        ;

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
        ;

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
        ;

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
        ;

        // Program starts here. Creates a sample graph in the
        // DOM node with the specified ID. This function is invoked
        // from the onLoad event handler of the document (see below).
        function main(container) {
            // Checks if the browser is supported
            if (!mxClient.isBrowserSupported()) {
                // Displays an error message if the browser is not supported.
                mxUtils.error('Browser is not supported!', 200, false);
            }
            else {
                // Creates the graph inside the given container
                var graph = new mxGraph(container);
                graph.setConnectable(true);
                graph.connectionHandler.createTarget = true;

                graph.createHandler = function (state) {
                    if (state != null &&
                        this.model.isVertex(state.cell)) {
                        return new mxVertexToolHandler(state);
                    }

                    return mxGraph.prototype.createHandler.apply(this, arguments);
                };

                // Uncomment the following if you want the container
                // to fit the size of the graph
                //graph.setResizeContainer(true);

                // Enables rubberband selection
                new mxRubberband(graph);

                // Gets the default parent for inserting new cells. This
                // is normally the first child of the root (ie. layer 0).
                var parent = graph.getDefaultParent();

                // Adds cells to the model in a single step
                graph.getModel().beginUpdate();
                try {
                    var v1 = graph.insertVertex(parent, null, 'Hello,', 20, 20, 80, 30);
                    var v2 = graph.insertVertex(parent, null, 'World!', 200, 150, 80, 30);
                    var e1 = graph.insertEdge(parent, null, '', v1, v2);
                }
                finally {
                    // Updates the display
                    graph.getModel().endUpdate();
                }
            }
        }
        ;
    </script>
    <script type="text/javascript">
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
                vertex.geometry.x = x;
                vertex.geometry.y = y;
//                vertex.style += ';align=right;verticalAlign=middle;fontSize=10;routingCenterX=0.5;';
//                vertex.value = '<img src="' + mxBasePath + 'images/editors/overlays/user3.png" width="16" height="16"><br><br>'+new Date().getTime()+'<br>';
//                alert(JSON.stringify(vertex))
//                console.log(prototype)
                console.log(vertex)
                graph.addCell(vertex);
                graph.setSelectionCell(vertex);


//                var parent = graph.getDefaultParent();
//
//                graph.getModel().beginUpdate();
//                try {
//                    var v1 = graph.insertVertex(parent, null, 'Hello,', x, y, vertex.geometry.width, vertex.geometry.height, vertex.style);
//                    // Alternative solution of creating a second label by creating a realtive child vertex
//                    // with size (0, 0). This will not be selectable and only the label colors can be used
//                    // for coloring as the actual shape will have zero size.
//                    var v11 = graph.insertVertex(v1, null, '<p style="color: red">1 (A)</p><p style="color: green">1.5 (A)</p><p style="color: orange">0 (A)</p>', 1, 1, 0, 0, 'align=left;verticalAlign=top;', true);
//                }
//                finally {
//                    graph.getModel().endUpdate();
//                }

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


        function main(container) {


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


            ///
            graph.createHandler = function (state) {
                if (state != null &&
                    this.model.isVertex(state.cell)) {
                    return new mxVertexToolHandler(state);
                }

                return mxGraph.prototype.createHandler.apply(this, arguments);
            };

            // Uncomment the following if you want the container
            // to fit the size of the graph
            //graph.setResizeContainer(true);

            ///

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
            addVertex(mxBasePath + 'images/editors/actor.gif', 100, 55, 'demo1');
            addVertex(mxBasePath + 'images/editors/actor.gif', 100, 40, 'demo2');
            addVertex(mxBasePath + 'images/editors/actor.gif', 150, 30, 'demo3');
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
            graph.getStylesheet().putCellStyle('demo1', style);

            style = mxUtils.clone(style);
            style[mxConstants.STYLE_IMAGE] = mxBasePath + 'images/examples/icons48/earth.png';
            graph.getStylesheet().putCellStyle('demo2', style);

            style = new Object();
            style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RECTANGLE;
            style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
            style[mxConstants.STYLE_FONTCOLOR] = '#000000';
            style[mxConstants.STYLE_STROKECOLOR] = 'none';
            style[mxConstants.STYLE_FILLCOLOR] = 'none';
            style[mxConstants.STYLE_GRADIENTCOLOR] = 'none';
            graph.getStylesheet().putCellStyle('demo3', style);


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
        }
        //        // Function to switch the overlay every 5 secs
        //        var f = function()
        //        {
        //            var overlays = graph.getCellOverlays(bar01);
        //
        //            if (overlays == null)
        //            {
        //                graph.removeCellOverlays(v2);
        //                graph.setCellWarning(v1, 'Tooltip');
        //            }
        //            else
        //            {
        //                graph.removeCellOverlays(v1);
        //                graph.setCellWarning(v2, 'Tooltip');
        //            }
        //        };
        //
        //        window.setInterval(f, 1000);
        //        f();

    </script>
    <!--
            Updates connection points before the routing is called.
    -->
    <script type="text/javascript">
        // Computes the position of edge to edge connection points.
        mxGraphView.prototype.updateFixedTerminalPoint = function (edge, terminal, source, constraint) {
            var pt = null;

            if (constraint != null) {
                pt = this.graph.getConnectionPoint(terminal, constraint);
            }

            if (source) {
                edge.sourceSegment = null;
            }
            else {
                edge.targetSegment = null;
            }

            if (pt == null) {
                var s = this.scale;
                var tr = this.translate;
                var orig = edge.origin;
                var geo = this.graph.getCellGeometry(edge.cell);
                pt = geo.getTerminalPoint(source);

                // Computes edge-to-edge connection point
                if (pt != null) {
                    pt = new mxPoint(s * (tr.x + pt.x + orig.x),
                        s * (tr.y + pt.y + orig.y));

                    // Finds nearest segment on edge and computes intersection
                    if (terminal != null && terminal.absolutePoints != null) {
                        var seg = mxUtils.findNearestSegment(terminal, pt.x, pt.y);

                        // Finds orientation of the segment
                        var p0 = terminal.absolutePoints[seg];
                        var pe = terminal.absolutePoints[seg + 1];
                        var horizontal = (p0.x - pe.x == 0);

                        // Stores the segment in the edge state
                        var key = (source) ? 'sourceConstraint' : 'targetConstraint';
                        var value = (horizontal) ? 'horizontal' : 'vertical';
                        edge.style[key] = value;

                        // Keeps the coordinate within the segment bounds
                        if (horizontal) {
                            pt.x = p0.x;
                            pt.y = Math.min(pt.y, Math.max(p0.y, pe.y));
                            pt.y = Math.max(pt.y, Math.min(p0.y, pe.y));
                        }
                        else {
                            pt.y = p0.y;
                            pt.x = Math.min(pt.x, Math.max(p0.x, pe.x));
                            pt.x = Math.max(pt.x, Math.min(p0.x, pe.x));
                        }
                    }
                }
                // Computes constraint connection points on vertices and ports
                else if (terminal != null && terminal.cell.geometry.relative) {
                    pt = new mxPoint(this.getRoutingCenterX(terminal),
                        this.getRoutingCenterY(terminal));
                }

                // Snaps point to grid
                /*if (pt != null)
                 {
                 var tr = this.graph.view.translate;
                 var s = this.graph.view.scale;

                 pt.x = (this.graph.snap(pt.x / s - tr.x) + tr.x) * s;
                 pt.y = (this.graph.snap(pt.y / s - tr.y) + tr.y) * s;
                 }*/
            }

            edge.setAbsoluteTerminalPoint(pt, source);
        };
    </script>
    <!--
        Overrides methods to preview and create new edges.
    -->
    <script type="text/javascript">
        // Sets source terminal point for edge-to-edge connections.
        mxConnectionHandler.prototype.createEdgeState = function (me) {
            var edge = this.graph.createEdge();

            if (this.sourceConstraint != null && this.previous != null) {
                edge.style = mxConstants.STYLE_EXIT_X + '=' + this.sourceConstraint.point.x + ';' +
                    mxConstants.STYLE_EXIT_Y + '=' + this.sourceConstraint.point.y + ';';
            }
            else if (this.graph.model.isEdge(me.getCell())) {
                var scale = this.graph.view.scale;
                var tr = this.graph.view.translate;
                var pt = new mxPoint(this.graph.snap(me.getGraphX() / scale) - tr.x,
                    this.graph.snap(me.getGraphY() / scale) - tr.y);
                edge.geometry.setTerminalPoint(pt, true);
            }

            return this.graph.view.createState(edge);
        };

        // Uses right mouse button to create edges on background (see also: lines 67 ff)
        mxConnectionHandler.prototype.isStopEvent = function (me) {
            return me.getState() != null || mxEvent.isRightMouseButton(me.getEvent());
        };

        // Updates target terminal point for edge-to-edge connections.
        mxConnectionHandlerUpdateCurrentState = mxConnectionHandler.prototype.updateCurrentState;
        mxConnectionHandler.prototype.updateCurrentState = function (me) {
            mxConnectionHandlerUpdateCurrentState.apply(this, arguments);

            if (this.edgeState != null) {
                this.edgeState.cell.geometry.setTerminalPoint(null, false);

                if (this.shape != null && this.currentState != null &&
                    this.currentState.view.graph.model.isEdge(this.currentState.cell)) {
                    var scale = this.graph.view.scale;
                    var tr = this.graph.view.translate;
                    var pt = new mxPoint(this.graph.snap(me.getGraphX() / scale) - tr.x,
                        this.graph.snap(me.getGraphY() / scale) - tr.y);
                    this.edgeState.cell.geometry.setTerminalPoint(pt, false);
                }
            }
        };

        // Updates the terminal and control points in the cloned preview.
        mxEdgeSegmentHandler.prototype.clonePreviewState = function (point, terminal) {
            var clone = mxEdgeHandler.prototype.clonePreviewState.apply(this, arguments);
            clone.cell = clone.cell.clone();

            if (this.isSource || this.isTarget) {
                clone.cell.geometry = clone.cell.geometry.clone();

                // Sets the terminal point of an edge if we're moving one of the endpoints
                if (this.graph.getModel().isEdge(clone.cell)) {
                    // TODO: Only set this if the target or source terminal is an edge
                    clone.cell.geometry.setTerminalPoint(point, this.isSource);
                }
                else {
                    clone.cell.geometry.setTerminalPoint(null, this.isSource);
                }
            }

            return clone;
        };

        var mxEdgeHandlerConnect = mxEdgeHandler.prototype.connect;
        mxEdgeHandler.prototype.connect = function (edge, terminal, isSource, isClone, me) {
            var result = null;
            var model = this.graph.getModel();
            var parent = model.getParent(edge);

            model.beginUpdate();
            try {
                result = mxEdgeHandlerConnect.apply(this, arguments);
                var geo = model.getGeometry(result);

                if (geo != null) {
                    geo = geo.clone();
                    var pt = null;

                    if (model.isEdge(terminal)) {
                        pt = this.abspoints[(this.isSource) ? 0 : this.abspoints.length - 1];
                        pt.x = pt.x / this.graph.view.scale - this.graph.view.translate.x;
                        pt.y = pt.y / this.graph.view.scale - this.graph.view.translate.y;

                        var pstate = this.graph.getView().getState(
                            this.graph.getModel().getParent(edge));

                        if (pstate != null) {
                            pt.x -= pstate.origin.x;
                            pt.y -= pstate.origin.y;
                        }

                        pt.x -= this.graph.panDx / this.graph.view.scale;
                        pt.y -= this.graph.panDy / this.graph.view.scale;
                    }

                    geo.setTerminalPoint(pt, isSource);
                    model.setGeometry(edge, geo);
                }
            }
            finally {
                model.endUpdate();
            }

            return result;
        };
    </script>
    <!--
        Adds in-place highlighting for complete cell area (no hotspot).
    -->
    <script type="text/javascript">
        mxConnectionHandlerCreateMarker = mxConnectionHandler.prototype.createMarker;
        mxConnectionHandler.prototype.createMarker = function () {
            var marker = mxConnectionHandlerCreateMarker.apply(this, arguments);

            // Uses complete area of cell for new connections (no hotspot)
            marker.intersects = function (state, evt) {
                return true;
            };

            // Adds in-place highlighting
            mxCellHighlightHighlight = mxCellHighlight.prototype.highlight;
            marker.highlight.highlight = function (state) {
                if (this.state != state) {
                    if (this.state != null) {
                        this.state.style = this.lastStyle;

                        // Workaround for shape using current stroke width if no strokewidth defined
                        this.state.style['strokeWidth'] = this.state.style['strokeWidth'] || '1';
                        this.state.style['strokeColor'] = this.state.style['strokeColor'] || 'none';

                        if (this.state.shape != null) {
                            this.state.view.graph.cellRenderer.configureShape(this.state);
                            this.state.shape.redraw();
                        }
                    }

                    if (state != null) {
                        this.lastStyle = state.style;
                        state.style = mxUtils.clone(state.style);
                        state.style['strokeColor'] = '#00ff00';
                        state.style['strokeWidth'] = '3';

                        if (state.shape != null) {
                            state.view.graph.cellRenderer.configureShape(state);
                            state.shape.redraw();
                        }
                    }

                    this.state = state;
                }
            };

            return marker;
        };

        mxEdgeHandlerCreateMarker = mxEdgeHandler.prototype.createMarker;
        mxEdgeHandler.prototype.createMarker = function () {
            var marker = mxEdgeHandlerCreateMarker.apply(this, arguments);

            // Adds in-place highlighting when reconnecting existing edges
            marker.highlight.highlight = this.graph.connectionHandler.marker.highlight.highlight;

            return marker;
        }
    </script>
    <!--
        Adds oval markers for edge-to-edge connections.
    -->
    <script type="text/javascript">
        mxGraphGetCellStyle = mxGraph.prototype.getCellStyle;
        mxGraph.prototype.getCellStyle = function (cell) {
            var style = mxGraphGetCellStyle.apply(this, arguments);

            if (style != null && this.model.isEdge(cell)) {
                style = mxUtils.clone(style);

                if (this.model.isEdge(this.model.getTerminal(cell, true))) {
                    style['startArrow'] = 'oval';
                }

                if (this.model.isEdge(this.model.getTerminal(cell, false))) {
                    style['endArrow'] = 'oval';
                }
            }

            return style;
        };
    </script>
    <!--
        Imlements a custom resistor shape. Direction currently ignored here.
    -->
    <script type="text/javascript">
        function ResistorShape() {
        }
        ;
        ResistorShape.prototype = new mxCylinder();
        ResistorShape.prototype.constructor = ResistorShape;

        ResistorShape.prototype.redrawPath = function (path, x, y, w, h, isForeground) {
            var dx = w / 16;

            if (isForeground) {
                path.moveTo(0, h / 2);
                path.lineTo(2 * dx, h / 2);
                path.lineTo(3 * dx, 0);
                path.lineTo(5 * dx, h);
                path.lineTo(7 * dx, 0);
                path.lineTo(9 * dx, h);
                path.lineTo(11 * dx, 0);
                path.lineTo(13 * dx, h);
                path.lineTo(14 * dx, h / 2);
                path.lineTo(16 * dx, h / 2);

                path.end();
            }
        };

        mxCellRenderer.prototype.defaultShapes['resistor'] = ResistorShape;
    </script>
    <!--
        Imlements a custom resistor shape. Direction currently ignored here.
    -->
    <script type="text/javascript">
        mxEdgeStyle.WireConnector = function (state, source, target, hints, result) {
            // Creates array of all way- and terminalpoints
            var pts = state.absolutePoints;
            var horizontal = true;
            var hint = null;

            // Gets the initial connection from the source terminal or edge
            if (source != null && state.view.graph.model.isEdge(source.cell)) {
                horizontal = state.style['sourceConstraint'] == 'horizontal';
            }
            else if (source != null) {
                horizontal = source.style['portConstraint'] != 'vertical';

                // Checks the direction of the shape and rotates
                var direction = source.style[mxConstants.STYLE_DIRECTION];

                if (direction == 'north' || direction == 'south') {
                    horizontal = !horizontal;
                }
            }

            // Adds the first point
            // TODO: Should move along connected segment
            var pt = pts[0];

            if (pt == null && source != null) {
                pt = new mxPoint(state.view.getRoutingCenterX(source), state.view.getRoutingCenterY(source));
            }
            else if (pt != null) {
                pt = pt.clone();
            }

            var first = pt;

            // Adds the waypoints
            if (hints != null && hints.length > 0) {
                // FIXME: First segment not movable
                /*hint = state.view.transformControlPoint(state, hints[0]);
                 mxLog.show();
                 mxLog.debug(hints.length,'hints0.y='+hint.y, pt.y)

                 if (horizontal && Math.floor(hint.y) != Math.floor(pt.y))
                 {
                 mxLog.show();
                 mxLog.debug('add waypoint');

                 pt = new mxPoint(pt.x, hint.y);
                 result.push(pt);
                 pt = pt.clone();
                 //horizontal = !horizontal;
                 }*/

                for (var i = 0; i < hints.length; i++) {
                    horizontal = !horizontal;
                    hint = state.view.transformControlPoint(state, hints[i]);

                    if (horizontal) {
                        if (pt.y != hint.y) {
                            pt.y = hint.y;
                            result.push(pt.clone());
                        }
                    }
                    else if (pt.x != hint.x) {
                        pt.x = hint.x;
                        result.push(pt.clone());
                    }
                }
            }
            else {
                hint = pt;
            }

            // Adds the last point
            pt = pts[pts.length - 1];

            // TODO: Should move along connected segment
            if (pt == null && target != null) {
                pt = new mxPoint(state.view.getRoutingCenterX(target), state.view.getRoutingCenterY(target));
            }

            if (horizontal) {
                if (pt.y != hint.y && first.x != pt.x) {
                    result.push(new mxPoint(pt.x, hint.y));
                }
            }
            else if (pt.x != hint.x && first.y != pt.y) {
                result.push(new mxPoint(hint.x, pt.y));
            }
        };

        mxStyleRegistry.putValue('wireEdgeStyle', mxEdgeStyle.WireConnector);

        // This connector needs an mxEdgeSegmentHandler
        mxGraphCreateHandler = mxGraph.prototype.createHandler;
        mxGraph.prototype.createHandler = function (state) {
            var result = null;

            if (state != null) {
                if (this.model.isEdge(state.cell)) {
                    var style = this.view.getEdgeStyle(state);

                    if (style == mxEdgeStyle.WireConnector) {
                        return new mxEdgeSegmentHandler(state);
                    }
                }
            }

            return mxGraphCreateHandler.apply(this, arguments);
        };
    </script>
</head>
<body onload="main(document.getElementById('graphContainer'))">
<div id="graphContainer"
     style="overflow:auto;position:relative;width:800px;height:600px;border:1px solid gray;background:url('images/wires-grid.gif');background-position:-1px 0px;cursor:crosshair;">
</div>
</body>
</html>
