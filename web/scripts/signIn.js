/**
 * FUNCTION signin
 * @param {type} options - Parameters for the connection with skype
 * This function is used to sign in the player with skype (if there is something wrong, an error is displayed)
 * and to send the fact that the player is connected to the database.
 */
function signin(options) {
    window.skypeWebApp.signInManager.signIn(options).then(function () {
    send();
    }, function (error) {
        // if something goes wrong in either of the steps above,
        // display the error message
        document.getElementById("error").innerHTML = "Can't sign in, please check the user name and password.";
        console.log(error || 'Cannot sign in');
    });
}

/**
 * FUNCTION send
 * This function send the playerName to the controller in order to create a connection in the database for him
 */
function send() {
    var form = document.createElement('form');
    form.method = "POST";
    form.action = "home2.htm";

//Add the player informations
    var champCache1 = document.createElement('input');
    champCache1.type = "hidden";
    champCache1.name = "playerName";
    champCache1.value = $("#txt-domain").val();
    form.appendChild(champCache1);

//Send the form
    document.body.appendChild(form);
    form.submit();
}


/**
 * FUNCTION start
 * This function is called when the user push the "sign in" button. It launches the connection with skype.
 */
function start() {
    var domain = $("#txt-domain").val();
    var access_token = $("#txt-token").val();
    var Bearercwt = 'Bearer cwt=';
    var Bearer = 'Bearer ';
    var cwt = 'cwt';
    if (access_token.indexOf(cwt) == -1) {
        access_token = Bearercwt + access_token;
    }
    if (access_token.indexOf(Bearer) == -1) {
        access_token = Bearer + access_token;
    }
    var options = {
        auth: function (req, send) {
            req.headers['Authorization'] = access_token.trim();
            return send(req);
        },
        domain: domain
    };
  // signin(options);
   send();
}





