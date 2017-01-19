/**
 * Created by barrie on 17/1/16.
 */
$(document).ready(function () {
    // Note that these XML nodes will be enclosing the
    // mxCell nodes for the model cells in the output
    var doc = mxUtils.createXmlDocument();
    threePhaseCurrent = doc.createElement('ThreePhaseCurrent');
    threePhaseCurrent.setAttribute('L', '0');
    threePhaseCurrent.setAttribute('N', '0');
    threePhaseCurrent.setAttribute('G', '0');

    twoPhaseCurrent = doc.createElement('TwoPhaseCurrent');
    twoPhaseCurrent.setAttribute('L', '0');
    twoPhaseCurrent.setAttribute('G', '0');


    // Overrides method to provide a cell label in the display
    graph.convertValueToString = function (cell) {
        if (mxUtils.isNode(cell.value)) {
            if (cell.value.nodeName == 'ThreePhaseCurrent') {
                var L = cell.getAttribute('L', '');
                var N = cell.getAttribute('N', '');
                var G = cell.getAttribute('G', '');
                return '<p style="color: red">' + '<span class="L">' + L + '</span>' + ' (A)</p>' + '<p style="color: green">' + '<span class="N">' + N + '</span>' + ' (A)</p>' + '<p style="color: orange">' + '<span class="G">' + G + '</span>' + ' (A)</p>';
            }
            if (cell.value.nodeName == 'TwoPhaseCurrent') {
                var L = cell.getAttribute('L', '');
                var G = cell.getAttribute('G', '');
                return '<p style="color: red">' + '<span class="L">' + L + '</span>' + ' (A)</p>' + '<p style="color: orange">' + '<span class="G">' + G + '</span>' + ' (A)</p>';
            }
        }

        return cell.value;
    };

    // Overrides method to store a cell label in the model
    var cellLabelChanged = graph.cellLabelChanged;
    graph.cellLabelChanged = function (cell, newValue, autoSize) {
        if (mxUtils.isNode(cell.value)) {
            if (cell.value.nodeName == 'ThreePhaseCurrent') {
                var L = $(newValue).find("L").text();
                var N = $(newValue).find("N").text();
                var G = $(newValue).find("G").text();
                // Clones the value for correct undo/redo
                var elt = cell.value.cloneNode(true);

                elt.setAttribute('L', L);
                elt.setAttribute('N', N);
                elt.setAttribute('G', G);
            }
            if (cell.value.nodeName == 'TwoPhaseCurrent') {
                var L = $(newValue).find("L").text();
                var G = $(newValue).find("G").text();
                // Clones the value for correct undo/redo
                var elt = cell.value.cloneNode(true);

                elt.setAttribute('L', L);
                elt.setAttribute('G', G);
            }
        }

        cellLabelChanged.apply(this, arguments);
    };


    // Overrides method to create the editing value
    var getEditingValue = graph.getEditingValue;
    graph.getEditingValue = function (cell) {
        if (mxUtils.isNode(cell.value)) {
            if (cell.value.nodeName == 'ThreePhaseCurrent') {
                var L = cell.getAttribute('L', '');
                var N = cell.getAttribute('N', '');
                var G = cell.getAttribute('G', '');
                return '<p style="color: red">' + '<span class="L">' + L + '</span>' + ' (A)</p>' + '<p style="color: green">' + '<span class="N">' + N + '</span>' + ' (A)</p>' + '<p style="color: orange">' + '<span class="G">' + G + '</span>' + ' (A)</p>';
            }
            if (cell.value.nodeName == 'TwoPhaseCurrent') {
                var L = cell.getAttribute('L', '');
                var G = cell.getAttribute('G', '');
                return '<p style="color: red">' + '<span class="L">' + L + '</span>' + ' (A)</p>' + '<p style="color: orange">' + '<span class="G">' + G + '</span>' + ' (A)</p>';
            }
        }
    };


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


});