<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Account</title>
    </head>
    <body>
        <h1>My Account</h1>

            Email history:<br>
            <c:forEach var="par" items="${emailList}">
                <c:out value="${par.getAddress()}"/>
            </c:forEach>
    </body>
</html>