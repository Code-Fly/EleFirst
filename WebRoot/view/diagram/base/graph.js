/**
 * Created by Administrator on 2017/1/19.
 */
$(document).ready(function () {
    var container = document.getElementById("graphContainer");

    if (mxClient.IS_QUIRKS) {
        document.body.style.overflow = "hidden";
    }
    if (mxClient.IS_QUIRKS) {
        new mxDivResizer(container);
    }

    editor = new mxEditor();
    graph = editor.graph;

    // Sets the graph container and configures the editor
    editor.setGraphContainer(container);

    // Configures the automatic layout for the table columns
    editor.layoutSwimlanes = true;
    editor.createSwimlaneLayout = function () {
        var layout = new mxStackLayout(this.graph, false);
        layout.fill = true;
        layout.resizeParent = true;

        // Overrides the function to always return true
        layout.isVertexMovable = function (cell) {
            return true;
        };

        return layout;
    };

    // var graph = new mxGraph(container);
    graph.view.scale = 1;
    graph.setPanning(true);
    graph.setConnectable(true);
    graph.setConnectableEdges(true);
    graph.setDisconnectOnMove(false);
    graph.foldingEnabled = false;

    graph.isHtmlLabel = function (cell) {
        return true;
    };

    // Enables new connections in the graph
    graph.setConnectable(true);
    graph.setMultigraph(false);

    //Maximum size
    // graph.maximumGraphBounds = new mxRectangle(0, 0, 800, 600)
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

    // If connect preview is not moved away then getCellAt is used to detect the cell under
    // the mouse if the mouse is over the preview shape in IE (no event transparency), ie.
    // the built-in hit-detection of the HTML document will not be used in this case.
    mxConnectionHandler.prototype.movePreviewAway = false;
    mxConnectionHandler.prototype.waypointsEnabled = true;
    mxGraph.prototype.resetEdgesOnConnect = false;

    // Replaces the port image
    mxConstraintHandler.prototype.pointImage = new mxImage(_ctx + "Content/images/graph/dot.gif", 10, 10);

    // Enables guides
    mxGraphHandler.prototype.guidesEnabled = true;

    // Alt disables guides
    mxGuide.prototype.isEnabledForEvent = function (evt) {
        return !mxEvent.isAltDown(evt);
    };

    // Enables snapping waypoints to terminals
    mxEdgeHandler.prototype.snapToTerminals = true;
});