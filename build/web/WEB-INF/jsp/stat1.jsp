<%-- 
    Document   : hello.jsp
    Created on : 12 mars 2016, 17:00:03
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
        <link rel="stylesheet" type="text/css" media="screen" href="css/Stat1.css">
        
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
                    <button class="tab" id="activeTab">Home </button>
                </form>
            </div>
            <div id="tab2">
                <form method="POST" action="submit.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="tab">Submit </button>
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
            <div id="back">
                <form method="POST" action="homeReturn.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="back" >Back </button>
                </form>
            </div>
            <div id="nbPlayers" class="bubble">
                <h1 class="h11"> Number of players : </h1>
                <br>
                <h1 class="h12"> <c:out value="${nbPlayers}"/></h1>
            </div>
            <div id="nbApprovedSentences" class="bubble">
                <h1 class="h11"> Number of approved sentences : </h1>
                <br>
                <h1 class="h12"><c:out value="${nbApprovedSent}"/></h1>
            </div>
            <div id="nbInProgressSentences" class="bubble">
                <h1 class="h11">  Number de sentences in progress :</h1>
                <br>
                <h1 class="h12"> <c:out value="${nbInprogressSent}"/></h1>
            </div>
            <div id="nbVotes" class="bubble">
                <h1 class="h11">  Number of votes : </h1>
                <br>
                <h1 class="h12">   <c:out value="${nbVotes}"/></h1>
            </div>
            <div id="nbSub" class="bubble">
                <h1 class="h11"> Number of submissions : </h1>
                <br>
                <h1 class="h12">   <c:out value="${nbSub}"/></h1>
            </div>
            <div id="nextPage">
                <form method="POST" action="stat2.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="nextPage" >See approved translations</button>
                </form>

            </div>
        </div>


    </body>

</html>
