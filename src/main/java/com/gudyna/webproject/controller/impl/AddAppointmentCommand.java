package com.gudyna.webproject.controller.impl;

import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.AttributeKey;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.entity.AppointmentData;
import com.gudyna.webproject.model.entity.DoctorData;
import com.gudyna.webproject.model.service.AppointmentService;
import com.gudyna.webproject.model.service.DoctorService;
import com.gudyna.webproject.model.service.impl.AppointmentServiceImpl;
import com.gudyna.webproject.model.service.impl.DoctorServiceImpl;
import com.gudyna.webproject.response.form.ResponseAppointmentData;
import com.gudyna.webproject.util.ParameterKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class AddAppointmentCommand implements ActionCommand {
    private final AppointmentService appointmentService = AppointmentServiceImpl.getInstance();
    private final DoctorService doctorService = DoctorServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws IOException {
        Router router;
        HttpSession session = request.getSession();
        int patientId = (Integer) session.getAttribute(ParameterKey.USER_ID);

        try {
            AppointmentData data = createAppointment(request, patientId);

            Optional<ResponseAppointmentData> appointmentData = appointmentService.addAppointment(data);
            if (appointmentData.isPresent()) {
                request.setAttribute("add_appointments_response", "patient.add_appointments_response.success");
                List<ResponseAppointmentData> appointments = appointmentService.getAppointmentByPatientId(patientId);
                request.setAttribute(AttributeKey.APPOINTMENTS_ATTR, appointments);

                router = new Router(PageName.SHOW_APPOINTMENT.getPath());
            } else {
                request.setAttribute("add_appointments_response", "patient.add_appointments_response.success");
                request.setAttribute(AttributeKey.DOCTORS_ATTR, request.getParameter(AttributeKey.DOCTORS_ATTR));

                router = new Router(PageName.ADD_APPOINTMENT.getPath());
            }

        } catch (ServiceException e) {
            router = new Router(PageName.ERROR.getPath());
        }

        return router;
    }

    private AppointmentData createAppointment(HttpServletRequest request, int patientId) throws ServiceException {

        AppointmentData appointment = new AppointmentData();
        appointment.setPatientId(patientId);
        int doctorId = Integer.parseInt(request.getParameter(AttributeKey.CHOSEN_DOCTOR));
        appointment.setDoctorId(doctorId);
        DoctorData doctorData = doctorService.getDoctorByUserId(doctorId);
        String parameterDate = request.getParameter(AttributeKey.DATE_OF_APPOINTMENT);
        Date dateOfBirth = Date.valueOf(parameterDate);
        appointment.setDateTime(dateOfBirth);
        appointment.setPrice(doctorData.getMinPrice());

        return appointment;
    }
}
