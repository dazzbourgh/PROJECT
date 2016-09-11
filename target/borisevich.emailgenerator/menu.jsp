<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="locale.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${menuLabelHeader}</title>
    </head>
    <body>
        <h1>${menuLabelHeader}</h1>

        <form action="myaccount" method="GET">
            <input type="submit" value="${menuButtonMyAccount}"/>
        </form>
        <form action="generationFormLoader" method="POST">
            <input type="submit" value="${menuButtonGeneration}"/>
        </form>
        <form action="editAddressesLoader" method="GET">
            <input type="submit" value="${menuButtonEditAddresses}"/>
        </form>
    </body>
</html>