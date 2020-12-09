package com.gudyna.webproject.controller.impl;

import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.AttributeKey;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;
import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.dao.AppointmentDao;
import com.gudyna.webproject.model.dao.impl.AppointmentDaoImpl;
import com.gudyna.webproject.model.entity.AppointmentData;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ChooseAppointment implements ActionCommand {
    private final AppointmentDao appointmentDao = AppointmentDaoImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws IOException {
        Router router;
        int id = Integer.parseInt(request.getParameter(AttributeKey.CHOSEN_APPOINTMENT_ID));
        try {
            AppointmentData data = appointmentDao.getAppointmentById(id);
            request.setAttribute(AttributeKey.CHOSEN_APPOINTMENT_DATA, data);
            router = new Router(PageName.UPDATE_CLOSE_PAGE.getPath());
        } catch (DaoException e) {
            router = new Router(PageName.ERROR.getPath());
        }
        return router;
    }
}
