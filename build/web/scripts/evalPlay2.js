/**
 * 
 * Creation of the topics cloud
 */
function topic() {
    var topicsSize = document.getElementById('nbTop').value;
    var list = [];
    for (i = 0; i < topicsSize; i++) {
        var t = {text: document.getElementById('top' + (i)).value, weight: document.getElementById('w' + (i)).value};
        list.push(t);
    }
    $('#cloud').empty();
    $('#cloud').jQCloud(list);
}

/*
 * Send the vote to the controller 
 * @param {int} vote
 */
function next(vote) {

    //Send data
    var form = document.createElement('form');
    form.method = "POST";
    form.action = "evalPlay2.htm";

    var c1 = document.createElement('input');
    c1.type = "hidden";
    c1.name = "idco";
    c1.value = document.getElementById('idco').value;
    form.appendChild(c1);

    var c2 = document.createElement('input');
    c2.type = "hidden";
    c2.name = "l1";
    c2.value = document.getElementById('l1').value;
    form.appendChild(c2);

    var c3 = document.createElement('input');
    c3.type = "hidden";
    c3.name = "l2";
    c3.value = document.getElementById('l2').value;
    form.appendChild(c3);

    var c4 = document.createElement('input');
    c4.type = "hidden";
    c4.name = "vote";
    c4.value = vote;
    form.appendChild(c4);

    var c5 = document.createElement('input');
    c5.type = "hidden";
    c5.name = "sentenceID";
    c5.value = document.getElementById('sentID').value;
    form.appendChild(c5);

    var c6 = document.createElement('input');
    c6.type = "hidden";
    c6.name = "n";
    c6.value = document.getElementById('n').value;
    form.appendChild(c6);

    document.body.appendChild(form);
    form.submit();

}

/*Change the background on mouse over*/
function tableover(t) {
    t.style.backgroundColor = "#A3E3E5";
}

function tableout(t) {
    t.style.backgroundColor = "#DFE1E2";
}
