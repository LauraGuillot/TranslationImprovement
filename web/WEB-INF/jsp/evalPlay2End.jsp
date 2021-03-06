<%-- 
    Document   : eval
    Created on : 21-Apr-2016, 16:40:31
    Author     : Laura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" media="screen" href="css/Main.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Buttons.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Evaluation2End.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Pop.css">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.2.min.js"></script>
        <script src="//swx.cdn.skype.com/shared/v/1.2.15/SkypeBootstrap.min.js"></script>

        <script src="scripts/launchAPI.js"></script>
        <script src="scripts/signOut.js"></script>
         <script src="scripts/missionPopUp.js"></script>

        <title></title>
    </head>
    <body onload="pop(); topic(); ">
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
                    <button class="tab">Submit </button>
                </form>
            </div>
            <div id="tab3">
                <form method="POST" action="eval.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="tab" id="activeTab">Evaluate </button>
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
                <div>Name : <c:out value="${player['playerName']}"/></div>
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


            <input type="hidden" id="sizeMissions" value= "<c:out value="${fn:length(wonMissions)}"/>">

            <c:choose>
                <c:when test="${status==0}">
                    <div id="playEnd" >
                        <div id="thank">
                            Thank you for your contribution
                        </div>
                        <br><br><br>

                        <div id = "results">
                            <br><br>
                            You have evaluated all the sentences in our database ! 
                            <br><br>
                            Come back later!
                        </div>
                        <div id = "earn">
                            <h1 id="s1">You earn </h1><br>
                            <h1 id="s2"><c:out value="${n}"/></h1>
                            <h1 id="s1">pts</h1>
                        </div>
                        <div id="missions">
                            <h1 ><font style="color:#535151;">MISSIONS</font></h1>
                            <br><br>
                            <div class="mission" id="m1"><font style="color:#ffc202;font-weight: bold">1.</font> <c:out value="${missions[0]}"/></div>
                            <br>
                            <div class="mission" id="m2"><font style="color:#ffc202;font-weight: bold">2.</font> <c:out value="${missions[1]}"/></div>
                            <br>
                            <div class="mission" id="m3"><font style="color:#ffc202;font-weight: bold">3.</font> <c:out value="${missions[2]}"/></div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div id="playEnd" >
                        <div id="thank">
                            Thank you for your contribution
                        </div>
                        <br><br><br>

                        <div id = "results">
                            <br><br>
                            What a shame ! <br><br>
                            Your last vote was not in agreement with the majority !
                        </div>
                        <div id = "earn">
                            <h1 id="s1">You earn </h1><br>
                            <h1 id="s2"><c:out value="${n}"/></h1>
                            <h1 id="s1">pts</h1>
                        </div>
                        <div id="missions">
                            <h1 ><font style="color:#535151;">MISSIONS</font></h1>
                            <br><br>
                            <div class="mission" id="m1"><font style="color:#ffc202;font-weight: bold">1.</font> <c:out value="${missions[0]}"/></div>
                            <br>
                            <div class="mission" id="m2"><font style="color:#ffc202;font-weight: bold">2.</font> <c:out value="${missions[1]}"/></div>
                            <br>
                            <div class="mission" id="m3"><font style="color:#ffc202;font-weight: bold">3.</font> <c:out value="${missions[2]}"/></div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
            
        <div id="popGround" onclick="groundclick();">

            <div id="pop" >
                <div id="star"></div>
                <button id="croix" class="end" onclick="groundclick();"></button>
                <h1 class="mTitle">Missions accomplished !</h1>
                <br><br>
                <h1 class="mlegend">You earn 15 points per achieved mission</h1>
                <br><br><br>
                <c:forEach var="m" items="${wonMissions}" varStatus="status">
                    <h1 class="mText"> <c:out value="${m}"/></h1><br>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
