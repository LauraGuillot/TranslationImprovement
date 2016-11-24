<%-- 
    Document   : eval
    Created on : 21-Apr-2016, 16:40:31
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
        <link rel="stylesheet" type="text/css" media="screen" href="css/Buttons.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Evaluation.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.2.min.js"></script>
        <script src="//swx.cdn.skype.com/shared/v/1.2.15/SkypeBootstrap.min.js"></script>

        <script src="scripts/launchAPI.js"></script>
        <script src="scripts/signOut.js"></script>

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
                    <button class="tab">Submissions </button>
                </form>
            </div>
            <div id="tab3">
                <form method="POST" action="eval.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="tab" id="activeTab">Evaluations </button>
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
                <button class="signButton" id="btn-sign-out" onclick="signOut();"  >SIGN OUT</button>
            </div>     
            <div id="info">
                <div>Name : <c:out value="${player['playerName']}"/> </div>
                <div>Points : <c:out value="${player['playerPoints']}"/> </div>
                <div>Confidence score : <c:out value="${player['playerConfidenceScore']}"/></div>
            </div>  
            <div id="leaderboard">
                <form method="POST" action="leaderboard.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="leaderboard" ></button>
                </form>
            </div>
        </div>

        <div class="main">
            <div class="title">   
                <h1 id="title">Evaluations</h1>
            </div>

            <div id="playModes">
                <div id="mode1">
                    <br>
                    <h1 class="modeTitle">Classic mode</h1>
                    <br><br><br>
                    <h1 class='legend'> Evaluate 10 translations</h1>
                    <form action="evalPlay1.htm" method="POST">  
                        <br><br>
                        <input type="hidden" name="idco" value="<c:out value="${idco}"/>">

                        <label>Sentence language : </label>
                        <select name="l1">
                            <option value="en">English - EN</option>
                            <option value="fr">French - FR</option>
                            <option value="de">German - DE</option>
                            <option value="es">Spanish - ES</option>
                            <option value="it">Italian - IT</option>
                            <option value="cn">Chinese - CN</option>
                        </select>

                        <br><br>
                        <label>Translation language : </label>
                        <select name="l2">
                            <option value="en">English - EN</option>
                            <option value="fr">French - FR</option>
                            <option value="de">German - DE</option>
                            <option value="es">Spanish - ES</option>
                            <option value="it">Italian - IT</option>
                            <option value="cn">Chinese - CN</option>
                        </select>

                        <br><br><br>
                        <input type="submit" class="play"  >
                        <br><br>
                    </form>
                </div>

                <div id="mode2">
                    <br>
                    <h1 class="modeTitle">Challenge mode</h1>
                    <br><br>
                    <h1 class='legend'> Evaluate translation as long as you are in agreement with the majority</h1>
                    <form  action="evalPlay2.htm" method="POST">
                        <br><br>
                        <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                        <label>Sentence language : </label>
                        <select name="l1">
                            <option value="en">English - EN</option>
                            <option value="fr">French - FR</option>
                            <option value="de">German - DE</option>
                            <option value="es">Spanish - ES</option>
                            <option value="it">Italian - IT</option>
                            <option value="cn">Chinese - CN</option>
                        </select>
                        <br><br>
                        <label>Translation language : </label>
                        <select name="l2">
                            <option value="en">English - EN</option>
                            <option value="fr">French - FR</option>
                            <option value="de">German - DE</option>
                            <option value="es">Spanish - ES</option>
                            <option value="it">Italian - IT</option>
                            <option value="cn">Chinese - CN</option>
                        </select>
                        <br><br><br>
                        <input type="hidden" name="n" value="0">
                        <input type="hidden" name="vote" value="0">
                        <input type="hidden" name="sentenceID" value="0">
                        <input type="submit" class="play" >
                        <br><br>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
