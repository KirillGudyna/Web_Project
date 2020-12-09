package com.gudyna.webproject.controller.impl;

import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PageName.ERROR.getPath());
    }
}
