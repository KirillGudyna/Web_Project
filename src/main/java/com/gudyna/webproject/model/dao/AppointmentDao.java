package com.gudyna.webproject.model.dao;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.entity.AppointmentData;

import java.util.List;

public interface AppointmentDao {
    AppointmentData addAppointment(AppointmentData data) throws DaoException;

    AppointmentData updateAppointment(AppointmentData data) throws DaoException;

    List<AppointmentData> getAppointmentsByDoctorId(int doctorId, boolean isClosed) throws DaoException;

    AppointmentData getAppointmentById(int id) throws DaoException;

    List<AppointmentData> getAppointmentsByPatientId(int patientId, boolean isClosed) throws DaoException;

    boolean deleteAppointment(int id) throws DaoException;
}
