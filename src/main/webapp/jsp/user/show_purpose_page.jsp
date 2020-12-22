<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="user_hat.jsp"/>

<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
    <head>
    <link type="text/css" href="css/bootstrap.css" rel="stylesheet"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="purpose.page"/></title>
    </head>
<body>
    <c:set var="purposes" value="${purpose_to_appointment}" scope="request"/>
    <h1 class="alert-info"><fmt:message key="table.requests.column.diagnosis"/> ${purposes.getPurposeData().getDiagnosis()}</h1>
    <table class="table table-striped table-hover table-bordered">
        <caption><fmt:message key="table.doctors.drugs"/></caption>
        <tr>
            <th><b><fmt:message key="table.purpose.drugs.column.name"/></b></th>
            <th><b><fmt:message key="table.purpose.drugs.column.amount"/></b></th>
            <th><b><fmt:message key="table.purpose.drugs.column.daytaking"/></b></th>
        </tr>
        <c:forEach var="item" items="${purposes.getDrugsData()}">
            <tr>
                <td>
                    <c:out value="${item.getName()}"/>
                </td>
                <td>
                    <c:out value="${item.getAmount()}"/>
                </td>
                <td>
                    <c:out value="${item.getTermTaking()}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <table class="table table-striped table-hover table-bordered">
    <caption><fmt:message key="table.doctors.procedures"/></caption>
    <tr>
        <th><b><fmt:message key="table.procedure.column.name"/></b></th>
        <th><b><fmt:message key="table.procedure.column.date.start"/></b></th>
        <th><b><fmt:message key="table.procedure.column.date.end"/></b></th>
    </tr>
    <c:forEach var="item" items="${purposes.getProceduresData()}">
        <tr>
            <td>
                <c:out value="${item.getName()}"/>
            </td>
            <td>
                <c:out value="${item.getDateStart()}"/>
            </td>
            <td>
                <c:out value="${item.getDateEnd()}"/>
            </td>
        </tr>
    </c:forEach>
    </table>
    <c:set var="user_type" value="${user_type}" scope="session"/>
    <div class="navbar-fixed-bottom">
        <c:choose>
            <c:when test="${user_type=='patient'}">
                <form action="ProcessController" method="POST">
                    <input type="hidden" name="command" value="go_to_patient_home"/>
                    <button type="submit" class="btn btn-danger"><fmt:message key="common.back.button"/></button>
                </form>
            </c:when>
            <c:otherwise>
                <form action="ProcessController" method="POST">
                    <input type="hidden" name="command" value="go_to_doctor_home"/>
                    <button type="submit" class="btn btn-danger"><fmt:message key="common.back.button"/></button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</fmt:bundle>
</html>
