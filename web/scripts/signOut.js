/**
 * FUNCTION signOutFromSkype
 * This function is used to sign out the user from skype
 */
function signOutFromSkype() {
 var client = window.skypeWebApp;
 
 // start signing out
 client.signInManager.signOut()
 .then(function () {
 // and report the success
 console.log('Signed out');
 }, function (error) {
 // or a failure
 console.log(error || 'Cannot sign out');
 });
 }

/**
 * FUNCTION signOut
 * We sign out the user from skype and then, we tell the controller to disconnect the user from the database
 */
function signOut() {
     signOutFromSkype();

    var form = document.createElement('form');
    form.method = "POST";
    form.action = "home3.htm";

    var champCache = document.createElement('input');
    champCache.type = "hidden";
    champCache.name = "connectID";
    champCache.value = document.getElementById("idco-sign-out").value;
    form.appendChild(champCache);

    document.body.appendChild(form);

    form.submit();
}

