package com.gudyna.webproject.controller.impl;

import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.AttributeKey;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.service.DoctorService;
import com.gudyna.webproject.model.service.impl.DoctorServiceImpl;
import com.gudyna.webproject.response.form.ResponseUserData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetAllDoctorsCommand implements ActionCommand {

    private final DoctorService service = DoctorServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            List<ResponseUserData> doctors = service.getAllDoctors();
            request.setAttribute(AttributeKey.DOCTORS_ATTR, doctors);
            router = new Router(PageName.MAIN_PAGE.getPath());
        } catch (ServiceException e) {
            router = new Router(PageName.ERROR.getPath());
        }

        return router;
    }
}
