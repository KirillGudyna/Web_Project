package com.gudyna.webproject.controller.impl;

import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.AttributeKey;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.entity.DrugData;
import com.gudyna.webproject.model.entity.MedicalProcedureData;
import com.gudyna.webproject.model.entity.PurposeData;
import com.gudyna.webproject.model.service.AppointmentService;
import com.gudyna.webproject.model.service.PurposeService;
import com.gudyna.webproject.model.service.impl.AppointmentServiceImpl;
import com.gudyna.webproject.model.service.impl.PurposeServiceImpl;
import com.gudyna.webproject.request.form.RequestAddPurposeData;
import com.gudyna.webproject.response.form.ResponseAppointmentData;
import com.gudyna.webproject.util.ParameterKey;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddPurposeCommand implements ActionCommand {

    private static final String BUTTON_ON = "on";

    private final PurposeService service = PurposeServiceImpl.getInstance();
    private final AppointmentService appointmentService = AppointmentServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestAddPurposeData requestPurpose = create(request);
        int doctorId = (int) request.getSession().getAttribute(ParameterKey.USER_ID);
        try {
            service.addPurpose(requestPurpose);
            List<ResponseAppointmentData> responseAppointmentList = appointmentService.getOpenAppointmentByDoctorId(doctorId);
            request.setAttribute(AttributeKey.APPOINTMENTS_ATTR, responseAppointmentList);
            router = new Router(PageName.OPEN_APPOINTMENTS_PAGE.getPath());
        } catch (ServiceException e) {
            router = new Router(PageName.ERROR.getPath());
        }

        return router;
    }

    private RequestAddPurposeData create(HttpServletRequest request) {
        RequestAddPurposeData requestPurpose = new RequestAddPurposeData();
        String appointmentIdString = request.getParameter(AttributeKey.CHOSEN_APPOINTMENT_ID);
        int appointmentId = Integer.parseInt(appointmentIdString);
        PurposeData purpose = new PurposeData();
        purpose.setDiagnosis(request.getParameter(ParameterKey.DIAGNOSIS));
        purpose.setAppointmentId(appointmentId);
        if (isEnabled(request.getParameter("add_drug"))) {
            DrugData drug = new DrugData();
            drug.setPurposeId(purpose.getId());
            drug.setName(request.getParameter(ParameterKey.DRUG_NAME));
            drug.setAmount(Integer.parseInt(request.getParameter(ParameterKey.AMOUNT)));
            drug.setTermTaking(Integer.parseInt(request.getParameter(ParameterKey.TERM_TAKING)));
            requestPurpose.setDrug(drug);
        }
        if (isEnabled(request.getParameter("add_procedure"))) {
            MedicalProcedureData procedure = new MedicalProcedureData();
            procedure.setPurposeId(purpose.getId());
            procedure.setName(request.getParameter(ParameterKey.PROCEDURE_NAME));
            procedure.setDateStart(request.getParameter(ParameterKey.START_DATE));
            procedure.setDateEnd(request.getParameter(ParameterKey.END_DATE));
            requestPurpose.setProcedure(procedure);
        }
        requestPurpose.setPurpose(purpose);

        return requestPurpose;
    }

    private boolean isEnabled(String parameter) {
        return BUTTON_ON.equals(parameter);
    }
}
