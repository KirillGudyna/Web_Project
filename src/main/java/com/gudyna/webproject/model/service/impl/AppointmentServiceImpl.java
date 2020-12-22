package com.gudyna.webproject.model.service.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.dao.*;
import com.gudyna.webproject.model.dao.impl.*;
import com.gudyna.webproject.model.entity.AppointmentData;
import com.gudyna.webproject.model.entity.DoctorData;
import com.gudyna.webproject.model.entity.UserData;
import com.gudyna.webproject.model.service.AppointmentService;
import com.gudyna.webproject.response.form.ResponseAppointmentData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger LOGGER = LogManager.getLogger(AppointmentServiceImpl.class);

    private static final AppointmentServiceImpl INSTANCE = new AppointmentServiceImpl();

    private final AppointmentDao appointmentDao = AppointmentDaoImpl.getInstance();
    private final PatientDao patientDao = PatientDaoImpl.getInstance();
    private final DoctorDao doctorDao = DoctorDaoImpl.getInstance();
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final PurposeDao purposeDao = PurposeDaoImpl.getInstance();

    private AppointmentServiceImpl() {

    }

    public static AppointmentServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<ResponseAppointmentData> addAppointment(AppointmentData data) throws ServiceException {
        try {
            ResponseAppointmentData responseData;
            DoctorData doctorData = doctorDao.getDoctorByUserId(data.getDoctorId());
            List<AppointmentData> appointmentDataList = appointmentDao.getAppointmentByDateDoctorId(data.getDateTime(), data.getId());
            if (doctorData.getMaxAppPerDay() > appointmentDataList.size()) {
                data = appointmentDao.addAppointment(data);
                responseData = ResponseAppointmentData.fromData(data);
                UserData userData = userDao.getUserById(doctorData.getUserId());
                responseData.setDoctorName(userData.getName());
                return Optional.of(responseData);
            } else {
                return Optional.empty();
            }

        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public AppointmentData updateAppointment(AppointmentData data) throws ServiceException {
        try {
            return appointmentDao.updateAppointment(data);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int updateAppointmentByParameters(int appointmentId, int price, Date dateTime) throws ServiceException {
        AppointmentData appointment = new AppointmentData();
        appointment.setPrice(price);
        appointment.setId(appointmentId);
        appointment.setDateTime(dateTime);
        try {
            return appointmentDao.updateAppointmentByParameters(appointment);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<ResponseAppointmentData> getOpenAppointmentByDoctorId(int doctorId) throws ServiceException {
        try {
            List<ResponseAppointmentData> appointmentDataList = new ArrayList<>();
            List<AppointmentData> listData = appointmentDao.getAppointmentsByDoctorId(doctorId, false);
            for (AppointmentData data : listData) {
                ResponseAppointmentData responseAppointmentData = ResponseAppointmentData.fromData(data);
                responseAppointmentData.setPatientName(patientDao.getNameByPatientId(data.getPatientId()));
                purposeDao.getPurposeByAppointmentId(data.getId())
                        .ifPresent(purpose -> responseAppointmentData.setPurposeId(purpose.getId()));
                appointmentDataList.add(responseAppointmentData);
            }

            return appointmentDataList;
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ResponseAppointmentData getAppointmentById(int id) throws ServiceException {
        ResponseAppointmentData data;
        try {
            AppointmentData appointment = appointmentDao.getAppointmentById(id);
            data = ResponseAppointmentData.fromData(appointment);
            UserData userData = userDao.getUserById(appointment.getPatientId());
            data.setPatientName(userData.getSurname()+ " " + userData.getName());
            data.setDoctorId(appointment.getDoctorId());
            data.setPatientId(appointment.getPatientId());

            return data;
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<ResponseAppointmentData> getCloseAppointmentByDoctorId(int doctorId) throws ServiceException {
        try {
            List<ResponseAppointmentData> appointmentDataList = new ArrayList<>();
            List<AppointmentData> listData = appointmentDao.getAppointmentsByDoctorId(doctorId, true);
            for (AppointmentData data : listData) {
                ResponseAppointmentData responseAppointmentData = ResponseAppointmentData.fromData(data);
                responseAppointmentData.setPatientName(patientDao.getNameByPatientId(data.getPatientId()));
                appointmentDataList.add(responseAppointmentData);
            }

            return appointmentDataList;
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<ResponseAppointmentData> getAppointmentByPatientId(int patientId) throws ServiceException {
        try {
            List<AppointmentData> appointments = appointmentDao.getAppointmentsByPatientId(patientId);
            List<ResponseAppointmentData> responseAppointments = new ArrayList<>();
            for (AppointmentData data : appointments) {
                UserData user = userDao.getUserById(data.getDoctorId());
                DoctorData doctor = doctorDao.getDoctorByUserId(data.getDoctorId());
                boolean existsAppointmentPurpose = purposeDao.existsPurposeByAppointmentId(data.getId());

                ResponseAppointmentData responseAppointment = ResponseAppointmentData.fromData(data);
                responseAppointment.setDoctorName(user.getSurname() + " " + user.getName());
                responseAppointment.setDoctorJobType(doctor.getJobType());
                responseAppointment.setExistsPurpose(existsAppointmentPurpose);
                responseAppointment.setDoctorId(user.getId());

                responseAppointments.add(responseAppointment);
            }
            responseAppointments.sort(Comparator.comparing(ResponseAppointmentData::getDateTime));

            return responseAppointments;
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteAppointment(int id) throws ServiceException {
        try {
            return appointmentDao.deleteAppointment(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
