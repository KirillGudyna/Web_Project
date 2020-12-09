package com.gudyna.webproject.model.entity;

import java.util.Objects;

public class PurposeData {
    private int id;
    private int appointmentId;
    private String diagnosis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurposeData that = (PurposeData) o;
        return getId() == that.getId() &&
                getAppointmentId() == that.getAppointmentId() &&
                getDiagnosis().equals(that.getDiagnosis());
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 31 * result + id;
        result += 31 * result + appointmentId;
        result += 31 * result + diagnosis.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PurposeData{\n");
        builder.append("id=");
        builder.append(id);
        builder.append("\n");
        builder.append("appointmentId=");
        builder.append(appointmentId);
        builder.append("\n");
        builder.append("diagnosis=");
        builder.append(diagnosis);
        builder.append("\n");
        builder.append("}");
        return builder.toString();
    }

}
