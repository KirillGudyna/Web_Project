package com.gudyna.webproject.controller.impl;

import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.AttributeKey;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.service.UserService;
import com.gudyna.webproject.model.service.impl.UserServiceImpl;
import com.gudyna.webproject.response.ResponseWrapper;
import com.gudyna.webproject.response.form.ResponseUserData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogInCommand implements ActionCommand {
    private static final String BUTTON_ON = "on";

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        try {
            ResponseUserData response = userService.authorizeUser(request.getParameter("email"), request.getParameter("password"), isDoctor(request.getParameter("isdoctor")));
            request.setAttribute("response",ResponseWrapper.setSuccess("User successfully authorized", response));
            session.setAttribute(AttributeKey.USER,response);
            router =new Router("main page");
        } catch (ServiceException e) {
            request.setAttribute("response",ResponseWrapper.setError("Unable to authorize user" + e.getMessage()));
            router = new Router(PageName.ERROR.getPath());
        }
        return router;
    }
    private boolean isDoctor(String parameter){
        return BUTTON_ON.equals(parameter);
    }
}
