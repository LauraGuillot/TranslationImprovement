<%-- 
    Document   : profile
    Created on : 21-Apr-2016, 16:40:37
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
        <link rel="stylesheet" type="text/css" media="screen" href="css/Profile.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/ProgressBar.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Trophies.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Buttons.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
        <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.2.min.js"></script>
        <script src="//swx.cdn.skype.com/shared/v/1.2.15/SkypeBootstrap.min.js"></script>

        <script src="scripts/launchAPI.js"></script>
        <script src="scripts/signOut.js"></script>
        <script src="scripts/progressBar.js"></script>
        <script src="scripts/legendTrophies.js"></script>
        <script src="scripts/profile.js"></script>

        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

        <title></title>
    </head>
    <body onload="display();">
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
                    <button class="tab">Evaluations </button>
                </form>
            </div>
            <div id="tab4">
                <form method="POST" action="profile.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="tab" id="activeTab">Profile </button>
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
            <div id="picture">
            </div>

            <div id="progressBar">
                <input type="hidden" id="pts" value="<c:out value="${player['playerPoints']}"/>"/>
                <ul class="checkout-bar" >
                    <li class="step" id="step0" onmouseover="displayText('0');" onmouseout="removeText();"></li>
                    <li class="step" id="step1" onmouseover="displayText('1');" onmouseout="removeText();"></li>
                    <li class="step" id="step2" onmouseover="displayText('2');" onmouseout="removeText();"></li>
                    <li class="step" id="step3" onmouseover="displayText('3');" onmouseout="removeText();"></li>
                    <li class="step" id="step4" onmouseover="displayText('4');" onmouseout="removeText();"></li>
                </ul>
                <div id="stepTxt"></div>
            </div>

            <div class="block" id="scores">
                <div class="score" id="myPoints">
                    <h1 id="h13">Points </h1> <br>
                    <h1 id="h11"><b><c:out value="${player['playerPoints']}"/></b></h1>
                </div>

                <div class="score" id="myCS">
                    <h1 id="h13">Confidence score</h1> 
                    <h1 id="h11"><b><c:out value="${player['playerConfidenceScore']}"/></b></h1>
                </div>

                <div class="score" id="myRank">
                    <h1 id="h12">Rank </h1><br> 
                    <h1 id="h11"><b><c:out value="${rank}"/></b></h1>
                </div>
            </div>

            <div class="block" id="missions">
                <h1 style="margin-top:5px;">MISSIONS</h1>
                <div class="mission" id="m1"><font style="color:#ffc202;font-weight: bold">1.</font> <c:out value="${missions[0]}"/></div>
                <div class="mission" id="m2"><font style="color:#ffc202;font-weight: bold">2.</font> <c:out value="${missions[1]}"/></div>
                <div class="mission" id="m3"><font style="color:#ffc202;font-weight: bold">3.</font> <c:out value="${missions[2]}"/> </div>
            </div>

            <div id="legendTrophy"></div>

            <div class="block" id="trophies">
                <h1 style="margin-top:5px;">TROPHIES</h1>
                <c:forEach var="i" begin="1" end="9">
                    <input type="hidden" id="hast<c:out value="${i}"/>" value="${trophies[i-1]}"/>
                </c:forEach>
                <div class="trophy" id="t1" onmouseover="legend(1);" onmouseout="removeLegend();"></div>
                <div class="trophy" id="t2" onmouseover="legend(2);" onmouseout="removeLegend();"></div>
                <div class="trophy" id="t3" onmouseover="legend(3);" onmouseout="removeLegend();"></div>
                <div class="trophy" id="t4" onmouseover="legend(4);" onmouseout="removeLegend();"></div> 
                <div class="trophy" id="t5" onmouseover="legend(5);" onmouseout="removeLegend();"></div>
                <div class="trophy" id="t6" onmouseover="legend(6);" onmouseout="removeLegend();"></div>
                <div class="trophy" id="t7" onmouseover="legend(7);" onmouseout="removeLegend();"></div>
                <div class="trophy" id="t8" onmouseover="legend(8);" onmouseout="removeLegend();"></div>
                <div class="trophy" id="t9" onmouseover="legend(9);" onmouseout="removeLegend();"></div>
            </div>

            <div id="mySub"> 
                <form method="POST" action="mySubmissions.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="linkButton">My submissions</button>
                </form>
            </div>

            <div id="myTopic">
                <form method="POST" action="myTopics.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="linkButton">My topics</button>
                </form>
            </div>
        </div>
    </body>
</html>
