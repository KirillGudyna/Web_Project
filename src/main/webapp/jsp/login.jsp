<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
    <head>
        <link type="text/css" href="css/bootstrap.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="login.title"/></title>
    </head>
    <body>
    <c:set var="current_page" scope="session" value="login"/>
    <form action="ProcessController" method="POST">
        <input type="hidden" name="command" value="login"/>
        <div class="form-group">
            <input type="text" name="email" placeholder=<fmt:message key="login.text.login"/>/><br/>
            <input type="password" name="password" placeholder=<fmt:message key="login.text.password"/>/><br/>
            <input type = "checkbox" id="isdoctor" name="isdoctor"/><fmt:message key = "login.checkbox.doctor"/>
        </div>
        <button type="submit" class="btn btn-info"><fmt:message key="login.button"/></button>
    </form>
    <form action="ProcessController" method="POST">
        <input type="hidden" name="command" value="go_registration"/>
        <button  type="submit" class="btn btn-danger"><fmt:message key="login.button.registration"/></button>
    </form>
    </body>
</fmt:bundle>
</html>