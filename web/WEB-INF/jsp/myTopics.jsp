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
        <link rel="stylesheet" type="text/css" media="screen" href="css/Buttons.css">
         <link rel="stylesheet" type="text/css" media="screen" href="css/Table.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/MyTopics.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
        <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.2.min.js"></script>
        <script src="//swx.cdn.skype.com/shared/v/1.2.15/SkypeBootstrap.min.js"></script>
        
        <script src="scripts/launchAPI.js"></script>
        <script src="scripts/signOut.js"></script>
        <script src="scripts/myTopics.js"></script>

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
            <input type="hidden" id="idco" name="idco" value="<c:out value="${idco}"/>">
            <input type="hidden" id="nbTopic" value="<c:out value="${fn:length(topics)}"/>">

            <div id="myTopic">
                <div class="title">   
                    <h1 id="title">Your topics</h1>
                </div>
                <div id="back">
                    <form method="POST" action="profile.htm">
                        <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                        <button class="back" >Back </button>
                    </form>
                </div>
                <center>
                    <div class="tablediv">
                        <table>
                            <c:forEach var="t" items="${myTopics}" varStatus="status">
                                <tr class="topic<c:out value="${status.index}"/>"  onmouseover="over('topic<c:out value="${status.index}"/>')" onmouseout="out('topic<c:out value="${status.index}"/>')">
                                    <td><c:out value="${t['topicName']}"/></td>
                                </Tr>
                            </c:forEach>
                        </table>
                    </div>
                </center>

                <br><br>
                <button class="classic" id="v1" onclick="add();"> Add some topics</button>
            </div>
            <div id="add">
                <div class="title">   
                    <h1 id="title">Choose some topics</h1>
                </div>
                <div id="back">
                    <form method="POST" action="profile.htm">
                        <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                        <button class="back" >Back </button>
                    </form>
                </div>
                <center>
                    <div class="tablediv">
                        <table>
                            <c:forEach var="t" items="${topics}" varStatus="status">
                                <tr class="topic1<c:out value="${status.index}"/>"  onmouseover="over('topic1<c:out value="${status.index}"/>')" onmouseout="out1('topic1<c:out value="${status.index}"/>')">
                                    <td><c:out value="${t['topicName']}"/></td>
                                    <td><input id ="topic1<c:out value="${status.index}"/>" type="checkbox" value="<c:out value="${t['topicID']}"/>" onclick="isChecked('topic1<c:out value="${status.index}"/>')"  ></td>
                                </Tr>
                            </c:forEach>
                        </table>
                    </div>
                </center>

                <br><br>

                <button class="classic" id="v2" onclick="send();"> Submit</button>
            </div>

        </div>

    </body>
</html>
