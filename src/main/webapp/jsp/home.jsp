<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="current_page" scope="session" value="home"/>
<jsp:include page="hat.jsp" />

<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="home.title"/></title>
    </head>
    <body>
        <div class="navbar-text">
            <h1><fmt:message key = "home.text"/></h1>
        </div>
        <div class="navbar-fixed-bottom">
             <form action=ProcessController id="get_doctors" method="post" class="navbar-form navbar-left">
                <input type="hidden" name="command" value="get_doctors"/>
                <button type="submit" class="btn btn-danger"><fmt:message key="home.button"/></button>
             </form>
        </div>
    </body>
</fmt:bundle>
</html>
