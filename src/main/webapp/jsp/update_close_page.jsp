<%@ page import="com.gudyna.webproject.model.entity.AppointmentData" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
    <head>
        <title><fmt:message key="choose_appointment.button.choose"/></title>
    </head>
    <body>
    <form action="ProcessController" id="update_appointment" method="POST">
        <input type="hidden" name="command" value="update_appointment">
        <input type="text" name="price" value="${appointments}"/>
        <button type="submit" class="btn btn-danger"><fmt:message key="doctor.main.button.update"/></button>
    </form>
    <form action="ProcessController" id="get_close_appointment" method="POST">
        <input type="hidden" name="command" value="get_close_appointments">
        <button type="submit" class="btn btn-danger"><fmt:message key="doctor.main.button.close"/></button>
    </form>
    </body>
</fmt:bundle>
</html>
