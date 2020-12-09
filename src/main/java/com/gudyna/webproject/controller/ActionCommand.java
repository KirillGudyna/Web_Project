package com.gudyna.webproject.controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ActionCommand {
    Router execute(HttpServletRequest request) throws IOException;
}
