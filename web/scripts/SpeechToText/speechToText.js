var client;
var request;
var n =0;
var key1 = "502f810df2094f319116242125d92e40";
var key2 = "dd981c84195f4589bdda19bf66d26145";

function getLanguage() {
    var l1 = document.getElementById('l1').value;

    switch (l1) {
        case "cn" :
            return "zh-CN";
            break;
        case "en" :
            return "en-GB";
            break;
        case "fr" :
            return "fr-FR";
            break;
        case "de" :
            return "de-DE";
            break;
        case "it" :
            return "it-IT";
            break;
        case "es" :
            return "es-ES";
            break;
        default:
            return "en-GB";
            break;
    }
}

function clearText() {
    document.getElementById("scriptTable").innerHTML = "";
}

function setText(text) {
    var tr = translate(text);
    addLine(text, tr);
}

function startRecognition() {

    clearText();

    var mode = Microsoft.ProjectOxford.SpeechRecognition.SpeechRecognitionMode.longDictation;

    client = Microsoft.ProjectOxford.SpeechRecognition.SpeechRecognitionServiceFactory.createMicrophoneClient(
            mode,
            getLanguage(),
            key1,
            key2);

    client.startMicAndRecognition();

    client.onPartialResponseReceived = function (response) {
        setText(response);
    };

    client.onFinalResponseReceived = function (response) {
        setText(JSON.stringify(response));
    };

    client.onIntentReceived = function (response) {
        setText(response);
    };
}

function endRecognition() {
    client.endMicAndRecognition();
}

function endRecognition2() {
    clickDiv(1);
    document.getElementById('endDiv1').style.display = "none";
    client.endMicAndRecognition();
    clearTimeout(x);
    clearTimeout(y);
}

var x;
var y;
/*Stop the microphone, send the answer and restart the microphone */
function boucle() {
    if (n == 0) {
        n++;
        startRecognition();
        x = setTimeout('boucle()', 2000);

    } else {
        endRecognition();
        y = setTimeout('startRecognition()', 1000);
        x = setTimeout('boucle()', 2000);
    }
}