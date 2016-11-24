/*
 * When the page is loading, we display a graph which reprensents 
 * how many votes the player has made in agreement with the majority and how many
 * in disagreement to the majority
 */

window.onload = function () {

    CanvasJS.addColorSet("customColorSet1",
            [//colorSet Array
                "#0fbbc1",
                "#ffc202"
            ]);

    var chart = new CanvasJS.Chart("stat",
            {
                animationEnabled: true,
                colorSet: "customColorSet1",
                labelFontFamilly: "Segoe UI Light",
                backgroundColor: "transparent",
                data: [
                    {
                        type: "doughnut",
                        startAngle: 20,
                        toolTipContent: "{label}: {y}",
                        indexLabel: "{label}: {y}",
                        dataPoints: [
                            {y: document.getElementById('majo').value, label: "Vote in agreement with the majority"},
                            {y: document.getElementById('nbVote').value - document.getElementById('majo').value, label: "Vote in disagreement with the majority"}
                        ]
                    }
                ]
            });
    chart.render();
}