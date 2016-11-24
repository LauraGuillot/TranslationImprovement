//Counter for the number of evaluated sentences
var cpt = 1;

//Array in which we will register the votes
// A vote is a pair (id,vote) : id is the sentenceID and vote is the id of the translation chosen
var votes = new Array();

/*
 * FUNCTION next
 * PARAMETER : character which represent a vote
 * This function aims to register a vote and display the next sentence
 */
function next() {

//Retrieve the vote
    var vote = -1;
    var nbTr = document.getElementById('nbTr' + cpt).value;
    for (j = 0; j < nbTr; j++) {
        if (document.getElementById('tr' + j).checked == true) {
            vote = document.getElementById('trid' + cpt + j).value;
        }
    }

    if (vote == -1) {
        var txt = document.getElementById('newTr').value;
        if (txt.length > 3) {
            vote = txt;
        }
    }

    if (vote == -1) {
        document.getElementById('error').innerHTML = 'Please, select one translation or add your own to the list';
    } else {
        if (cpt < 10) {

            //Save the current vote
            var id = document.getElementById('id' + cpt).value;
            var v = {id: id, vote: vote};
            votes.push(v);

            cpt++;

            //Next sentence
            document.getElementById('cpt').innerHTML = cpt + "/10";
            document.getElementById('sentenceBody').innerHTML = document.getElementById('s' + cpt).value;

            document.getElementById('error').innerHTML = "";

            //Next translations
            document.getElementById('tr').innerHTML = "";

            var table = document.createElement("table");
            var nbTr = document.getElementById('nbTr' + cpt).value;

            for (var j = 0; j < nbTr; j++) {
                var tr = document.createElement("tr");
                var td1 = document.createElement("td");
                var td2 = document.createElement("td");

                td1.innerHTML = document.getElementById('tr' + cpt + j).value;

                var content2 = document.createElement("input");
                content2.type = "checkbox";
                content2.id = "tr" + j.toString();

                content2.addEventListener('click', function () {
                    check(this);
                });

                tr.addEventListener('mouseover', function () {
                    tableover(this);
                });

                tr.addEventListener('mouseout', function () {
                    tableout(this);
                });

                td2.appendChild(content2);
                tr.appendChild(td1);
                tr.appendChild(td2);
                table.appendChild(tr);
            }

            document.getElementById('tr').innerHTML = "";
            document.getElementById('tr').appendChild(table);

            //Clean the text area
            document.getElementById('newTr').value = "";

            //Next topics
            loadtopics();

        } else {
            //If the player has just voted for the sentence number 10, we register his vote and send the data to the controller to add them in the database

            //Save the vote
            var id = document.getElementById('id' + cpt).value;
            var v = {id: id, vote: vote};
            votes.push(v);

            //Send data
            var form = document.createElement('form');
            form.method = "POST";
            form.action = "evalPlay1End.htm";


            var c1 = document.createElement('input');
            c1.type = "hidden";
            c1.name = "idco";
            c1.value = document.getElementById('idco').value;
            form.appendChild(c1);

            for (i = 1; i < 11; i++) {

                var c1 = document.createElement('input');
                c1.type = "hidden";
                c1.name = "sid" + i;
                c1.value = votes[i - 1].id;
                form.appendChild(c1);

                var c2 = document.createElement('input');
                c2.type = "hidden";
                c2.name = "vote" + i;
                c2.value = votes[i - 1].vote;
                form.appendChild(c2);
            }

            document.body.appendChild(form);
            form.submit();
        }
    }
}



function check(t) {
    if (!t.checked) {
        t.checked = false;
    } else {
        document.getElementById('newTr').value = "";
        var l = document.getElementsByTagName('input');
        for (var i = 0, iMax = l.length; i < iMax; ++i) {
            var check = l[i];
            check.checked = false;
        }
        t.checked = true;
    }
}

function tableover(t) {
    t.style.backgroundColor = "#A3E3E5";
}

function tableout(t) {
    t.style.backgroundColor = "#DFE1E2";
}

function decoche() {
    var l = document.getElementsByTagName('input');
    for (var i = 0, iMax = l.length; i < iMax; ++i) {
        var check = l[i];
        check.checked = false;
    }
}