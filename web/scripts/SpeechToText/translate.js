function transl(s) {

    var l1 = document.getElementById("l1").value;
    var l2 = document.getElementById("l2").value;

    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var tr = xhttp.responseText;
            alert(tr);
            return tr;
        }
    };
    xhttp.open("GET", "myServlet?" + "l1=" + l1 + "&l2=" + l2 + "&s=" + s, true);
   xhttp.setRequestHeader("Content-Type","text/html; charset=UTF-8");
    xhttp.send();



}


