<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="dz" uri="/WEB-INF/logTag.tld" %>
<jsp:directive.include file="locale.jsp" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style type="text/css"> <%@include file="/css/styles.css" %> </style>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
    <dz:log />
        <div class="container vertical-center bg-blue">
            <div class="form-group well bg-white" align="center">
                <fmt:message key="index.label.header" bundle="${lang}" var="header1" />
                <h1>${header1}</h1>

                    <form action="login" method="POST">
                        <fmt:message key="index.label.username" bundle="${lang}" /><br>
                        <input type="text" name="username"/><br/>
                        <fmt:message key="index.label.password" bundle="${lang}" /><br>
                        <input type="password" name="password"/>
                        <br>
                        <fmt:message key="index.button.signin" bundle="${lang}" var="buttonSignIn" />
                        <input type="submit" value="${buttonSignIn}" class="btn btn-default btn-margin"/>
                    </form>
                    <form action="register.jsp" method="GET">
                        <c:set var="isLoginIncorrect" value="${false}" scope="request"/>
                        <c:set var="isLoginUsed" value="${false}" scope="request"/>
                        <c:set var="isPasswordIncorrect" value="${false}" scope="request"/>
                        <fmt:message key="index.button.register" bundle="${lang}" var="buttonRegister" />
                        <input type="submit" value="${buttonRegister}" class="btn btn-default btn-margin"/>
                    </form>
            </div>
        </div>
    </body>
</html>