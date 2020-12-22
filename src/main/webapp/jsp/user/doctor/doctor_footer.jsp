<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/bootstrap.css" rel="stylesheet"/>
    </head>
    <body>
        <div class="navbar-fixed-bottom">
            <form action="ProcessController" method="POST">
                <input type="hidden" name="command" value="go_to_doctor_home"/>
                <button type="submit" class="btn btn-danger"><fmt:message key="common.back.button"/></button>
            </form>
        </div>
    </body>
</fmt:bundle>
</html>