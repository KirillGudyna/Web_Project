package com.gudyna.webproject.controller.impl;

import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.AttributeKey;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.service.UserService;
import com.gudyna.webproject.model.service.impl.UserServiceImpl;
import com.gudyna.webproject.response.form.ResponseUserData;
import com.gudyna.webproject.util.ParameterKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogInCommand implements ActionCommand {
    private static final String BUTTON_ON = "on";

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            String email = request.getParameter(ParameterKey.EMAIL_PARAM);
            String password = request.getParameter(ParameterKey.PASSWORD_PARAM);
            boolean isDoctor = isDoctor(request.getParameter(ParameterKey.IS_DOCTOR_PARAM));
            ResponseUserData responseUser = userService.authorizeUser(email, password, isDoctor);
            HttpSession session = request.getSession();
            session.setAttribute(ParameterKey.USER_ID, responseUser.getId());
            session.setAttribute(ParameterKey.USER_NAME, responseUser.getName() + " " + responseUser.getSurname());
            if (isDoctor) {
                session.setAttribute(ParameterKey.USER_TYPE, AttributeKey.DOCTOR);
                router = new Router(PageName.DOCTOR_HOME.getPath());
            } else {
                session.setAttribute(ParameterKey.USER_TYPE, AttributeKey.PATIENT);
                router = new Router(PageName.PATIENT_HOME.getPath());
            }
        } catch (ServiceException e) {
            request.setAttribute(AttributeKey.LOGIN_RESPONSE_MESSAGE, "login.error.message");
            router = new Router(PageName.LOGIN.getPath());
        }

        return router;
    }

    private boolean isDoctor(String parameter) {
        return BUTTON_ON.equals(parameter);
    }
}
