package com.gudyna.webproject.model.service.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.dao.AppointmentDao;
import com.gudyna.webproject.model.dao.impl.AppointmentDaoImpl;
import com.gudyna.webproject.model.entity.AppointmentData;
import com.gudyna.webproject.model.service.AppointmentService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentDao appointmentDao = AppointmentDaoImpl.getInstance();
    private static final AppointmentServiceImpl INSTANCE = new AppointmentServiceImpl();

    private AppointmentServiceImpl() {

    }

    public static AppointmentServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public AppointmentData addAppointment(AppointmentData data) throws ServiceException {
        try {
            return appointmentDao.addAppointment(data);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<AppointmentData> getOpenAppointmentByDoctorId(int doctorId) throws ServiceException {
        try {
            return appointmentDao.getAppointmentsByDoctorId(doctorId, false);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<AppointmentData> getCloseAppointmentByDoctorId(int doctorId) throws ServiceException {
        try {
            return appointmentDao.getAppointmentsByDoctorId(doctorId, true);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public AppointmentData updateAppointment(AppointmentData data) throws ServiceException {
        try {
            return appointmentDao.addAppointment(data);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteAppointment(int id) throws ServiceException {
        try {
            return appointmentDao.deleteAppointment(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
