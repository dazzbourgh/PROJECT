<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="language" value="en-US" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text_ru_CYRILLIC" var="lang"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <fmt:message key="index.label.header" bundle="${lang}" var="header1" />
        <h1>${header1}</h1>

        <form action="login" method="POST">
            <fmt:message key="index.label.username" bundle="${lang}" /><br>
            <input type="text" name="username"/><br/>
            <fmt:message key="index.label.password" bundle="${lang}" /><br>
            <input type="password" name="password"/>
            <br>
            <fmt:message key="index.button.signin" bundle="${lang}" var="buttonSignIn" />
            <input type="submit" value="${buttonSignIn}"/>
        </form>
        <form action="register.jsp" method="GET">
            <c:set var="isLoginIncorrect" value="${false}" scope="request"/>
            <c:set var="isLoginUsed" value="${false}" scope="request"/>
            <c:set var="isPasswordIncorrect" value="${false}" scope="request"/>
            <fmt:message key="index.button.register" bundle="${lang}" var="buttonRegister" />
            <input type="submit" value="${buttonRegister}"/>
        </form>
    </body>
</html>