<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login to our app</title>
        <style>
        p    {color: red;}
        </style>
    </head>
    <body>
        <h1>Please register</h1>

        <c:set var="isLoginIncorrect" value="${isLoginIncorrect}"/>
        <c:set var="isLoginUsed" value="${isLoginUsed}"/>
        <c:set var="isPasswordIncorrect" value="${isPasswordIncorrect}"/>
        <form action="register" method="POST">
            Username: <input type="text" name="username"/><br/>
            Password: <input type="password" name="password"/><br/>
            Repeat password: <input type="password" name="password2"/><br/>
            <input type="submit" value="Register"/>
        </form>
        <c:if test="${Error != null}">
            <p>"${Error}"</p>
        </c:if>
    </body>
</html>