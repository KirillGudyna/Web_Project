<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../user_hat.jsp"/>
<jsp:include page="doctor_footer.jsp"/>

<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
    <head>
        <title><fmt:message key="doctor.closed.appointments.title"/></title>
        <link type="text/css" href="css/bootstrap.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
<body>
    <form action="ProcessController" method="POST" id="show_purpose">
        <input type="hidden" name="command" value="show_purpose"/>
    </form>
    <c:set var="appointments" value="${appointments}" scope="session"/>
    <c:choose>
        <c:when test="${appointments.size() > 0}">
            <h2><fmt:message key="table.closed.appointments.caption"/></h2>
            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <th><b><fmt:message key="table.appointments.column.id"/></b></th>
                    <th><b><fmt:message key="table.appointments.column.price"/></b></th>
                    <th><b><fmt:message key="table.appointments.column.date"/></b></th>
                    <th><b><fmt:message key="table.appointments.column.patient.name"/></b></th>
                </tr>
                <c:forEach var="item" items="${appointments}">
                    <tr>
                        <td>
                            <input type="radio" form="show_purpose" name="chosen_appointment" value="${item.getId()}" checked />
                            <c:out value="${item.getId()}"/>
                        </td>
                        <td>
                            <c:out value="${item.getPrice()}"/>
                        </td>
                        <td>
                            <c:out value="${item.getDateTime()}"/>
                        </td>
                        <td>
                            <c:out value="${item.getPatientName()}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <input form="show_purpose" type="submit" class="btn btn-primary" value=<fmt:message key="doctor.button.show.purpose"/> />
        </c:when>
        <c:otherwise>
            <h3><fmt:message key="no.closed.appointments.message"/></h3>
        </c:otherwise>
    </c:choose>
    <div class="navbar-fixed-bottom">
        <form action="ProcessController" method="POST">
            <input type="hidden" name="command" value="go_to_doctor_home"/>
            <button type="submit" class="btn btn-danger"><fmt:message key="common.back.button"/></button>
        </form>
    </div>
</body>
</fmt:bundle>
</html>
