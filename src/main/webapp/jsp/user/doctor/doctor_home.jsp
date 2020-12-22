<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../user_hat.jsp"/>

<html>
    <fmt:bundle basename="property.gui">
        <head>
            <title><fmt:message key="doctor.main.title"/></title>
        </head>
        <body>
             <div class="navbar-left">
                <form action="ProcessController" id="get_open_appointment" method="POST">
                    <input type = "hidden" name="command" value="get_open_appointments">
                    <button type="submit" class="btn btn-danger">
                        <fmt:message key="doctor.main.button.open.appointment"/>
                    </button>
                </form>
                <form action="ProcessController" id="get_close_appointment" method="POST">
                    <input type = "hidden" name="command" value="get_close_appointments">
                    <button type="submit" class="btn btn-danger">
                        <fmt:message key="doctor.main.button.close.appointment"/>
                    </button>
                </form>
             </div>
        </body>
    </fmt:bundle>
</html>
