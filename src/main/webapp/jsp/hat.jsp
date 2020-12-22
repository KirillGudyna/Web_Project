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
            <form action="ProcessController" method="POST" id="change_language" class="navbar-form navbar-left col-lg-1">
                <input type="hidden" name="command" value="change_language"/>
                <div class="form-group">
                    <label><fmt:message key="common.radio.label"/></label>
                    <input type="radio" id="en" name="locale" value="en_US" onclick="chooseLanguage()" /><fmt:message key="common.radio.english"/>
                    <input type="radio" id="ru" name="locale" value="ru_RU" onclick="chooseLanguage()" /><fmt:message key="common.radio.russian"/>
                </div>
            </form>
            <form action="ProcessController" method="POST" class="navbar-form navbar-left col-lg-3">
                <input type="hidden" name="command" value="go_to_login_page"/>
                <button  type="submit" class="btn btn-warning"><fmt:message key="common.login"/></button>
            </form>
        </div>
    </nav>
    </body>
</fmt:bundle>
</html>