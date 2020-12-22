package com.gudyna.webproject.controller.impl.pagecommand;

import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GoToPatientHomePageCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) throws IOException {
        return new Router(PageName.PATIENT_HOME.getPath());
    }
}
