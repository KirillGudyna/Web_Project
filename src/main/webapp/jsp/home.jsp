<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:set var="current_page" scope="session" value="home"/>
<%@include file="fragment/hat.jsp" %>
<!DOCTYPE html>
<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="index.title"/></title>
    </head>
    <body>
    <c:set var="locale" scope="session" value="en_US"/>
    <h1><fmt:message key = "index.text"/></h1>
    <form action=ProcessController id="get_doctors" method="post">
        <input type="hidden" name="command" value="get_doctors"/>
        <button type="submit" class="btn btn-danger"><fmt:message key="index.button"/></button>
    </form>
    </body>
</fmt:bundle>
</html>
