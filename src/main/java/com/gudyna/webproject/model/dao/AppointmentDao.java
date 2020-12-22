package com.gudyna.webproject.model.dao;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.entity.AppointmentData;

import java.sql.Date;
import java.util.List;

public interface AppointmentDao {
    AppointmentData addAppointment(AppointmentData data) throws DaoException;

    AppointmentData updateAppointment(AppointmentData data) throws DaoException;

    int updateAppointmentByParameters(AppointmentData appointment) throws DaoException;

    AppointmentData closeAppointment(AppointmentData appointment) throws DaoException;

    List<AppointmentData> getAppointmentsByDoctorId(int doctorId, boolean isClosed) throws DaoException;

    AppointmentData getAppointmentById(int id) throws DaoException;

    List<AppointmentData> getAppointmentsByPatientId(int patientId) throws DaoException;

    List<AppointmentData> getAppointmentByDateDoctorId(Date date, int doctorId) throws DaoException;

    boolean deleteAppointment(int id) throws DaoException;
}
