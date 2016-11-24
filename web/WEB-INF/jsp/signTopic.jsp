<%-- 
    Document   : signIn
    Created on : 21-Apr-2016, 14:42:07
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
        <link rel="stylesheet" type="text/css" media="screen" href="css/SignIn.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.2.min.js"></script>
        <script src="//swx.cdn.skype.com/shared/v/1.2.15/SkypeBootstrap.min.js"></script>

        <script src="scripts/launchAPI.js"></script>
        <script src="scripts/signTopic.js"></script>
        
        <title></title>
    </head>
    <body>
        <div id="menu">
            <div id="tab1">
                <button class="tab" onclick="self.location.href = 'home.htm'" >Home </button>
            </div>
        </div>
        <div class="main">
            <div id="topic">

                <h1 id="title" style="font-size:30px">Please select the topics you're confident with</h1>
                <br><br><br><br>

                <input type="hidden" id="nbTopic" value="<c:out value="${fn:length(topics)}"/>">
                <input type="hidden" id="playerName" value="<c:out value="${playerName}"/>">

                <center>
                    <div class="tablediv">
                        <table>
                            <c:forEach var="t" items="${topics}" varStatus="status">
                                <tr class="topic<c:out value="${status.index}"/>"  onmouseover="over('topic<c:out value="${status.index}"/>')" onmouseout="out('topic<c:out value="${status.index}"/>')">
                                    <td><c:out value="${t['topicName']}"/></td>
                                    <td><input id ="topic<c:out value="${status.index}"/>" type="checkbox" value="<c:out value="${t['topicID']}"/>" onclick="isChecked('topic<c:out value="${status.index}"/>')"  ></td>
                                </Tr>
                            </c:forEach>
                        </table>
                    </div>
                </center>

                <br><br>

                <button class="classic" onclick="send();"> Submit</button>

                <p class="error" style="display: none;" id="errorTopic">Please tick at least one topic </p>
            </div>
        </div>
    </body>
</html>
