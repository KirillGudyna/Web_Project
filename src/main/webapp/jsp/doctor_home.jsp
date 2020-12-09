<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="fragment/hat.jsp" %>

<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
    <head>
        <title><fmt:message key="doctor.main.title"/></title>
    </head>
    <body>
    <c:set var="user_id" value="12" scope="session"/>
    <form action=ProcessController id="get_doctors" method="post">
        <input type="hidden" name="command" value="get_doctors"/>
        <button type="submit" class="btn btn-danger"><fmt:message key="index.button"/></button>
    </form>
    <form action="ProcessController" id="get_open_appointment" method="POST">
        <input type = "hidden" name="command" value="get_open_appointments">
        <button type="submit" class="btn btn-danger"><fmt:message key="doctor.main.button.open.appointment"/></button>
    </form>
    <form action="ProcessController" id="get_close_appointment" method="POST">
        <input type = "hidden" name="command" value="get_close_appointments">
        <button type="submit" class="btn btn-danger"><fmt:message key="doctor.main.button.close.appointment"/></button>
    </form>
    </body>
</fmt:bundle>
</html>
