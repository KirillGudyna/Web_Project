package com.gudyna.webproject.controller.impl;

import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.AttributeKey;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.service.AppointmentService;
import com.gudyna.webproject.model.service.impl.AppointmentServiceImpl;
import com.gudyna.webproject.response.form.ResponseAppointmentData;
import com.gudyna.webproject.util.ParameterKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetAllOpenAppointments implements ActionCommand {

    private final AppointmentService service = AppointmentServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        int doctorId = (int) session.getAttribute(ParameterKey.USER_ID);
        try {
            List<ResponseAppointmentData> dataList = service.getOpenAppointmentByDoctorId(doctorId);
            request.setAttribute(AttributeKey.APPOINTMENTS_ATTR, dataList);
            router = new Router(PageName.OPEN_APPOINTMENTS_PAGE.getPath());
        } catch (ServiceException e) {
            router = new Router(PageName.ERROR.getPath());
        }

        return router;
    }
}
