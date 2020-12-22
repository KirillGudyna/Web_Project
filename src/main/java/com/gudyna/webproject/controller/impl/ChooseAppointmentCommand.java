package com.gudyna.webproject.controller.impl;

import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.AttributeKey;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.service.AppointmentService;
import com.gudyna.webproject.model.service.impl.AppointmentServiceImpl;
import com.gudyna.webproject.response.form.ResponseAppointmentData;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ChooseAppointmentCommand implements ActionCommand {

    private final AppointmentService appointmentService = AppointmentServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws IOException {
        Router router;
        int id = Integer.parseInt(request.getParameter(AttributeKey.CHOSEN_APPOINTMENT_ID));
        try {
            ResponseAppointmentData data = appointmentService.getAppointmentById(id);
            request.setAttribute(AttributeKey.CHOSEN_APPOINTMENT_DATA, data);
            request.setAttribute(AttributeKey.CHOSEN_APPOINTMENT_ID, id);
            router = new Router(PageName.UPDATE_CLOSE_PAGE.getPath());
        } catch (ServiceException e) {
            router = new Router(PageName.ERROR.getPath());
        }

        return router;
    }
}
