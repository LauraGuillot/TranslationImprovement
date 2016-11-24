/*Display the legend of the trophy*/
function legend(n) {
    switch (n) {
        case 1: document.getElementById("legendTrophy").innerHTML = "Be in the top 50";
            break;
        case 2: document.getElementById("legendTrophy").innerHTML = "Be in the top 20";
            break;
        case 3: document.getElementById("legendTrophy").innerHTML = "Be in the top 5";
            break;
        case 4: document.getElementById("legendTrophy").innerHTML = "Evaluate 50 translations";
            break;
        case 5: document.getElementById("legendTrophy").innerHTML = "Evaluate 100 translations";
            break;
        case 6: document.getElementById("legendTrophy").innerHTML = "Evaluate 500 translations";
            break;
        case 7: document.getElementById("legendTrophy").innerHTML = "Submit 10 translations";
            break;
        case 8: document.getElementById("legendTrophy").innerHTML = "Submit 25 translations";
            break;
        case 9: document.getElementById("legendTrophy").innerHTML = "Submit 50 translations";
            break;
    }
}

/*remove the legend*/
function removeLegend(){
    document.getElementById("legendTrophy").innerHTML = "";
}