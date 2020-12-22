<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../user_hat.jsp"/>
<jsp:include page="doctor_footer.jsp"/>

<html>
    <fmt:setLocale value="${locale}" scope="session"/>
    <fmt:bundle basename="property.gui">
        <head>
            <title><fmt:message key="doctor.update.appointment.title"/></title>
        </head>
        <body>
            <h2 class="text-warning"><fmt:message key="doctor.update.appointment.text"/></h2>
            <form action="ProcessController?command=update_appointment" id="update_appointment" method="POST" class="navbar-form left">
                <input type="hidden" name="chosen_appointment" value="${chosen_appointment}"/>
                <h3><fmt:message key="doctor.update.appointment.price"/></h3>
                <input type="text" name="price" value="${chosen_appointment_data.getPrice()}"/><br/>
                <h3><fmt:message key="doctor.update.appointment.date"/></h3>
                <input type="date" name="dateTime" value="${chosen_appointment_data.getDateTime()}"/><br/>
                <h3><fmt:message key="doctor.update.appointment.patient"/></h3>
                <input type="text" name="patientName" value="${chosen_appointment_data.getPatientName()}" disabled/><br/><br/>
                <button type="submit" class="btn btn-danger"><fmt:message key="doctor.update.appointment.button.update"/></button>
            </form>
            <form action="ProcessController?command=close_appointment" id="get_close_appointment" method="POST" class="navbar-form left">
                <input type="hidden" name="chosen_appointment" value="${chosen_appointment}"/>
                <button type="submit" class="btn btn-info"><fmt:message key="doctor.update.appointment.button.close"/></button>
            </form>
        </body>
    </fmt:bundle>
</html>
