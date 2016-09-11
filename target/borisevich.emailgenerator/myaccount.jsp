<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="locale.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${myaccountLabelHeader}</title>
    </head>
    <body>
        <h1>${myaccountLabelHeader}</h1>

            <c:out value="${myaccountLabelEmailHistory}:" /><br>
            <c:set var="i" value="${1}" />
            <c:forEach var="par" items="${emailList}">
                <c:out value="${i}. To: ${par.getAddress()}"/>
                <c:set var="i" value="${i+1}" />
            </c:forEach>
    </body>
</html>