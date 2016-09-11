<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="locale.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <style>
        p    {color: red;}
        </style>
    </head>
    <body>
        <h1>${registerLabelHeader}</h1>

        <c:set var="isLoginIncorrect" value="${isLoginIncorrect}"/>
        <c:set var="isLoginUsed" value="${isLoginUsed}"/>
        <c:set var="isPasswordIncorrect" value="${isPasswordIncorrect}"/>
        <form action="register" method="POST">
            <c:out value="${registerLabelUsername}:" /><br>
            <input type="text" name="username"/><br/>
            <c:out value="${registerLabelPassword}:" /><br>
            <input type="password" name="password"/><br/>
            <c:out value="${registerLabelRepeatPassword}:" /><br>
            <input type="password" name="password2"/><br/>
            <input type="submit" value="${registerButtonSubmit}"/>
        </form>
        <c:if test="${Error != null}">
            <p>"${Error}"</p>
        </c:if>
    </body>
</html>