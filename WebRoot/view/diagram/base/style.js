/**
 * Created by Administrator on 2017/1/19.
 */
$(document).ready(function () {
    mxConstants.SHADOWCOLOR = "#C0C0C0";
    var joinNodeSize = 7;
    var strokeWidth = 2;
    // Switch for black background and bright styles
    var invert = false;

    if (invert) {
        container.style.backgroundColor = "black";
        // White in-place editor text color
        mxCellEditorStartEditing = mxCellEditor.prototype.startEditing;
        mxCellEditor.prototype.startEditing = function (cell, trigger) {
            mxCellEditorStartEditing.apply(this, arguments);

            if (this.textarea != null) {
                this.textarea.style.color = "#FFFFFF";
            }
        };

        mxGraphHandler.prototype.previewColor = "white";
    }

    var labelBackground = (invert) ? "#000000" : "#FFFFFF";
    var fontColor = (invert) ? "#FFFFFF" : "#000000";
    var strokeColor = (invert) ? "#C0C0C0" : "#000000";
    var fillColor = (invert) ? "none" : "#FFFFFF";

    var style = graph.getStylesheet().getDefaultEdgeStyle();
    delete style["endArrow"];
    style["strokeColor"] = strokeColor;
    style["labelBackgroundColor"] = labelBackground;
    style["edgeStyle"] = "wireEdgeStyle";
    style["fontColor"] = fontColor;
    style["fontSize"] = "9";
    style["movable"] = "0";
    style["strokeWidth"] = strokeWidth;
    //style["rounded"] = "1";

    // Sets join node size
    style["startSize"] = joinNodeSize;
    style["endSize"] = joinNodeSize;

    style = graph.getStylesheet().getDefaultVertexStyle();
    style["gradientDirection"] = "south";
    //style["gradientColor"] = "#909090";
    style["strokeColor"] = strokeColor;
    //style["fillColor"] = "#e0e0e0";
    style["fillColor"] = "none";
    style["fontColor"] = fontColor;
    style["fontStyle"] = mxConstants.FONT_BOLD;
    style["fontSize"] = "12";
    style["resizable"] = "0";
    //style["rounded"] = "1";
    style["strokeWidth"] = strokeWidth;
});