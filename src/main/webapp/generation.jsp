<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generation</title>
    </head>
    <body>
        <h1>Email Generator</h1>

        <form action="generate" method="GET">
            Track info:<br>
            <input type="text" name="trackInfo"/><br/>
            <c:forEach var="par" items="${addressList}">
                <input type="checkbox" name="address" value="${par.getName()}" />
                <c:out value="${par.getName()}"/>
                <br>
            </c:forEach>
            <input type="submit" />
        </form>

        <textarea name="generatedMail" rows="10" cols="30">Generated mail will be shown here</textarea>
    </body>
</html>