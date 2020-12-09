package com.gudyna.webproject.model.service;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.entity.AppointmentData;

import java.util.List;

public interface AppointmentService {
    AppointmentData addAppointment(AppointmentData data) throws ServiceException;

    List<AppointmentData> getOpenAppointmentByDoctorId(int doctorId) throws ServiceException;

    List<AppointmentData> getCloseAppointmentByDoctorId(int doctorId) throws ServiceException;

    AppointmentData updateAppointment(AppointmentData data) throws ServiceException;

    boolean deleteAppointment(int id) throws DaoException, ServiceException;
}
