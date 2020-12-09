package com.gudyna.webproject.controller;

import com.gudyna.webproject.controller.impl.*;
import com.gudyna.webproject.controller.impl.pagecommand.HomePageCommand;
import com.gudyna.webproject.controller.impl.pagecommand.IndexPageCommand;
import com.gudyna.webproject.controller.impl.pagecommand.RegistrationPageCommand;
import com.gudyna.webproject.controller.impl.pagecommand.UpdateAppointmentPageCommand;

public enum CommandType {
    EMPTY(new EmptyCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    LOGIN(new LogInCommand()),
    GO_REGISTRATION(new RegistrationPageCommand()),
    REGISTRATION(new RegisterCommand()),
    GET_DOCTORS(new GetAllDoctorsCommand()),
    INDEX(new IndexPageCommand()),
    HOME(new HomePageCommand()),
    GET_CLOSE_APPOINTMENTS(new GetAllCloseAppointments()),
    GET_OPEN_APPOINTMENTS(new GetAllOpenAppointments()),
    CHOOSE_APPOINTMENT(new ChooseAppointment()),
    UPDATE_APPOINTMENT(new UpdateAppointmentPageCommand());

    private ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
