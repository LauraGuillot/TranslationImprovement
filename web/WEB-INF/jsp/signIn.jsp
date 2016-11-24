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
        <link rel="stylesheet" type="text/css" media="screen" href="css/SignIn.css">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.2.min.js"></script>
        <script src="//swx.cdn.skype.com/shared/v/1.2.15/SkypeBootstrap.min.js"></script>

        <script src="scripts/launchAPI.js"></script>
        <script src="scripts/signIn.js"></script>

        <title></title>
    </head>
    <body>
        <div id="menu">
            <div id="tab1">
                <button class="tab" onclick="self.location.href = 'home.htm'" >Home </button>
            </div>
        </div>

        <div class="main">
            <div id="form">
                <h1 id="title">Sign In</h1>

                <br><br>
                <div id="error"></div>
                <br>

                <div class="token-sign-in">
                    <div>
                        <h1 id="domain">Name</h1>
                        <input id="txt-domain" type="text" value="" placeholder="Name" />
                    </div>
                    <br><br>
                    <div>
                        <h1 id="token">Password</h1>
                        <input id="txt-token" type="text" value="" placeholder="Password" />
                    </div>
                    <br><br>
                    <div>
                        <button class="classic" id="btn-token-sign-in" onclick="start();">Sign in</button>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
