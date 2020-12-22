<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="hat.jsp"/>

<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
<head>
    <c:set var="current_page" scope="session" value="main_page"/>
    <title><fmt:message key="main.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <c:set var="doctors" value="${doctors}" scope="session"/>
    <h1 class="h1"><fmt:message key="table.doctors.caption"/></h1>
    <c:choose>
        <c:when test="${doctors.size() > 0}">
          <table class="table table-striped table-hover table-bordered">
            <tr>
                <th><b><fmt:message key="table.doctors.column.id"/></b></th>
                <th><b><fmt:message key="table.doctors.column.name"/></b></th>
                <th><b><fmt:message key="table.doctors.column.surname"/></b></th>
                <th><b><fmt:message key="table.doctors.column.timework"/></b></th>
                <th><b><fmt:message key="table.doctors.column.jobtype"/></b></th>
                <th><b><fmt:message key="table.doctors.column.min.price"/></b></th>
                <th><b><fmt:message key="table.doctors.column.max.appointments"/></b></th>
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
                    <td>
                        <c:out value="${item.getMaxAppPerDay()}"/>
                    </td>
                    <td>
                        <c:out value="${item.getMinPrice()}"/>
                    </td>
                </tr>
            </c:forEach>
          </table>
        </c:when>
        <c:otherwise>
            <fmt:message key="main.empty.doctors.message"/>
        </c:otherwise>
    </c:choose>

    <form action="ProcessController" method="POST">
      <input type="hidden" name="command" value="go_to_home"/>
      <button type="submit" class="btn btn-danger"><fmt:message key="common.back.button"/></button>
     </form>
</body>
</fmt:bundle>
</html>
