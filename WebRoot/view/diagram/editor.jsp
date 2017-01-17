<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/17
  Time: 下午9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/commonMxGraph.jsp" %>
    <script type="text/javascript" src="js/mxApplication.js"></script>

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
        mxConstraintHandler.prototype.pointImage = new mxImage('images/dot.gif', 10, 10);

        // Enables guides
        mxGraphHandler.prototype.guidesEnabled = true;

        // Alt disables guides
        mxGuide.prototype.isEnabledForEvent = function(evt)
        {
            return !mxEvent.isAltDown(evt);
        };

        // Enables snapping waypoints to terminals
        mxEdgeHandler.prototype.snapToTerminals = true;

        main(document.getElementById('graph'));
        function main(container)
        {
            var graph = new mxGraph(container);
            graph.view.scale = 1;
            graph.setPanning(true);
            graph.setConnectable(true);
            graph.setConnectableEdges(true);
            graph.setDisconnectOnMove(false);
            graph.foldingEnabled = false;

            //Maximum size
            graph.maximumGraphBounds = new mxRectangle(0, 0, 800, 600)
            graph.border = 50;

            // Panning handler consumed right click so this must be
            // disabled if right click should stop connection handler.
            graph.panningHandler.isPopupTrigger = function() { return false; };

            // Enables return key to stop editing (use shift-enter for newlines)
            graph.setEnterStopsCellEditing(true);

            // Adds rubberband selection
            new mxRubberband(graph);

            // Alternative solution for implementing connection points without child cells.
            // This can be extended as shown in portrefs.html example to allow for per-port
            // incoming/outgoing direction.
            graph.getAllConnectionConstraints = function(terminal)
            {
                var geo = (terminal != null) ? this.getCellGeometry(terminal.cell) : null;

                if ((geo != null ? !geo.relative : false) &&
                    this.getModel().isVertex(terminal.cell) &&
                    this.getModel().getChildCount(terminal.cell) == 0)
                {
                    return [new mxConnectionConstraint(new mxPoint(0, 0.5), false),
                        new mxConnectionConstraint(new mxPoint(1, 0.5), false)];
                }

                return null;
            };

            // Makes sure non-relative cells can only be connected via constraints
            graph.connectionHandler.isConnectableCell = function(cell)
            {
                if (this.graph.getModel().isEdge(cell))
                {
                    return true;
                }
                else
                {
                    var geo = (cell != null) ? this.graph.getCellGeometry(cell) : null;

                    return (geo != null) ? geo.relative : false;
                }
            };
            mxEdgeHandler.prototype.isConnectableCell = function(cell)
            {
                return graph.connectionHandler.isConnectableCell(cell);
            };

            // Adds a special tooltip for edges
            graph.setTooltips(true);

            var getTooltipForCell = graph.getTooltipForCell;
            graph.getTooltipForCell = function(cell)
            {
                var tip = '';

                if (cell != null)
                {
                    var src = this.getModel().getTerminal(cell, true);

                    if (src != null)
                    {
                        tip += this.getTooltipForCell(src) + ' ';
                    }

                    var parent = this.getModel().getParent(cell);

                    if (this.getModel().isVertex(parent))
                    {
                        tip += this.getTooltipForCell(parent) + '.';
                    }

                    tip += getTooltipForCell.apply(this, arguments);

                    var trg = this.getModel().getTerminal(cell, false);

                    if (trg != null)
                    {
                        tip += ' ' + this.getTooltipForCell(trg);
                    }
                }

                return tip;
            };


            document.body.appendChild(mxUtils.button('Zoom In', function()
            {
                graph.zoomIn();
            }));

            document.body.appendChild(mxUtils.button('Zoom Out', function()
            {
                graph.zoomOut();
            }));

            // Undo/redo
            var undoManager = new mxUndoManager();
            var listener = function(sender, evt)
            {
                undoManager.undoableEditHappened(evt.getProperty('edit'));
            };
            graph.getModel().addListener(mxEvent.UNDO, listener);
            graph.getView().addListener(mxEvent.UNDO, listener);

            document.body.appendChild(mxUtils.button('Undo', function()
            {
                undoManager.undo();
            }));

            document.body.appendChild(mxUtils.button('Redo', function()
            {
                undoManager.redo();
            }));

            // Shows XML for debugging the actual model
            document.body.appendChild(mxUtils.button('Delete', function()
            {
                graph.removeCells();
            }));

            // Wire-mode
            var checkbox = document.createElement('input');
            checkbox.setAttribute('type', 'checkbox');

            document.body.appendChild(checkbox);
            mxUtils.write(document.body, 'Wire Mode');

            // Starts connections on the background in wire-mode
            var connectionHandlerIsStartEvent = graph.connectionHandler.isStartEvent;
            graph.connectionHandler.isStartEvent = function(me)
            {
                return checkbox.checked || connectionHandlerIsStartEvent.apply(this, arguments);
            };

            // Avoids any connections for gestures within tolerance except when in wire-mode
            // or when over a port
            var connectionHandlerMouseUp = graph.connectionHandler.mouseUp;
            graph.connectionHandler.mouseUp = function(sender, me)
            {
                if (this.first != null && this.previous != null)
                {
                    var point = mxUtils.convertPoint(this.graph.container, me.getX(), me.getY());
                    var dx = Math.abs(point.x - this.first.x);
                    var dy = Math.abs(point.y - this.first.y);

                    if (dx < this.graph.tolerance && dy < this.graph.tolerance)
                    {
                        // Selects edges in non-wire mode for single clicks, but starts
                        // connecting for non-edges regardless of wire-mode
                        if (!checkbox.checked && this.graph.getModel().isEdge(this.previous.cell))
                        {
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

            mxEvent.addListener(checkbox2, 'click', function(evt)
            {
                if (checkbox2.checked)
                {
                    container.style.background = 'url(\'images/wires-grid.gif\')';
                }
                else
                {
                    container.style.background = '';
                }

                container.style.backgroundColor = (invert) ? 'black' : 'white';
            });

            mxEvent.disableContextMenu(container);
        };
    </script>
    <!--
            Updates connection points before the routing is called.
    -->
    <script type="text/javascript">
        // Computes the position of edge to edge connection points.
        mxGraphView.prototype.updateFixedTerminalPoint = function(edge, terminal, source, constraint)
        {
            var pt = null;

            if (constraint != null)
            {
                pt = this.graph.getConnectionPoint(terminal, constraint);
            }

            if (source)
            {
                edge.sourceSegment = null;
            }
            else
            {
                edge.targetSegment = null;
            }

            if (pt == null)
            {
                var s = this.scale;
                var tr = this.translate;
                var orig = edge.origin;
                var geo = this.graph.getCellGeometry(edge.cell);
                pt = geo.getTerminalPoint(source);

                // Computes edge-to-edge connection point
                if (pt != null)
                {
                    pt = new mxPoint(s * (tr.x + pt.x + orig.x),
                        s * (tr.y + pt.y + orig.y));

                    // Finds nearest segment on edge and computes intersection
                    if (terminal != null && terminal.absolutePoints != null)
                    {
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
                        if (horizontal)
                        {
                            pt.x = p0.x;
                            pt.y = Math.min(pt.y, Math.max(p0.y, pe.y));
                            pt.y = Math.max(pt.y, Math.min(p0.y, pe.y));
                        }
                        else
                        {
                            pt.y = p0.y;
                            pt.x = Math.min(pt.x, Math.max(p0.x, pe.x));
                            pt.x = Math.max(pt.x, Math.min(p0.x, pe.x));
                        }
                    }
                }
                // Computes constraint connection points on vertices and ports
                else if (terminal != null && terminal.cell.geometry.relative)
                {
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
		Updates connection points before the routing is called.
-->
    <script type="text/javascript">
        // Computes the position of edge to edge connection points.
        mxGraphView.prototype.updateFixedTerminalPoint = function(edge, terminal, source, constraint)
        {
            var pt = null;

            if (constraint != null)
            {
                pt = this.graph.getConnectionPoint(terminal, constraint);
            }

            if (source)
            {
                edge.sourceSegment = null;
            }
            else
            {
                edge.targetSegment = null;
            }

            if (pt == null)
            {
                var s = this.scale;
                var tr = this.translate;
                var orig = edge.origin;
                var geo = this.graph.getCellGeometry(edge.cell);
                pt = geo.getTerminalPoint(source);

                // Computes edge-to-edge connection point
                if (pt != null)
                {
                    pt = new mxPoint(s * (tr.x + pt.x + orig.x),
                        s * (tr.y + pt.y + orig.y));

                    // Finds nearest segment on edge and computes intersection
                    if (terminal != null && terminal.absolutePoints != null)
                    {
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
                        if (horizontal)
                        {
                            pt.x = p0.x;
                            pt.y = Math.min(pt.y, Math.max(p0.y, pe.y));
                            pt.y = Math.max(pt.y, Math.min(p0.y, pe.y));
                        }
                        else
                        {
                            pt.y = p0.y;
                            pt.x = Math.min(pt.x, Math.max(p0.x, pe.x));
                            pt.x = Math.max(pt.x, Math.min(p0.x, pe.x));
                        }
                    }
                }
                // Computes constraint connection points on vertices and ports
                else if (terminal != null && terminal.cell.geometry.relative)
                {
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
        mxConnectionHandler.prototype.createEdgeState = function(me)
        {
            var edge = this.graph.createEdge();

            if (this.sourceConstraint != null && this.previous != null)
            {
                edge.style = mxConstants.STYLE_EXIT_X+'='+this.sourceConstraint.point.x+';'+
                    mxConstants.STYLE_EXIT_Y+'='+this.sourceConstraint.point.y+';';
            }
            else if (this.graph.model.isEdge(me.getCell()))
            {
                var scale = this.graph.view.scale;
                var tr = this.graph.view.translate;
                var pt = new mxPoint(this.graph.snap(me.getGraphX() / scale) - tr.x,
                    this.graph.snap(me.getGraphY() / scale) - tr.y);
                edge.geometry.setTerminalPoint(pt, true);
            }

            return this.graph.view.createState(edge);
        };

        // Uses right mouse button to create edges on background (see also: lines 67 ff)
        mxConnectionHandler.prototype.isStopEvent = function(me)
        {
            return me.getState() != null || mxEvent.isRightMouseButton(me.getEvent());
        };

        // Updates target terminal point for edge-to-edge connections.
        mxConnectionHandlerUpdateCurrentState = mxConnectionHandler.prototype.updateCurrentState;
        mxConnectionHandler.prototype.updateCurrentState = function(me)
        {
            mxConnectionHandlerUpdateCurrentState.apply(this, arguments);

            if (this.edgeState != null)
            {
                this.edgeState.cell.geometry.setTerminalPoint(null, false);

                if (this.shape != null && this.currentState != null &&
                    this.currentState.view.graph.model.isEdge(this.currentState.cell))
                {
                    var scale = this.graph.view.scale;
                    var tr = this.graph.view.translate;
                    var pt = new mxPoint(this.graph.snap(me.getGraphX() / scale) - tr.x,
                        this.graph.snap(me.getGraphY() / scale) - tr.y);
                    this.edgeState.cell.geometry.setTerminalPoint(pt, false);
                }
            }
        };

        // Updates the terminal and control points in the cloned preview.
        mxEdgeSegmentHandler.prototype.clonePreviewState = function(point, terminal)
        {
            var clone = mxEdgeHandler.prototype.clonePreviewState.apply(this, arguments);
            clone.cell = clone.cell.clone();

            if (this.isSource || this.isTarget)
            {
                clone.cell.geometry = clone.cell.geometry.clone();

                // Sets the terminal point of an edge if we're moving one of the endpoints
                if (this.graph.getModel().isEdge(clone.cell))
                {
                    // TODO: Only set this if the target or source terminal is an edge
                    clone.cell.geometry.setTerminalPoint(point, this.isSource);
                }
                else
                {
                    clone.cell.geometry.setTerminalPoint(null, this.isSource);
                }
            }

            return clone;
        };

        var mxEdgeHandlerConnect = mxEdgeHandler.prototype.connect;
        mxEdgeHandler.prototype.connect = function(edge, terminal, isSource, isClone, me)
        {
            var result = null;
            var model = this.graph.getModel();
            var parent = model.getParent(edge);

            model.beginUpdate();
            try
            {
                result = mxEdgeHandlerConnect.apply(this, arguments);
                var geo = model.getGeometry(result);

                if (geo != null)
                {
                    geo = geo.clone();
                    var pt = null;

                    if (model.isEdge(terminal))
                    {
                        pt = this.abspoints[(this.isSource) ? 0 : this.abspoints.length - 1];
                        pt.x = pt.x / this.graph.view.scale - this.graph.view.translate.x;
                        pt.y = pt.y / this.graph.view.scale - this.graph.view.translate.y;

                        var pstate = this.graph.getView().getState(
                            this.graph.getModel().getParent(edge));

                        if (pstate != null)
                        {
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
            finally
            {
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
        mxConnectionHandler.prototype.createMarker = function()
        {
            var marker = mxConnectionHandlerCreateMarker.apply(this, arguments);

            // Uses complete area of cell for new connections (no hotspot)
            marker.intersects = function(state, evt)
            {
                return true;
            };

            // Adds in-place highlighting
            mxCellHighlightHighlight = mxCellHighlight.prototype.highlight;
            marker.highlight.highlight = function(state)
            {
                if (this.state != state)
                {
                    if (this.state != null)
                    {
                        this.state.style = this.lastStyle;

                        // Workaround for shape using current stroke width if no strokewidth defined
                        this.state.style['strokeWidth'] = this.state.style['strokeWidth'] || '1';
                        this.state.style['strokeColor'] = this.state.style['strokeColor'] || 'none';

                        if (this.state.shape != null)
                        {
                            this.state.view.graph.cellRenderer.configureShape(this.state);
                            this.state.shape.redraw();
                        }
                    }

                    if (state != null)
                    {
                        this.lastStyle = state.style;
                        state.style = mxUtils.clone(state.style);
                        state.style['strokeColor'] = '#00ff00';
                        state.style['strokeWidth'] = '3';

                        if (state.shape != null)
                        {
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
        mxEdgeHandler.prototype.createMarker = function()
        {
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
        mxGraph.prototype.getCellStyle = function(cell)
        {
            var style = mxGraphGetCellStyle.apply(this, arguments);

            if (style != null && this.model.isEdge(cell))
            {
                style = mxUtils.clone(style);

                if (this.model.isEdge(this.model.getTerminal(cell, true)))
                {
                    style['startArrow'] = 'oval';
                }

                if (this.model.isEdge(this.model.getTerminal(cell, false)))
                {
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
        function ResistorShape() { };
        ResistorShape.prototype = new mxCylinder();
        ResistorShape.prototype.constructor = ResistorShape;

        ResistorShape.prototype.redrawPath = function(path, x, y, w, h, isForeground)
        {
            var dx = w / 16;

            if (isForeground)
            {
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
        mxEdgeStyle.WireConnector = function(state, source, target, hints, result)
        {
            // Creates array of all way- and terminalpoints
            var pts = state.absolutePoints;
            var horizontal = true;
            var hint = null;

            // Gets the initial connection from the source terminal or edge
            if (source != null && state.view.graph.model.isEdge(source.cell))
            {
                horizontal = state.style['sourceConstraint'] == 'horizontal';
            }
            else if (source != null)
            {
                horizontal = source.style['portConstraint'] != 'vertical';

                // Checks the direction of the shape and rotates
                var direction = source.style[mxConstants.STYLE_DIRECTION];

                if (direction == 'north' || direction == 'south')
                {
                    horizontal = !horizontal;
                }
            }

            // Adds the first point
            // TODO: Should move along connected segment
            var pt = pts[0];

            if (pt == null && source != null)
            {
                pt = new mxPoint(state.view.getRoutingCenterX(source), state.view.getRoutingCenterY(source));
            }
            else if (pt != null)
            {
                pt = pt.clone();
            }

            var first = pt;

            // Adds the waypoints
            if (hints != null && hints.length > 0)
            {
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

                for (var i = 0; i < hints.length; i++)
                {
                    horizontal = !horizontal;
                    hint = state.view.transformControlPoint(state, hints[i]);

                    if (horizontal)
                    {
                        if (pt.y != hint.y)
                        {
                            pt.y = hint.y;
                            result.push(pt.clone());
                        }
                    }
                    else if (pt.x != hint.x)
                    {
                        pt.x = hint.x;
                        result.push(pt.clone());
                    }
                }
            }
            else
            {
                hint = pt;
            }

            // Adds the last point
            pt = pts[pts.length - 1];

            // TODO: Should move along connected segment
            if (pt == null && target != null)
            {
                pt = new mxPoint(state.view.getRoutingCenterX(target), state.view.getRoutingCenterY(target));
            }

            if (horizontal)
            {
                if (pt.y != hint.y && first.x != pt.x)
                {
                    result.push(new mxPoint(pt.x, hint.y));
                }
            }
            else if (pt.x != hint.x && first.y != pt.y)
            {
                result.push(new mxPoint(hint.x, pt.y));
            }
        };

        mxStyleRegistry.putValue('wireEdgeStyle', mxEdgeStyle.WireConnector);

        // This connector needs an mxEdgeSegmentHandler
        mxGraphCreateHandler = mxGraph.prototype.createHandler;
        mxGraph.prototype.createHandler = function(state)
        {
            var result = null;

            if (state != null)
            {
                if (this.model.isEdge(state.cell))
                {
                    var style = this.view.getEdgeStyle(state);

                    if (style == mxEdgeStyle.WireConnector)
                    {
                        return new mxEdgeSegmentHandler(state);
                    }
                }
            }

            return mxGraphCreateHandler.apply(this, arguments);
        };
    </script>
    <style type="text/css" media="screen">
        div.base {
            position: absolute;
            overflow: hidden;
            font-family: Arial;
            font-size: 8pt;
        }
        div.base#graph {
            border-style: solid;
            border-color: #F2F2F2;
            border-width: 1px;
            background: url('images/grid.gif');
        }
    </style>
</head>
<body onload="mxApplication('config/layouteditor.xml');">
<div id="graph" class="base">
    <!-- Graph Here -->
</div>
</body>
</html>
