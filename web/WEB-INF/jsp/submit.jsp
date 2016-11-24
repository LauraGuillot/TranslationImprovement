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
        <link rel="stylesheet" type="text/css" media="screen" href="css/Buttons.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/SubmitStart.css">
        

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.2.min.js"></script>
        <script src="//swx.cdn.skype.com/shared/v/1.2.15/SkypeBootstrap.min.js"></script>
        
        <script src="scripts/launchAPI.js"></script>
        <script src="scripts/signOut.js"></script>
        <script src="scripts/submit.js"></script>
        <title>JSP Page</title>

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
                    <button class="tab" id="activeTab">Submissions </button>
                </form>
            </div>
            <div id="tab3">
                <form method="POST" action="eval.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="tab">Evaluations </button>
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
                <div>Name : <c:out value="${player['playerName']}"/> </div>
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
            <div id="form">
                <br>
                <h1> Please select two languages for the translator</h1>
                <br><br><br>



                <label>Oral language : </label>
                <select id="l1" name="l1">
                    <option value="en">English - EN</option>
                    <option value="fr">French - FR</option>
                    <option value="de">German - DE</option>
                    <option value="es">Spanish - ES</option>
                    <option value="it">Italian - IT</option>
                    <option value="cn">Chinese - CN</option>
                </select>
                <br>
                <br><br>
                <label>Translation language : </label>
                <select  id="l2" name="l2">
                    <option value="en">English - EN</option>
                    <option value="fr">French - FR</option>
                    <option value="de">German - DE</option>
                    <option value="es">Spanish - ES</option>
                    <option value="it">Italian - IT</option>
                    <option value="cn">Chinese - CN</option>
                </select>
                <br><br><br><br>
                <input type="hidden" id="idco" name="idco" value="<c:out value="${idco}"/>">
                <button class="play" onclick="submit();"></button>
            </div>
            <div id="error"></div>
        </div>
    </body>
</html>
