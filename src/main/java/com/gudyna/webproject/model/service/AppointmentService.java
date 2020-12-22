package com.gudyna.webproject.model.service;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.entity.AppointmentData;
import com.gudyna.webproject.response.form.ResponseAppointmentData;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * interface AppointmentService for working with appointment
 */
public interface AppointmentService {
    /**
     * Add appointment in database
     *
     * @param data
     * @return adding appointment's data
     * @throws ServiceException in case of DB errors during adding user
     */
    Optional<ResponseAppointmentData> addAppointment(AppointmentData data) throws ServiceException;

    /**
     * Update appointment by parameters
     * @param appointmentId
     * @param price
     * @param dateTime
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws ServiceException in case of DB errors during adding user
     */
    int updateAppointmentByParameters(int appointmentId, int price, Date dateTime) throws ServiceException;

    /**
     * Get open appointments by doctor's id
     * @param doctorId
     * @return array list with appoinment's data
     * @throws ServiceException in case of DB errors during adding user
     */
    List<ResponseAppointmentData> getOpenAppointmentByDoctorId(int doctorId) throws ServiceException;

    /**
     * Get close appointments by doctor's id
     * @param doctorId
     * @return array list with appoinment's data
     * @throws ServiceException in case of DB errors during adding user
     */
    List<ResponseAppointmentData> getCloseAppointmentByDoctorId(int doctorId) throws ServiceException;

    /**
     * Update appointment's data by class with data
     * @param data
     * @return modified version of data
     * @throws ServiceException in case of DB errors during adding user
     */
    AppointmentData updateAppointment(AppointmentData data) throws ServiceException;

    /**
     * Get appointment by patient's id
     * @param patientId
     * @return array list with appoinment's data
     * @throws ServiceException in case of DB errors during adding user
     */
    List<ResponseAppointmentData> getAppointmentByPatientId(int patientId) throws ServiceException;

    /**
     * Get appointment by appointment's id
     * @param id
     * @return found appointment data
     * @throws ServiceException in case of DB errors during adding user
     */
    ResponseAppointmentData getAppointmentById(int id) throws ServiceException;

    /**
     * Delete appointment by appointment's id
     * @param id
     * @return found appointment data
     * @throws ServiceException in case of DB errors during adding user
     */
    boolean deleteAppointment(int id) throws DaoException, ServiceException;

}
