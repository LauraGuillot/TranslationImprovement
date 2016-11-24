function display() {
    displayStep();
    displayTrophies();
    displayMissions();
}

/*Display the trohpies*/
function displayTrophies(){
    for(i=1;i<10;i++){
        if(document.getElementById("hast"+i).value=="1"){
            displayThisTrophy(i);
        }
    }
}

/*Display a trophy*/
function displayThisTrophy(n){
     switch (n) {
        case 1: document.getElementById("t1").style.backgroundImage = "url('./images/trophies/1/trophy1b.png')";
            break;
        case 2: document.getElementById("t2").style.backgroundImage = "url('./images/trophies/1/trophy1o.png')";
            break;
        case 3: document.getElementById("t3").style.backgroundImage = "url('./images/trophies/1/trophy1y.png')";
            break;
        case 4: document.getElementById("t4").style.backgroundImage = "url('./images/trophies/2/trophy2b.png')";
            break;
        case 5: document.getElementById("t5").style.backgroundImage = "url('./images/trophies/2/trophy2o.png')";
            break;
        case 6: document.getElementById("t6").style.backgroundImage = "url('./images/trophies/2/trophy2y.png')";
            break;
        case 7: document.getElementById("t7").style.backgroundImage = "url('./images/trophies/3/trophy3b.png')";
            break;
        case 8: document.getElementById("t8").style.backgroundImage = "url('./images/trophies/3/trophy3o.png')";
            break;
        case 9: document.getElementById("t9").style.backgroundImage = "url('./images/trophies/3/trophy3y.png')";
            break;
    }
}

