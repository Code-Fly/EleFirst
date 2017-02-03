/**
 * Created by barrie on 17/2/4.
 */
$(document).ready(function () {

    // Overrides method to disallow edge label editing
    graph.isCellEditable = function (cell) {
        // return !this.getModel().isEdge(cell);
        var style = cell.getStyle();
        if (graphUtils.isValidStyle(style, graphConstants.USER_OBJECT_TEXT)) {
            return true;
        }
        return false;
    };

    var style = new Object();
    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
    style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
    style[mxConstants.STYLE_FONTCOLOR] = "#000000";
    graph.getStylesheet().putCellStyle(graphConstants.USER_OBJECT_CUSTOM_IMG, style);

    var style = new Object();
    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RECTANGLE;
    style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
    style[mxConstants.STYLE_FILLCOLOR] = "none";
    style[mxConstants.STYLE_FONTCOLOR] = "#000000";
    style[mxConstants.STYLE_FONTSTYLE] = mxConstants.FONT_BOLD;
    graph.getStylesheet().putCellStyle(graphConstants.USER_OBJECT_SWITCH_STATUS, style);

    style = new Object();
    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RECTANGLE;
    style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
    style[mxConstants.STYLE_FONTCOLOR] = "#000000";
    style[mxConstants.STYLE_FONTSTYLE] = "";
    style[mxConstants.STYLE_STROKECOLOR] = "none";
    style[mxConstants.STYLE_FILLCOLOR] = "none";
    style[mxConstants.STYLE_GRADIENTCOLOR] = "none";
    graph.getStylesheet().putCellStyle(graphConstants.USER_OBJECT_TEXT, style);

    style = new Object();
    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RECTANGLE;
    style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
    style[mxConstants.STYLE_FONTCOLOR] = "#000000";
    style[mxConstants.STYLE_STROKECOLOR] = "none";
    style[mxConstants.STYLE_FILLCOLOR] = "none";
    style[mxConstants.STYLE_GRADIENTCOLOR] = "none";
    style[mxConstants.STYLE_ALIGN] = "right";
    graph.getStylesheet().putCellStyle(graphConstants.USER_OBJECT_CURRENT, style);

});