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
        <link rel="stylesheet" type="text/css" media="screen" href="css/Table.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Buttons.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Stat2.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.2.min.js"></script>
        <script src="//swx.cdn.skype.com/shared/v/1.2.15/SkypeBootstrap.min.js"></script>

        <script src="scripts/launchAPI.js"></script>
        <script src="scripts/filter.js"></script>
        <script src="scripts/Lib/datatable.js"></script>
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
                <form method="POST" action="stat1.htm">
                    <input type="hidden" name="idco" value="<c:out value="${idco}"/>">
                    <button class="back" >Back </button>
                </form>
            </div>

            <div id="table">
                <table id="mytable" class="mytable filterable" cellpadding="0" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Sentence language</th>
                            <th>Translation language</th>
                            <th>Sentence</th>
                            <th>Approved translation</th>
                            <th>Translation's writer</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr style="text-align: center; height:35px;background-color: #B5D3D4"> 
                            <td></td>
                            <td></td>
                            <td></th>
                            <td></td>
                            <td></td>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="s" items="${sent}" varStatus="status">

                            <c:choose>
                                <c:when test="${s['player']==player['playerName']}"> 
                                    <tr class="me">
                                        <td style="text-align:center;"> <c:out value="${s['l1']}"/></td>
                                        <td style="text-align:center;">  <c:out value="${s['l2']}"/></td>
                                        <td> <c:out value="${s['body']}"/></td>
                                        <td> <c:out value="${s['tr']}"/></td>
                                        <td style="text-align:center;">  <c:out value="${s['player']}"/></td>
                                    </tr> 
                                </c:when>   
                                <c:otherwise>
                                    <tr>
                                        <td style="text-align:center;">  <c:out value="${s['l1']}"/></td>
                                        <td style="text-align:center;">  <c:out value="${s['l2']}"/></td>
                                        <td> <c:out value="${s['body']}"/></td>
                                        <td> <c:out value="${s['tr']}"/></td>
                                        <td style="text-align:center;">  <c:out value="${s['player']}"/></td>
                                    </tr> 
                                </c:otherwise>
                            </c:choose>


                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>


    </body>

</html>
