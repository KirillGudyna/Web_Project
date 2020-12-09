<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    <form action="ProcessController" method="POST">
        <input type="hidden" name="command" value="registration"/>
        <div class="form-group">
            <input type="text" name="name" required placeholder=<fmt:message key="registration.text.name"/>/><br/>
            <input type="text" name="surname" required placeholder=<fmt:message key="registration.text.surname"/>/><br/>
            <input type="text" name="email" required placeholder=<fmt:message key="registration.text.login"/>/><br/>
            <input type="password" name="password" required placeholder=<fmt:message key="registration.text.password"/>/><br/>
            <input type="password" name="repeatedPassword" required placeholder=<fmt:message key="registration.text.repeatpassword"/>/><br/>
            <input type="checkbox" id="doctor" name="doctor" onclick="chooseIsDoctor()"/><fmt:message key="registration.text.isdoctor"/><br/>
            <input type="text" id="keyDoctor" name="keyDoctor" style="display: none" placeholder=<fmt:message key="registration.text.doctorkey"/>/>
            <input type="text" id="jobType" name="jobType" style="display: none" placeholder=<fmt:message key="registration.text.jobType"/>/>
            <input type="text" id="timeWork" name="timeWork" style="display: none" placeholder=<fmt:message key="registration.text.timeWork"/>/>
            <input type="text" id="profession" name="profession" required placeholder=<fmt:message key="registration.text.profession"/>/><br/>
            <input type="text" id="address" name="address" required placeholder=<fmt:message key="registration.text.address"/>/><br/>
            <input type="date" id="dateOfBirth" name="dateOfBirth" required placeholder=<fmt:message key="registration.date.dateofbirth"/>/>
        </div>
        <button type="submit" id="k" class="btn btn-danger"><fmt:message key="registration.button.registration"/></button>
    </form>
    </body>
</fmt:bundle>
</html>