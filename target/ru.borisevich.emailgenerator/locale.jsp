<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="langEng" value="english" />
<c:set var="langRus" value="russian" />
<c:if test="${sessionScope.language == langEng}">
       <fmt:setBundle basename="text_en" var="lang"/>
</c:if>
<c:if test="${sessionScope.language == langRus}">
       <fmt:setBundle basename="text_ru_CYRILLIC" var="lang"/>
</c:if>
<c:if test="${sessionScope.language == null}">
       <fmt:setBundle basename="text_en" var="lang"/>
</c:if>

<fmt:message key="edit_addresses.label.header" bundle="${lang}" var="headerEditAddresses" />
<fmt:message key="edit_addresses.button.submitchanges" bundle="${lang}" var="submitChangesButton" />
<fmt:message key="edit_addresses.button.add" bundle="${lang}" var="addButton" />
<fmt:message key="edit_addresses.button.addNewAddress" bundle="${lang}" var="addNewAddress"/>
<fmt:message key="edit_addresses.label.name" bundle="${lang}" var="edit_addressesLabelName"/>
<fmt:message key="edit_addresses.label.address" bundle="${lang}" var="edit_addressesLabelAddress"/>

<fmt:message key="generation.label.header" bundle="${lang}" var="generationHeader"/>
<fmt:message key="generation.label.trackInfo" bundle="${lang}" var="generationLabelTrackInfo"/>
<fmt:message key="generation.label.generatedText" bundle="${lang}" var="generationLabelGeneratedText"/>
<fmt:message key="generation.button.generate" bundle="${lang}" var="generateButton"/>
<fmt:message key="generation.button.send" bundle="${lang}" var="sendButton"/>

<fmt:message key="menu.label.header" bundle="${lang}" var="menuLabelHeader"/>
<fmt:message key="menu.button.myaccount" bundle="${lang}" var="menuButtonMyAccount"/>
<fmt:message key="menu.button.generation" bundle="${lang}" var="menuButtonGeneration"/>
<fmt:message key="menu.button.editaddresses" bundle="${lang}" var="menuButtonEditAddresses"/>
<fmt:message key="menu.button.edittemplates" bundle="${lang}" var="menuButtonEditTemplates"/>

<fmt:message key="myaccount.label.header" bundle="${lang}" var="myaccountLabelHeader"/>
<fmt:message key="myaccount.label.emailhistory" bundle="${lang}" var="myaccountLabelEmailHistory"/>

<fmt:message key="register.label.header" bundle="${lang}" var="registerLabelHeader"/>
<fmt:message key="register.label.username" bundle="${lang}" var="registerLabelUsername"/>
<fmt:message key="register.label.password" bundle="${lang}" var="registerLabelPassword"/>
<fmt:message key="register.label.repeatPassword" bundle="${lang}" var="registerLabelRepeatPassword"/>
<fmt:message key="register.button.submit" bundle="${lang}" var="registerButtonSubmit"/>