<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="hat.jsp"/>
<c:set var="current_page" scope="session" value="register"/>

<html>
    <fmt:setLocale value="${locale}" scope="session"/>
    <fmt:bundle basename="property.gui">
        <head>
            <link type="text/css" href="css/bootstrap.css" rel="stylesheet"/>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="registration.page"/></title>
        </head>
        <body>
            <script src="js/functions.js"></script>
            <div class="navbar-left">
                <h2 class="text-danger">
                    <c:choose>
                       <c:when test="${not empty registration_response}">
                        <fmt:message key="${registration_response}"/>
                       </c:when>
                    </c:choose>
                </h2>
                <h4><fmt:message key="registration.text.enter.data"/></h4>
                <form action="ProcessController" method="POST" class="navbar-form navbar-left" onsubmit="return validForm()">
                    <input type="hidden" name="command" value="registration"/>
                    <input type="text" name="name" required placeholder=<fmt:message key="registration.text.name"/>/><br/>
                    <input type="text" name="surname" required placeholder=<fmt:message key="registration.text.surname"/>/><br/>
                    <input type="email" name="email" required placeholder=<fmt:message key="registration.text.email"/>/><br/>
                    <input type="password" id="password" name="password" required placeholder=<fmt:message key="registration.text.password"/>/><br/>
                    <input type="password" id="repeatedPassword" name="repeatedPassword" required placeholder=<fmt:message key="registration.text.repeatpassword"/>/><br/>
                    <input type="checkbox" id="doctor" name="doctor" onclick="chooseIsDoctor()"/><fmt:message key="registration.text.isdoctor"/><br/>
                    <input type="text" id="keyDoctor" name="keyDoctor" style="display: none" placeholder=<fmt:message key="registration.text.doctorkey"/>/>
                    <input type="text" id="jobType" name="jobType" style="display: none" placeholder=<fmt:message key="registration.text.jobType"/>/>
                    <input type="text" id="timeWork" name="timeWork" style="display: none" placeholder=<fmt:message key="registration.text.timeWork"/>/>
                    <input type="number" id="maxAppPerDay" value="0" name="maxAppPerDay" style="display: none" placeholder=<fmt:message key="registration.text.maxAppPerDay"/>/>
                    <input type="number" id="minPrice" name="minPrice" value="0" style="display: none" placeholder=<fmt:message key="registration.text.minPrice"/>/>
                    <input type="text" id="profession" name="profession" required placeholder=<fmt:message key="registration.text.profession"/> /><br/>
                    <input type="text" id="address" name="address" required placeholder=<fmt:message key="registration.text.address"/>/><br/>
                    <input  type="date" id="dateOfBirth" name="dateOfBirth" required><fmt:message key="registration.date.dateofbirth"/></input><br/>
                    <button type="submit" id="registerButton" class="btn btn-info"><fmt:message key="registration.button.register"/></button>
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