/**
 * Created by VM on 1/26/2017.
 */
$(document).ready(function () {
    $("#dTree").tree({
        url: "data/physicalTree.json",
        method: "get",
        loadFilter: function (data) {
            if (data.d) {
                return data.d;
            } else {
                return data;
            }
        }
    });
});