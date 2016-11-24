function page2() {
    document.getElementById("page2").style.display = "block";
    document.getElementById("page1").style.display = "none";
    boucle();
}

function tableover(t) {
    t.style.backgroundColor = "#A3E3E5";
}

function tableout(t) {
    t.style.backgroundColor = "#DFE1E2";
}

function select(t) {
    var c = t.getElementsByTagName('font');
    var s = c[0];
    var tr = c[1];

    document.getElementById('sentence').value = s.innerHTML;
    document.getElementById('tr1').value = tr.innerHTML;
}

var sentences = "";
var tr1 = "";
var tr2 = "";
var cpt = 0;
var script = "";

function nextsub() {
    var s = document.getElementById('sentence').value;
    var t1 = document.getElementById('tr1').value;
    var t2 = document.getElementById('tr2').value;

    if (s != "" && t1 != "" && t2 != "") {

        document.getElementById('comment').style.color = "#429D09";
        document.getElementById('comment').innerHTML = "Comment registered!";

        sentences = sentences + "|" + s;
        tr1 = tr1 + "|" + t1;
        tr2 = tr2 + "|" + t2;
        cpt++;

    } else {
        document.getElementById('comment').style.color = "#AB0627";
        document.getElementById('comment').innerHTML = "Please, fill in all the fields";
    }
}


function end() {
    endRecognition();
    clickDiv(2);
    var form = document.createElement('form');
    form.method = "POST";
    form.action = "submitEnd.htm";

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
    c4.name = "sentences";
    c4.value = sentences;
    form.appendChild(c4);

    var c5 = document.createElement('input');
    c5.type = "hidden";
    c5.name = "tr1";
    c5.value = tr1;
    form.appendChild(c5);

    var c6 = document.createElement('input');
    c6.type = "hidden";
    c6.name = "tr2";
    c6.value = tr2;
    form.appendChild(c6);

    var c7 = document.createElement('input');
    c7.type = "hidden";
    c7.name = "cpt";
    c7.value = cpt;
    form.appendChild(c7);

    var c8 = document.createElement('input');
    c8.type = "hidden";
    c8.name = "script";
    getScript();
    c8.value = script;
    form.appendChild(c8);

    document.body.appendChild(form);

    form.submit();
}

function getScript() {
    var l = document.getElementsByClassName('td');

    for (i = 0; i < l.length; i++) {
        var children = l[i].getElementsByTagName('font');
        var child = children[0];
        script = script + child.innerHTML + " ";
    }
}

function addLine(s, tr) {
    var table = document.getElementById("scriptTable");

    var tr1 = document.createElement("tr");

    var td = document.createElement("td");
    td.className = "td";

    td.addEventListener('mouseover', function () {
        tableover(this);
    });

    td.addEventListener('mouseout', function () {
        tableout(this);
    });
    td.addEventListener('click', function () {
        select(this);
    });

    var f1 = document.createElement("font");
    f1.className = "s";
    f1.innerHTML = s;

    var br = document.createElement("br");

    var f2 = document.createElement("font");
    f2.className = "tr";
    f2.innerHTML = tr;


    td.appendChild(f1);
    td.appendChild(br);
    td.appendChild(f2);
    tr1.appendChild(td);
    table.appendChild(tr1);
}

function overDiv(n) {
    var button = document.getElementById('end' + n);
    button.style.backgroundImage = "url('images/buttons/crossOver.png')";
}

function outDiv(n) {
    var button = document.getElementById('end' + n);
    button.style.backgroundImage = "url('images/buttons/cross.png')";
}

function clickDiv(n) {
    var button = document.getElementById('end' + n);
    button.style.backgroundImage = "url('images/buttons/crossActive.png')";
}