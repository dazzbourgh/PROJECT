<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="locale.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${generationHeader}</title>
    </head>
    <body>
        <h1>${generationHeader}</h1>

        <form action="generateServlet" method="GET">
            <c:out value="${generationLabelTrackInfo}:" /><br>
            <input type="text" name="trackInfo"/><br/>
            <c:forEach var="par" items="${addressList}">
                <input type="checkbox" name="address" value="${par.getName()}" />
                <c:out value="${par.getName()}"/>
                <br>
            </c:forEach>
            <input type="submit" value="${generateButton}"/>
        </form>

        <form action="sendServlet" method="POST">
            Login: <input type="text" name="emailUsername"/><br/>
            Password: <input type="password" name="emailPassword"/><br/>
            <input type="submit" value="${sendButton}"/>
        </form>

        <c:if test="${emailList != null}">
            <textarea name="generatedMail" rows="10" cols="30">${emailList.get(0).getText()}</textarea>
        </c:if>
        <c:if test="${emailList == null}">
            <textarea name="generatedMail" rows="10" cols="30">${generationLabelGeneratedText}</textarea>
        </c:if>
    </body>
</html>