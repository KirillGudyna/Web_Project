package com.gudyna.webproject.controller;

public enum PageName {
    INDEX("/index.jsp"),
    LOGIN("/jsp/login.jsp"),
    REGISTER("/jsp/registration.jsp"),
    ERROR("/jsp/error.jsp"),
    MAIN_PAGE("/jsp/main_page.jsp"),
    HOME("/jsp/home.jsp"),
    OPEN_APPOINTMENTS_PAGE("/jsp/user/doctor/open_appointment_page.jsp"),
    CLOSE_APPOINTMENTS_PAGE("/jsp/user/doctor/close_appointment_page.jsp"),
    UPDATE_CLOSE_PAGE("/jsp/user/doctor/update_close_page.jsp"),
    DOCTOR_HOME("/jsp/user/doctor/doctor_home.jsp"),
    SHOW_PURPOSE("/jsp/user/show_purpose_page.jsp"),
    PATIENT_HOME("/jsp/user/patient/patient_home.jsp"),
    ADD_APPOINTMENT("/jsp/user/patient/add_appointment.jsp"),
    SHOW_APPOINTMENT("/jsp/user/patient/patient_appointments_page.jsp"),
    ADD_PURPOSE_PAGE("/jsp/user/doctor/add_purpose_page.jsp");

    private String path;

    PageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
