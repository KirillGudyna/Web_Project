package com.gudyna.webproject.controller;

public enum PageName {
    INDEX("/index.jsp"),
    LOGIN("/jsp/login.jsp"),
    REGISTER("/jsp/registration.jsp"),
    ERROR("/jsp/error.jsp"),
    MAIN_PAGE("/jsp/main_page.jsp"),
    HOME("/jsp/home.jsp"),
    OPEN_APPOINTMENTS_PAGE("/jsp/open_appointment_page.jsp"),
    CLOSE_APPOINTMENTS_PAGE("/jsp/close_appointment_page.jsp"),
    UPDATE_APPOINTMENT_PAGE("/jsp/update_appointment_page.jsp"),
    UPDATE_CLOSE_PAGE("/jsp/update_close_page.jsp"),
    UPDATE_PAGE("/jsp/update_appointment.jsp");
    private String path;

    PageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
