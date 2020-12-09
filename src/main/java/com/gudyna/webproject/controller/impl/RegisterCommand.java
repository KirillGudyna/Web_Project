package com.gudyna.webproject.controller.impl;

import com.google.gson.Gson;
import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.service.UserService;
import com.gudyna.webproject.model.service.impl.UserServiceImpl;
import com.gudyna.webproject.request.form.RequestUserRegistrationData;
import com.gudyna.webproject.response.ResponseWrapper;
import com.gudyna.webproject.response.form.ResponseUserData;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RegisterCommand implements ActionCommand {
    private static final String ON_NAME = "on";

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws IOException {
        Gson gson = new Gson();
        Router router;
        RequestUserRegistrationData userData = gson.fromJson(gson.toJson(request.getParameterMap()).replaceAll("\\[","").replaceAll("\\]","") , RequestUserRegistrationData.class);
        userData.setDoctor(ON_NAME.equals(request.getParameter("doctor")));
        try {
            ResponseUserData response = userService.addUser(userData);
            request.setAttribute("response",ResponseWrapper.setSuccess("User successfully added", response));
            router = new Router("main page");
        } catch (ServiceException e) {
            request.setAttribute("response",ResponseWrapper.setError("Unable to create user" + e.getMessage()));
            router = new Router(PageName.ERROR.getPath());
        }
        return router;
    }
}
