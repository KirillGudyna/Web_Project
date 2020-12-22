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
import java.sql.Date;
import java.util.List;

public class UpdateAppointmentCommand implements ActionCommand {

    private final AppointmentService service = AppointmentServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int price = Integer.parseInt(request.getParameter(ParameterKey.PRICE));
        int appointmentId = Integer.parseInt(request.getParameter(AttributeKey.CHOSEN_APPOINTMENT_ID));
        Date dateTime = Date.valueOf(request.getParameter(ParameterKey.DATE_TIME));
        int doctorId = (int) request.getSession().getAttribute(ParameterKey.USER_ID);
        try {
            service.updateAppointmentByParameters(appointmentId, price, dateTime);
            List<ResponseAppointmentData> responseAppointmentList = service.getOpenAppointmentByDoctorId(doctorId);
            request.setAttribute(AttributeKey.APPOINTMENTS_ATTR, responseAppointmentList);

            router = new Router(PageName.OPEN_APPOINTMENTS_PAGE.getPath());
        } catch (ServiceException e) {
            router = new Router(PageName.ERROR.getPath());
        }

        return router;
    }
}
