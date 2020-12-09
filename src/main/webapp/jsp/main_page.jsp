<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
<head>
    <c:set var="current_page" scope="session" value="main_page"/>
    <title><fmt:message key="main.title"/></title>
    <link type="text/css" href="css/bootstrap.css" rel="stylesheet"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<c:set var="doctors" value="${doctors}" scope="session"/>
<table class="table table-striped table-hover table-bordered">
    <caption><fmt:message key="table.doctors.caption"/></caption>
    <tr>
        <th><b><fmt:message key="table.requests.column.id"/></b></th>
        <th><b><fmt:message key="table.requests.column.name"/></b></th>
        <th><b><fmt:message key="table.requests.column.surname"/></b></th>
        <th><b><fmt:message key="table.requests.column.timework"/></b></th>
        <th><b><fmt:message key="table.requests.column.jobtype"/></b></th>
    </tr>
    <c:forEach var="item" items="${doctors}">
        <tr>
            <td>
                <c:out value="${item.getId()}"/>
            </td>
            <td>
                <c:out value="${item.getName()}"/>
            </td>
            <td>
                <c:out value="${item.getSurname()}"/>
            </td>
            <td>
                <c:out value="${item.getTimeWork()}"/>
            </td>
            <td>
                <c:out value="${item.getJobType()}"/>
            </td>
        </tr>
        </c:forEach>
</table>
<form action="ProcessController" method="POST">
    <input type="hidden" name="command" value="home"/>
    <button type="submit" class="btn btn-danger"><fmt:message key="main.back.button"/></button>
</form>
</body>
</fmt:bundle>
</html>
