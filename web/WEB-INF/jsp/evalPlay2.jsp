<%-- 
    Document   : eval
    Created on : 21-Apr-2016, 16:40:31
    Author     : Laura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" media="screen" href="css/Main.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Table.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Buttons.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Evaluation1.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Jqcloud.css">
        

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.2.min.js"></script>
        <script src="//swx.cdn.skype.com/shared/v/1.2.15/SkypeBootstrap.min.js"></script>

        <script src="scripts/Jqcloud/jqcloud-1.0.4.min.js"></script>
        <script src="scripts/Jqcloud/jqcloud-1.0.4.js"></script>
        
        <script src="scripts/launchAPI.js"></script>
        <script src="scripts/evalPlay2.js"></script>
        <script src="scripts/cloud.js"></script>
        <script src="scripts/signOut.js"></script>

        <title></title>
    </head>
    <body onload="topic();">
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
            <div id="play">

                <input type="hidden" id="l1" value="<c:out value="${l1}"/>">
                <input type="hidden" id="l2" value="<c:out value="${l2}"/>">
                <input type="hidden" id="idco" value="<c:out value="${idco}"/>">
                <input type="hidden" id="n" value="<c:out value="${n}"/>">




                <c:forEach var="t" items="${weight}" varStatus="status">
                    <input type="hidden" id="w<c:out value="${status.index}"/>" value="<c:out value="${t}"/>"/>
                </c:forEach>

                <c:forEach var="t" items="${topics}" varStatus="status">
                    <input type="hidden" id="top<c:out value="${status.index}"/>" value="<c:out value="${t['topicName']}"/>"/>
                </c:forEach>


                <input type="hidden" id="nbTop" value="<c:out value="${fn:length(topics)}"/>"/>
                <input type="hidden" id="sentID" value="<c:out value="${sentence['sentenceID']}"/>"/>

                <div id="cpt2">
                    Score : <c:out value="${n}"/>
                </div>

                <div id="sentence1">
                    <div id="sentenceTitle"> 
                        <h1>Sentence</h1>
                    </div>
                    <div id="sentenceBody"> 
                        <c:out value="${sentence['sentenceBody']}"/>
                    </div>
                </div>

                <div id="trv2">
                    <table>
                        <c:forEach var="tr" items="${translations}" varStatus="status">
                            <tr onmouseover="tableover(this);" onmouseout="tableout(this);">
                                <td><c:out value="${tr['translationBody']}"/></td>
                                <td> <button class="valid" onclick="next(<c:out value="${tr['translationID']}"/>);"></button> </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div id="error"></div>

                <div id="cloudTitle">Context of the sentence </div>
                <div id="cloud"></div>

            </div>
        </div>
    </body>
</html>
