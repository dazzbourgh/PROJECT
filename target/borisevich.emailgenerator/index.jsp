<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Please login</h1>

        <form action="login" method="POST">
            Username: <input type="text" name="username"/><br/>
            Password: <input type="password" name="password"/><br/>
            <input type="submit" value="Login"/>
        </form>
        <form action="register.jsp" method="GET">
            <c:set var="isLoginIncorrect" value="${false}" scope="request"/>
            <c:set var="isLoginUsed" value="${false}" scope="request"/>
            <c:set var="isPasswordIncorrect" value="${false}" scope="request"/>
            <input type="submit" value="Register"/>
        </form>
    </body>
</html>