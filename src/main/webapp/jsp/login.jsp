<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="hat.jsp"/>
<c:set var="current_page" scope="session" value="login"/>

<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
    <head>
        <link type="text/css" href="css/bootstrap.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="login.title"/></title>
    </head>

    <body>
    <div class="navbar-left">
        <h1 class="text-danger">
            <c:choose>
                <c:when test="${not empty login_error_message}">
                    <fmt:message key="${login_error_message}"/>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${not empty registration_response}">
                    <fmt:message key="${registration_response}"/>
                </c:when>
            </c:choose>
        </h1>
        <h3><fmt:message key="login.text.enter.credentials"/></h3>
        <form method="POST" class="navbar-form navbar-left">
            <input type="email" class="text-primary" name="email" placeholder=
                <fmt:message key="login.text.login"/>/><br/><br/>
            <input type="password" class="text-primary" name="password" placeholder=<fmt:message
                    key="login.text.password"/>/><br/>
            <input type="checkbox" id="isdoctor" name="isdoctor"/><fmt:message key="login.checkbox.doctor"/><br/>
            <button type="submit" class="btn btn-warning" formaction="ProcessController?command=login">
                <fmt:message key="login.button"/>
            </button>
            <button type="submit" class="btn btn-success" formaction="ProcessController?command=go_registration">
                <fmt:message key="login.button.registration"/>
            </button>
        </form>
    </div>
    <div class="navbar-fixed-bottom">
        <form action="ProcessController" method="POST">
            <input type="hidden" name="command" value="go_to_home"/>
            <button type="submit" class="btn btn-danger"><fmt:message key="common.back.button"/></button>
        </form>
    </div>
    </body>
</fmt:bundle>
</html>