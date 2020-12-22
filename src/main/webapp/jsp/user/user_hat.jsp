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
    <script src="js/functions.js">
    </script>
    <nav class="navbar navbar-default">
        <div class="navbar-header">
            <a class="navbar-brand"><fmt:message key="common.hospital"/></a>
        </div>
        <div class="navbar-right">
            <form action="ProcessController?command=logout" method="POST" class="navbar-form navbar-left col-lg-3">
                ${user_name}
                <button  type="submit" class="btn btn-warning"><fmt:message key="common.logout"/></button>
            </form>
        </div>
    </nav>
    </body>
</fmt:bundle>
</html>