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
        <style type="text/css"> <%@include file="/css/styles.css" %> </style>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body class="bg-blue">
    <div class="vertical-center bg-blue">
                <div class="form-group well bg-white" align="center">
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
            <input type="submit" value="${registerButtonSubmit}" class="btn btn-default btn-margin"/>
        </form>
        <c:if test="${Error != null}">
            <p>${Error}</p>
        </c:if>
        </div>
        </div>
    </body>
</html>