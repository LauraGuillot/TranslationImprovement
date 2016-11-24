function submit() {

    var l1 = document.getElementById('l1').value;
    var l2 = document.getElementById('l2').value;

    if (l1 == l2) {
        document.getElementById('error').innerHTML = "Please select two different languages";
    } else {
        var form = document.createElement('form');
        form.method = "POST";
        form.action = "submitPlay.htm";

        var c1 = document.createElement('input');
        c1.type = "hidden";
        c1.name = "idco";
        c1.value = document.getElementById('idco').value;
        form.appendChild(c1);

        var c2 = document.createElement('input');
        c2.type = "hidden";
        c2.name = "l1";
        c2.value = l1;
        form.appendChild(c2);


        var c3 = document.createElement('input');
        c3.type = "hidden";
        c3.name = "l2";
        c3.value = l2;
        form.appendChild(c3);

        document.body.appendChild(form);
        form.submit();
    }
}


