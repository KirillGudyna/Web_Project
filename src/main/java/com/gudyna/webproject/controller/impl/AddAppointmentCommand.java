package com.gudyna.webproject.controller.impl;

import com.google.gson.Gson;
import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.entity.AppointmentData;
import com.gudyna.webproject.model.service.AppointmentService;
import com.gudyna.webproject.model.service.impl.AppointmentServiceImpl;
import com.gudyna.webproject.response.ResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

public class AddAppointmentCommand implements ActionCommand {
    AppointmentService appointmentService = AppointmentServiceImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request) throws IOException {
        Gson gson = new Gson();
        Router router;
        AppointmentData appointmentData = gson.fromJson(request.getReader().lines().filter(line -> line.startsWith("{") && line.endsWith("}")).collect(Collectors.joining()), AppointmentData.class);
        try {
            AppointmentData data =appointmentService.addAppointment(appointmentData);
            request.setAttribute("response", ResponseWrapper.setSuccess("Appointment successfully added", data));
            router = new Router("before add appointment page");
        } catch (ServiceException e) {
            request.setAttribute("response",ResponseWrapper.setError("Unable to add appointment" + e.getMessage()));
            router = new Router(PageName.ERROR.getPath());
        }
        return router;
    }
}
