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
        <link rel="stylesheet" type="text/css" media="screen" href="css/Home.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.2.min.js"></script>
        <script src="//swx.cdn.skype.com/shared/v/1.2.15/SkypeBootstrap.min.js"></script>

        <script src="scripts/signLegend.js"></script>
        <script src="scripts/launchAPI.js"></script>
        <script src="scripts/signOut.js"></script>

        <title>Home Page</title>

    </head>
    <body>

        <c:choose>
            <c:when test="${isSign==true}"> 
                <div id="menu">
                    <div id="tab1">
                        <button class="tab" id="activeTab">Home </button>
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

                <div id="stat1">
                    <form method="POST" action="stat1.htm">
                        <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                        <button class="stat" href="stat1.htm";> Statistics about the game &nbsp;&nbsp;&nbsp;&#10095;</button>
                    </form>
                </div>
            </c:when>
            <c:otherwise>   
                <div id="menu">
                    <div id="sign">
                        <form method="GET" action="signIn.htm">
                            <button class="signButton" id="signIn" >SIGN IN</button>
                        </form>
                    </div>     
                    <div id="legendIn">
                    </div>
                </div>

            </c:otherwise>
        </c:choose>

        <div class="wrap" >
            <div class="mySlides" id="slide1">
                <div class="txt">
                    <center>
                        <h1 id="welcome">Welcome !</h1>
                        <br><br><br><br>
                    </center>
                    <div class="pa">
                        <br><br>
                        <center> Help us to mprove automatic translations from <a href="https://www.skype.com/en/features/skype-translator/" target="_blank">Skype</a> !<br> 
                            Vote for the best translations, compete with your friends, win trophies and points, deliver your opinion improving also your language skills!
                            <c:choose>
                                <c:when test="${isSign==false}"> 
                                    Let's go! <a class="a2" href="signIn.htm">Sign in</a> with your Skype account
                                </c:when>
                            </c:choose>
                        </center>
                    </div>
                </div>
            </div>

            <div class="mySlides" id="slide2">
                <div class="txt">
                    <center>
                        <h1 id="welcome">Presentation</h1>
                        <br><br><br><br>
                    </center>
                    <div class="pa">
                        <br>
                        <center>
                            <br>
                            The game is based on a collection of user feedback : users submit sentences and translations, and they vote for the most accurate ones. The objective is to improve automatic translation using the user's comments.
                            <br><br>
                            This game has been developed thanks to a cooperation between University College Dublin and Microsoft Skype.
                        </center>
                    </div>
                </div>
            </div>

            <div class="buttons" >
                <div class="nextSlide" id="left" onclick="plusDivs(-1)">&#10094;</div>
                <div class="nextSlide" id="right" onclick="plusDivs(1)">&#10095;</div>  
            </div>
        </div>
    </body>
    <script src="scripts/slide.js"></script>
</html>
