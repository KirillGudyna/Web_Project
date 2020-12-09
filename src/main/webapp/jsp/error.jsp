<%--
  Created by IntelliJ IDEA.
  User: kiril
  Date: 07.12.2020
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="resources.gui">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="errorpage.title"/></title>
        <style type="text/css">
            <%@include file="../css/bootstrap.css" %>
        </style>
    </head>
    <body>
    <h1 class="text-danger"><fmt:message key="errorpage.message"/></h1>
    </body>
</fmt:bundle>
</html>
