package com.gudyna.webproject.controller;

import com.gudyna.webproject.controller.impl.*;
import com.gudyna.webproject.controller.impl.pagecommand.*;

public enum CommandType {
    EMPTY(new EmptyCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    LOGIN(new LogInCommand()),
    LOGOUT(new LogOutCommand()),
    GO_REGISTRATION(new RegistrationPageCommand()),
    REGISTRATION(new RegisterCommand()),
    GET_DOCTORS(new GetAllDoctorsCommand()),
    INDEX(new IndexPageCommand()),
    GO_TO_LOGIN_PAGE(new LoginPageCommand()),
    GO_TO_HOME(new GoToHomePageCommand()),
    GET_CLOSE_APPOINTMENTS(new GetAllClosedAppointments()),
    GET_OPEN_APPOINTMENTS(new GetAllOpenAppointments()),
    CHOOSE_APPOINTMENT(new ChooseAppointmentCommand()),
    UPDATE_APPOINTMENT(new UpdateAppointmentCommand()),
    CLOSE_APPOINTMENT(new CloseAppointmentCommand()),
    SHOW_PURPOSE(new ShowPurposeCommand()),
    GO_ADD_APPOINTMENT(new GoAddAppointmentPageCommand()),
    ADD_APPOINTMENT(new AddAppointmentCommand()),
    SHOW_APPOINTMENTS(new GetAllPatientAppointmentsCommand()),
    ADD_PURPOSE(new AddPurposeCommand()),
    GO_TO_ADD_PURPOSE_PAGE(new GoToAddPurposePage()),
    GO_TO_DOCTOR_HOME(new GoToDoctorHomePageCommand()),
    GO_TO_PATIENT_HOME(new GoToPatientHomePageCommand());

    private ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
