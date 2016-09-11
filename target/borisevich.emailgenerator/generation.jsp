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

        <form action="generate" method="GET">
            <c:out value="${generationLabelTrackInfo}:" /><br>
            <input type="text" name="trackInfo"/><br/>
            <c:forEach var="par" items="${addressList}">
                <input type="checkbox" name="address" value="${par.getName()}" />
                <c:out value="${par.getName()}"/>
                <br>
            </c:forEach>
            <input type="submit" value="${generateButton}"/>
        </form>

        <textarea name="generatedMail" rows="10" cols="30">"${generationLabelGeneratedText}"</textarea>
    </body>
</html>