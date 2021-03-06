//Array to register the topics (their id) selected by the player
var topics = [];

//Counter for the number of topics selected by the player
var cpt = 0;

/**
 * FUNCTION takeTopic
 * This function retrieves the topics selected by the player and register them in the array
 */
function takeTopics() {
    var nb = document.getElementById('nbTopic').value;
    nb = Number(nb);

    for (i = 0; i < nb; i++) {
        if (document.getElementById('topic' + i).checked == true) {
            topics.push(document.getElementById('topic' + i).value);
            cpt++;
        }
    }
}

/**
 * FUNCTION send
 * This function send the topics chosen by the player to a controller in order to register them in the database
 */
function send() {

    takeTopics();

    //If the player has selected 0 topic, an error is displayed
    if (cpt == 0) {
        document.getElementById("errorTopic").style.display = "block";
    } else {
        var form = document.createElement('form');
        form.method = "POST";
        form.action = "home4.htm";

        //Add the player informations
        var champCache1 = document.createElement('input');
        champCache1.type = "hidden";
        champCache1.name = "playerName";
        champCache1.value = document.getElementById('playerName').value;
        form.appendChild(champCache1);

        //Add the topics
        var champCache2 = document.createElement('input');
        champCache2.type = "hidden";
        champCache2.name = "topics";
        champCache2.value = topics;
        form.appendChild(champCache2);

        //Send the form
        document.body.appendChild(form);
        form.submit()
    }
}

/**********************************************
 *  Displaying functions
***********************************************/

/**
 * FUNCTION isChecked
 * @param {int} id - id of a topic
 * This function changes the background of the selected topics
 */
function isChecked(id) {
    if (document.getElementById(id).checked == true) {
        document.getElementsByClassName(id)[0].style.backgroundColor = "#D7F58C";
    } else {
        document.getElementsByClassName(id)[0].style.backgroundColor = "transparent";
    }
}

/**
 * FUNCTION over
 * @param {int} id - id of a topic
 * This function changes the background of the topic which has the mouse over
 */
function over(id) {
    document.getElementsByClassName(id)[0].style.backgroundColor = "#A3E3E5";
}

/**
 * FUNCTION out
 * @param {int} id - id of a topic
 * This function changes the background of the topic which has the mouse out
 */
function out(id) {
    if (document.getElementById(id).checked == true) {
        document.getElementsByClassName(id)[0].style.backgroundColor = "#D7F58C";
    } else {
        document.getElementsByClassName(id)[0].style.backgroundColor = "transparent";
    }
}
