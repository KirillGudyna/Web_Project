package com.gudyna.webproject.model.entity;

import java.util.Objects;

public class AppointmentData {
    private boolean isClosed;
    private String dateTime;
    private int price;
    private int patientId;
    private int doctorId;
    private int id;

    public boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean closed) {
        isClosed = closed;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentData appointmentData = (AppointmentData) o;
        return getPrice() == appointmentData.getPrice() &&
                getPatientId() == appointmentData.getPatientId() &&
                getDoctorId() == appointmentData.getDoctorId() &&
                getId() == appointmentData.getId() &&
                Objects.equals(getDateTime(), appointmentData.getDateTime()) &&
                getIsClosed() && appointmentData.getIsClosed();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + id;
        result = 31 * result + doctorId;
        result = 31 * result + patientId;
        result = 31 * result + Boolean.hashCode(isClosed);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AppointmentData{\n");
        builder.append("id=");
        builder.append(id);
        builder.append("\n");
        builder.append("date=");
        builder.append(dateTime);
        builder.append("\n");
        builder.append("price=");
        builder.append(price);
        builder.append("\n");
        builder.append("patientId=");
        builder.append(patientId);
        builder.append("\n");
        builder.append("doctorId=");
        builder.append(doctorId);
        builder.append("\n");
        builder.append("isClosed=");
        builder.append(isClosed);
        builder.append("\n}");
        return builder.toString();
    }
}
