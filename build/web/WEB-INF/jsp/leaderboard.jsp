<%-- 
    Document   : leaderboard
    Created on : 21-Apr-2016, 16:41:01
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
        <link rel="stylesheet" type="text/css" media="screen" href="css/Leaderboard.css">


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
                <div>Name : <c:out value="${player['playerName']}"/></div>
                <div>Points : <c:out value="${player['playerPoints']}"/> </div>
                <div>Confidence score : <c:out value="${player['playerConfidenceScore']}"/></div>
            </div>  

            <div id="leaderboard">
                <form method="POST" action="leaderboard.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="leaderboardActive" ></button>
                </form>
            </div>

        </div>
        <div class="main">

            <div class="title">   
                <h1 id="title">Leaderboards</h1>
            </div>

            <div id="tables">

                <div class="board" id="table1" >

                    <h1 class="subtitle">By points</h1>


                    <div class="tableDiv">   
                        <table class="rank">
                            <tr>
                                <th>Rank</th>
                                <th>Name</th>
                                <th>Points</th>
                            </tr>
                            <c:forEach var="p" items="${t1}" varStatus="status">

                                <c:choose>
                                    <c:when test="${p['playerID']==player['playerID']}"> 
                                        <c:choose>
                                            <c:when test="${r1[status.index]<4}"> 
                                                <tr class="meTop">    
                                                    <td class="topRank"><b><c:out value="${r1[status.index]}"/></b></td>
                                                    <td><c:out value="${p['playerName']}"/></td>
                                                    <td><c:out value="${p['playerPoints']}"/></td>
                                                </tr>
                                            </c:when>

                                            <c:otherwise> 
                                                <tr class="me">    
                                                    <td><b><c:out value="${r1[status.index]}"/></b></td>
                                                    <td><c:out value="${p['playerName']}"/></td>
                                                    <td><c:out value="${p['playerPoints']}"/></td>
                                                </tr>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>

                                    <c:otherwise> 
                                        <c:choose>
                                            <c:when test="${r1[status.index]<4}"> 
                                                <tr class="top">    
                                                    <td class="topRank"><b><c:out value="${r1[status.index]}"/></b></td>
                                                    <td><c:out value="${p['playerName']}"/></td>
                                                    <td><c:out value="${p['playerPoints']}"/></td>
                                                </tr>
                                            </c:when>

                                            <c:otherwise> 
                                                <tr class="other">    
                                                    <td><b><c:out value="${r1[status.index]}"/></b></td>
                                                    <td><c:out value="${p['playerName']}"/></td>
                                                    <td><c:out value="${p['playerPoints']}"/></td>
                                                </tr>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>

                                </c:choose>

                            </c:forEach>
                        </table>
                    </div>

                </div>

                <div class="board" id="table2" >
                    <h1 class="subtitle">By confidence score</h1>

                    <div class="tableDiv">  <table class="rank">
                            <tr>
                                <th>Rank</th>
                                <th>Name</th>
                                <th>Confidence score</th>
                            </tr>
                            <c:forEach var="p" items="${t2}" varStatus="status">
                                <c:choose>
                                    <c:when test="${p['playerID']==player['playerID']}"> 
                                        <c:choose>
                                            <c:when test="${r2[status.index]<4}"> 
                                                <tr class="meTop">    
                                                    <td class="topRank"><b><c:out value="${r2[status.index]}"/></b></td>
                                                    <td><c:out value="${p['playerName']}"/></td>
                                                    <td><c:out value="${p['playerConfidenceScore']}"/></td>
                                                </tr>
                                            </c:when>

                                            <c:otherwise> 
                                                <tr class="me">    
                                                    <td><b><c:out value="${r2[status.index]}"/></b></td>
                                                    <td><c:out value="${p['playerName']}"/></td>
                                                    <td><c:out value="${p['playerConfidenceScore']}"/></td>
                                                </tr>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>

                                    <c:otherwise> 
                                        <c:choose>
                                            <c:when test="${r2[status.index]<4}"> 
                                                <tr class="top">    
                                                    <td class="topRank"><b><c:out value="${r2[status.index]}"/></b></td>
                                                    <td><c:out value="${p['playerName']}"/></td>
                                                    <td><c:out value="${p['playerConfidenceScore']}"/></td>
                                                </tr>
                                            </c:when>

                                            <c:otherwise> 
                                                <tr class="other">    
                                                    <td><b><c:out value="${r2[status.index]}"/></b></td>
                                                    <td><c:out value="${p['playerName']}"/></td>
                                                    <td><c:out value="${p['playerConfidenceScore']}"/></td>
                                                </tr>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>

                                </c:choose>

                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
