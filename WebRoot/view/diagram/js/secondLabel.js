/**
 * Created by barrie on 17/1/17.
 */
$(document).ready(function () {

});

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