/**
 * 
 * Function to create the cloud of topics
 * @returns {void}
 */
function loadtopics() {
    var topicsSize = document.getElementById('sent' + cpt).value;
    var list = [];
    for (i = 0; i < topicsSize; i++) {
        var t = {text: document.getElementById('top' + cpt + '' + (i)).value, weight: document.getElementById('w' + cpt + '' + (i)).value};
        list.push(t);
    }
    $('#cloud').empty();
    $('#cloud').jQCloud(list);
}


