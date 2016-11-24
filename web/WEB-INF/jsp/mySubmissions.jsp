<%-- 
    Document   : profile
    Created on : 21-Apr-2016, 16:40:37
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
        <link rel="stylesheet" type="text/css" media="screen" href="css/Table.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Buttons.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/MySubmissions.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
        <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.2.min.js"></script>
        <script src="//swx.cdn.skype.com/shared/v/1.2.15/SkypeBootstrap.min.js"></script>
        
        <script src="scripts/launchAPI.js"></script>
        <script src="scripts/signOut.js"></script>

        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

        <title></title>
    </head>
    <body >
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

            <div class="title">   
                <h1 id="title">Your submissions</h1>
            </div>
            <div id="back">
                <form method="POST" action="profile.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="back" >Back </button>
                </form>
            </div>

            <div id="sub">
                <c:choose>
                    <c:when test="${fn:length(sub)>0}"> 
                        <table>
                            <tr>
                                <th> Sentence </th>
                                <th> Translation </th>
                                <th> Votes for </th>
                                <th> Votes against</th>
                                <th> Status </th>
                            </tr>
                            <c:forEach var="i" begin="0" end="${fn:length(sub)-1}">
                                <c:choose>
                                    <c:when test="${sub[i]['sentenceValidation']}"> 
                                        <c:choose>
                                            <c:when test="${tr2[i]['player']==player['playerID']}"> 
                                                <tr class="A">
                                                    <td> <b><c:out value="${sub[i]['sentenceBody']}"/></b></td>
                                                    <td> <c:out value="${tr[i]['translationBody']}"/></td>
                                                    <td> <c:out value="${vu[i]}"/></td>
                                                    <td> <c:out value="${vs[i]}"/></td> 
                                                    <td> Approved </td>
                                                </tr>
                                            </c:when>
                                            <c:otherwise>
                                                <tr class="R">
                                                    <td> <b><c:out value="${sub[i]['sentenceBody']}"/></b></td>
                                                    <td> <c:out value="${tr[i]['translationBody']}"/></td>
                                                    <td> <c:out value="${vu[i]}"/></td>
                                                    <td> <c:out value="${vs[i]}"/></td> 
                                                    <td> Rejected </td>
                                                </tr>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>  
                                        <tr class="P">
                                            <td> <b><c:out value="${sub[i]['sentenceBody']}"/></b></td>
                                            <td> <c:out value="${tr[i]['translationBody']}"/></td>
                                            <td> <c:out value="${vu[i]}"/></td>
                                            <td> <c:out value="${vs[i]}"/></td>
                                            <td> In progress</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <br><br>
                            <h1 style="font-weight:normal;color:#535351"> No submissions for the moment</h1>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>

        </div>

    </body>
</html>
