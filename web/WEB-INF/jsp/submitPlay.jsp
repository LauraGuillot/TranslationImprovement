<%-- 
    Document   : submit
    Created on : 21-Apr-2016, 16:40:20
    Author     : Laura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" media="screen" href="css/Main.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Table.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Buttons.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/SubmitPlay.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script type="text/javascript" src="https://secure.skypeassets.com/i/scom/js/skype-uri.js"></script>
        <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.2.min.js"></script>

        <script src="scripts/submitPlay.js"></script>
        <script src="scripts/signOut.js"></script>

        <script src="scripts/SpeechToText/speech.1.0.0.js"></script>
        <script src="scripts/SpeechToText/speech.1.0.0.min.js"></script>
        <script src="scripts/SpeechToText/translate.js"></script>
        <script src="scripts/SpeechToText/speechToText.js"></script>

        <title></title>

    </head>
    <body>
        <div id="menu">
            <div id="tab1">
                <form method="POST" action="homeReturn.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="tab">Home </button>
                </form>
            </div>
            <div id="tab2">
                <form method="POST" action="submit.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="tab" id="activeTab">Submit </button>
                </form>
            </div>
            <div id="tab3">
                <form method="POST" action="eval.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="tab">Evaluate</button>
                </form>
            </div>
            <div id="tab4">
                <form method="POST" action="profile.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="tab">Profile </button>
                </form>
            </div>

            <div id="sign" style="margin-right:10px">
                <input style="display:none" type="txt" id="idco-sign-out" name="idco" value="<c:out value="${idco}"/>">
                <button class="signButton" id="btn-sign-out" onclick="signOut();">SIGN OUT</button>
            </div>

            <div id="info">
                <div class="txt">Name : <c:out value="${player['playerName']}"/></div>
                <div class="txt">Points : <c:out value="${player['playerPoints']}"/> </div>
                <div class="txt">Confidence score : <c:out value="${player['playerConfidenceScore']}"/></div>
            </div> 

            <div id="leaderboard">
                <form method="POST" action="leaderboard.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="leaderboard" ></button>
                </form>
            </div>
        </div>

        <div class="main">
            <input type="hidden" id="idco" value="<c:out value="${idco}"/>">
            <input type="hidden" id="l1" value="<c:out value="${l1}"/>">
            <input type="hidden" id="l2" value="<c:out value="${l2}"/>">

            <div id="page1">
                <center><div id="instruction">
                        Click on the following button to launch Skype. Then, call your friends.<br>
                        Your entire conversation will be translated on this screen and you will be able to correct it.
                    </div></center>

                <div id="Skype">
                    <div id="SkypeButton" onclick="page2();">
                        <script type="text/javascript">
                            Skype.ui({
                                "element": "SkypeButton",
                                "imageColor": "white",
                                "imageSize": 32
                            });
                        </script>
                    </div>

                </div>
            </div>
            <div id="page2">
                <div id="script">
                    <table id="scriptTable">  
                    </table>
                </div>

                <div id="formSub">
                    <label>Sentence</label>
                    <br>
                    <input class="subTxt" type="text" name="sentence" id="sentence" value="">
                    <br>
                    <label>Automatic translation</label>
                    <br>
                    <input class="subTxt" type="text" name="tr1" id="tr1" value="">
                    <br>
                    <label>Your correction</label>
                    <br>
                    <input class="subTxt" type="text" name="tr2" id="tr2" value="">
                    <br>
                    <button class="valid" onclick="nextsub();"></button>
                </div>

                <div id="buttons">
                    <div id="endDiv1"  onmousedown="endRecognition();" onmouseover="overDiv(1)" onmouseout="outDiv(1)">
                        <button id = "end1" class="end"></button>
                        Stop the transcription
                    </div>
                    <div id="endDiv2" onmousedown="end();" onmouseover="overDiv(2)" onmouseout="outDiv(2)">
                        <button id ="end2" class="end" ></button>
                        Send my contributions and finish
                    </div>
                </div>

                <div id="instruction2">
                   
                     <font style="margin-bottom:100px">
                    Click and correct translations!
                    </font>
                    <br>
                    <font style="font-size: 12pt;color:#ff6f00;font-weight: normal;">Don't forget to allow access to the microphone.</font>
                    <br>
                   
                    <font style="font-size: 15pt; font-weight: normal;">
                    Click on "Stop the transcription" to switch off the microphone. 
                    <br>
                    When you have finished the corrections, click on "Send my contributions and finish" to register them. Otherwise, it won't be save.
                    </font>

                </div>
            </div>
    </body>
</html>
