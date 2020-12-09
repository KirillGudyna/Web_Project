<%@ page import="com.gudyna.webproject.model.entity.AppointmentData" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
    <head>
    <c:set var="current_page" scope="session" value="main_page"/>
    <title><fmt:message key="doctor.main.title"/></title>
    <link type="text/css" href="css/bootstrap.css" rel="stylesheet"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
<body>
    <form action="ProcessController" method="POST" id="choose_appointment">
        <input type="hidden" name="command" value="choose_appointment"/>
    </form>
    <c:set var="appointments" value="${appointments}" scope="session"/>
    <table class="table table-striped table-hover table-bordered">
        <caption><fmt:message key="table.doctors.caption"/></caption>
        <tr>
            <th><b><fmt:message key="table.requests.column.id"/></b></th>
            <th><b><fmt:message key="table.requests.column.price"/></b></th>
            <th><b><fmt:message key="table.requests.column.date.time"/></b></th>
            <th><b><fmt:message key="table.requests.column.patient.id"/></b></th>
            <th><b><fmt:message key="table.requests.column.is.closed"/></b></th>
        </tr>
        <c:forEach var="item" items="${appointments}">
            <tr>
                <td>
                    <input type="radio" form="choose_appointment" name="chosen" value="${item.getId()}" checked />
                    <c:out value="${item.getId()}"/>
                </td>
                <td>
                    <c:out value="${item.getPrice()}"/>
                </td>
                <td>
                    <c:out value="${item.getDateTime()}"/>
                </td>
                <td>
                    <c:out value="${item.getPatientId()}"/>
                </td>
                <td>
                    <c:out value="${item.getIsClosed()}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input  form="choose_appointment" type="submit" class="btn btn-primary" value=<fmt:message key="choose_appointment.button.choose"/>/>
</body>
</fmt:bundle>
</html>
