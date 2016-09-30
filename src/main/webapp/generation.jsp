<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="locale.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${generationHeader}</title>
        <style type="text/css"> <%@include file="/css/styles.css" %> </style>
                                <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
                                <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
                                <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
            red    {color: red;}
            </style>
    </head>
    <body class="bg-blue">

    <jsp:directive.include file="header_top.jsp" />
    <div class="vertical-center bg-blue">
    <div class="form-group well bg-white" align="center">
        <h1>${generationHeader}</h1>


        <form action="generateServlet" method="GET">
            <c:out value="${generationLabelTrackInfo}:" /><br>
            ${generationLabelAuthor}:<br> <input type="text" name="author" /><br>
            ${generationLabelTitle}: <br> <input type="text" name="title" /> <br>
            ${generationLabelStyle}: <br> <input type="text" name="style" /> <br>
            ${generationLabelBpm}:   <br> <input type="text" name="bpm" />   <br>
            ${generationLabelLink}:  <br> <input type="text" name="link" />  <br>
            ${generationLabelName}:  <br> <input type="text" name="name" />  <br>

            ${generationLabelTrackInfo}:<br>
            <textarea name="trackInfo" rows="10" cols="30"></textarea>
            <br>
            <c:forEach var="par" items="${addressList}">
                <input type="checkbox" name="address" value="${par.getName()}" />
                <c:out value="${par.getName()}"/>
                <br>
            </c:forEach>
            <input type="submit" class="btn btn-default btn-margin" value="${generateButton}"/>
        </form>

        <form action="sendServlet" method="POST">
            ${generationLabelLogin}: <br>
            <input type="text" class="text-margin" name="emailUsername"/><br/>
            ${generationLabelPassword}: <br>
            <input type="password" class="text-margin" name="emailPassword"/><br/>
            <input type="submit" class="btn btn-default btn-margin" value="${sendButton}"/>
        </form>

        <c:if test="${emailList != null}">
            <textarea name="generatedMail" rows="10" cols="30">${emailList.get(0).getText()}</textarea>
        </c:if>
        <c:if test="${emailList == null}">
            <textarea name="generatedMail" rows="10" cols="30">${generationLabelGeneratedText}</textarea>
        </c:if>
        <br>
        <c:if test="${Error != null}">
                    <red>${Error}</red>
        </c:if>
        </div>
    </div>
    </body>
</html>