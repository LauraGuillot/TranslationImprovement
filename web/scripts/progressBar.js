
/**
 * FUNCTION displayStep
 * This function is used for displaying the step of the player according to his points
 */
function displayStep() {
    var pts = document.getElementById('pts').value;
    pts = Number(pts);

    if (pts < 100) {
        document.getElementById('step0').className = "active";
        document.getElementById("step0").classList.remove('step');
    }
    if (pts >= 100 && pts < 200) {
        document.getElementById('step1').className = "active";
        document.getElementById("step1").classList.remove('step');
    }
    if (pts >= 200 && pts < 500) {
        document.getElementById('step2').className = "active";
        document.getElementById("step2").classList.remove('step');
    }
    if (pts >= 500 && pts < 1000) {
        document.getElementById('step3').className = "active";
        document.getElementById("step3").classList.remove('step');
    }
    if (pts >= 1000) {
        document.getElementById('step4').className = "active";
        document.getElementById("step4").classList.remove('step');
    }

    var percent = pts * 100 / 1000;

    if (percent > 100) {
        percent = 100;
    }

    percent = percent + "%";
    document.styleSheets[2].cssRules[10].style.width = percent;
}

/**
 * FUNCTION displayText
 * @param {type} id
 * When a player put is mouse over a step, we display a legend : the name of the step and the number of points
 */
function displayText(id) {
    switch (id) {
        case '0':
            document.getElementById('stepTxt').innerHTML = "Beginner (0 pts)";
            break;
        case '1':
            document.getElementById('stepTxt').innerHTML = "Trainee (100 pts)";
            break;
        case '2':
            document.getElementById('stepTxt').innerHTML = "Experienced (200 pts)";
            break;
        case '3':
            document.getElementById('stepTxt').innerHTML = "Master (500 pts)";
            break;
        case '4':
            document.getElementById('stepTxt').innerHTML = "Expert (1000 pts)";
            break;
    }
    document.getElementById('stepTxt').style.display = "block";
}

/**
 * FUNCTION removeText
 * When a player put is mouse out of a step, we remove the legend.
 */
function removeText() {
    document.getElementById('stepTxt').style.display = "none";
}


