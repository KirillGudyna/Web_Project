package com.gudyna.webproject.controller.impl;

import com.google.gson.Gson;
import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.AttributeKey;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.service.UserService;
import com.gudyna.webproject.model.service.impl.UserServiceImpl;
import com.gudyna.webproject.request.form.RequestUserRegistrationData;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements ActionCommand {

    private static final String ON_NAME = "on";
    private static final String WRONG_KEY_MESSAGE = "Wrong key";

    private final UserService userService = UserServiceImpl.getInstance();
    private final Gson gson = new Gson();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String requestAsJson = gson.toJson(request.getParameterMap()).replaceAll("\\[", "").replace("]", "");
        RequestUserRegistrationData userData = gson.fromJson(requestAsJson, RequestUserRegistrationData.class);
        userData.setDoctor(ON_NAME.equals(request.getParameter(AttributeKey.DOCTOR)));
        try {
            userService.addUser(userData);
            request.setAttribute(AttributeKey.REGISTRATION_RESPONSE_MESSAGE, "registration.response.success");
            router = new Router(PageName.LOGIN.getPath());
        } catch (ServiceException e) {
            String errorMessageCode = WRONG_KEY_MESSAGE.equals(e.getMessage())
                    ? "registration.response.wrong.key"
                    : "registration.response.error";
            request.setAttribute(AttributeKey.REGISTRATION_RESPONSE_MESSAGE, errorMessageCode);

            router = new Router(PageName.REGISTER.getPath());
        }

        return router;
    }
}
