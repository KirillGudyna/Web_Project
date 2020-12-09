<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="property.gui">
    <body>
    <h2><fmt:message key="hat.text"/></h2>
    <script src="js/functions.js"></script>
    <form action="ProcessController" method="POST" id="change_language">
        <input type="hidden" name="command" value="change_language"/>
        <div class="form-group">
            <label><fmt:message key="login.radio.label"/></label>
            <input type="radio" id="en" name="locale" value="en_US" onclick="chooseLanguage()" /><fmt:message key="login.radio.english"/>
            <input type="radio" id="ru" name="locale" value="ru_RU" onclick="chooseLanguage()" /><fmt:message key="login.radio.russian"/>
        </div>
    </form>
    </body>
</fmt:bundle>
</html>
