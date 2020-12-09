package com.gudyna.webproject.controller.impl;

import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.AttributeKey;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.entity.AppointmentData;
import com.gudyna.webproject.model.service.AppointmentService;
import com.gudyna.webproject.model.service.impl.AppointmentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetAllCloseAppointments implements ActionCommand {
    private final AppointmentService service = AppointmentServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws IOException {
        Router router;
        HttpSession session = request.getSession();
        int doctorId =Integer.parseInt((String)session.getAttribute("user_id"));
        try {
            List<AppointmentData> dataList = service.getCloseAppointmentByDoctorId(doctorId);
            request.setAttribute(AttributeKey.APPOINTMENTS_ATTR, dataList);
            router = new Router(PageName.CLOSE_APPOINTMENTS_PAGE.getPath());
        } catch (ServiceException e) {
            router = new Router(PageName.ERROR.getPath());
        }
        return router;
    }
}
