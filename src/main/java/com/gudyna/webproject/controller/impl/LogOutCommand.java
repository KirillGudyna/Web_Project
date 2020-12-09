package com.gudyna.webproject.controller.impl;

import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return new Router("home");
    }
}
