/**
 * Created by barrie on 17/1/17.
 */
$(document).ready(function () {
    // <!--
    //     Updates connection points before the routing is called.
    // -->
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
                    var key = (source) ? "sourceConstraint" : "targetConstraint";
                    var value = (horizontal) ? "horizontal" : "vertical";
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
    // <!--
    //     Overrides methods to preview and create new edges.
    // -->
    // Sets source terminal point for edge-to-edge connections.
    mxConnectionHandler.prototype.createEdgeState = function (me) {
        var edge = this.graph.createEdge();

        if (this.sourceConstraint != null && this.previous != null) {
            edge.style = mxConstants.STYLE_EXIT_X + "=" + this.sourceConstraint.point.x + ";" +
                mxConstants.STYLE_EXIT_Y + "=" + this.sourceConstraint.point.y + ";";
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
        // if(this.marker.highlight.hasOwnProperty("shape")){
        mxConnectionHandlerUpdateCurrentState.apply(this, arguments);
        // }

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

            // Sets the terminal point of an edge if we"re moving one of the endpoints
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
    // <!--
    //     Adds in-place highlighting for complete cell area (no hotspot).
    // -->
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
                    this.state.style["strokeWidth"] = this.state.style["strokeWidth"] || "1";
                    this.state.style["strokeColor"] = this.state.style["strokeColor"] || "none";

                    if (this.state.shape != null) {
                        this.state.view.graph.cellRenderer.configureShape(this.state);
                        this.state.shape.redraw();
                    }
                }

                if (state != null) {
                    this.lastStyle = state.style;
                    state.style = mxUtils.clone(state.style);
                    state.style["strokeColor"] = "#00ff00";
                    state.style["strokeWidth"] = "3";

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
    // <!--
    //     Adds oval markers for edge-to-edge connections.
    // -->
    mxGraphGetCellStyle = mxGraph.prototype.getCellStyle;
    mxGraph.prototype.getCellStyle = function (cell) {
        var style = mxGraphGetCellStyle.apply(this, arguments);

        if (style != null && this.model.isEdge(cell)) {
            style = mxUtils.clone(style);

            if (this.model.isEdge(this.model.getTerminal(cell, true))) {
                style["startArrow"] = "oval";
            }

            if (this.model.isEdge(this.model.getTerminal(cell, false))) {
                style["endArrow"] = "oval";
            }
        }

        return style;
    };
    // <!--
    //     Imlements a custom resistor shape. Direction currently ignored here.
    // -->
    function ResistorShape() {
    };
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

    mxCellRenderer.prototype.defaultShapes["resistor"] = ResistorShape;
    // <!--
    //     Imlements a custom resistor shape. Direction currently ignored here.
    // -->
    mxEdgeStyle.WireConnector = function (state, source, target, hints, result) {
        // Creates array of all way- and terminalpoints
        var pts = state.absolutePoints;
        var horizontal = true;
        var hint = null;

        // Gets the initial connection from the source terminal or edge
        if (source != null && state.view.graph.model.isEdge(source.cell)) {
            horizontal = state.style["sourceConstraint"] == "horizontal";
        }
        else if (source != null) {
            horizontal = source.style["portConstraint"] != "vertical";

            // Checks the direction of the shape and rotates
            var direction = source.style[mxConstants.STYLE_DIRECTION];

            if (direction == "north" || direction == "south") {
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
             mxLog.debug(hints.length,"hints0.y="+hint.y, pt.y)

             if (horizontal && Math.floor(hint.y) != Math.floor(pt.y))
             {
             mxLog.show();
             mxLog.debug("add waypoint");

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

    mxStyleRegistry.putValue("wireEdgeStyle", mxEdgeStyle.WireConnector);

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
});

