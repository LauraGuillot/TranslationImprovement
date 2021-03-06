//Configuration 
var config = {
    version: 'BuildConference/1.0.0', // this helps to identify telemetry generated by the samples
    apiKey: 'a42fcebd-5b43-4b89-a065-74450fb91255', // SDK DF
    apiKeyCC: '9c967f6b-a846-4df2-b43d-5167e47d81e1' // SDK+CC DF
};

$(function () {
  //Launching of SKYPE
    Skype.initialize({apiKey: config.apiKey}, function (api) {
        window.skypeWebAppCtor = api.application;
        window.skypeWebApp = new api.application();
    });
    
}, function (err) {
    console.log(err);
    alert('Cannot load the SDK.');
});

function monitor(title, promise) {
    console.log(title);
    promise.then(function (res) {
        return console.log(title + '...done');
    }, function (err) {
        return console.log(title + '...failed', err && err.stack || err);
    });
}
