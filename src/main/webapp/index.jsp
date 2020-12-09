<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="jsp/fragment/hat.jsp" %>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="index.title"/></title>
    </head>
    <body>
        <c:set var="locale" scope="session" value="en_US"/>
        <jsp:forward page="jsp/doctor_home.jsp"/>
    </body>
</fmt:bundle>
</html>
