package com.gudyna.webproject.model.service.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.dao.DoctorDao;
import com.gudyna.webproject.model.dao.PatientDao;
import com.gudyna.webproject.model.dao.UserDao;
import com.gudyna.webproject.model.dao.impl.DoctorDaoImpl;
import com.gudyna.webproject.model.dao.impl.PatientDaoImpl;
import com.gudyna.webproject.model.dao.impl.UserDaoImpl;
import com.gudyna.webproject.model.entity.DoctorData;
import com.gudyna.webproject.model.entity.PatientData;
import com.gudyna.webproject.model.entity.UserData;
import com.gudyna.webproject.model.service.UserService;
import com.gudyna.webproject.request.form.RequestUserRegistrationData;
import com.gudyna.webproject.response.form.ResponseUserData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    private static final String KEY = "asd";
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final UserDao userDao = UserDaoImpl.getInstance();
    private final DoctorDao doctorDao = DoctorDaoImpl.getInstance();
    private final PatientDao patientDao = PatientDaoImpl.getInstance();

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    private UserServiceImpl() {

    }

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

   @Override
   public ResponseUserData addUser(RequestUserRegistrationData registrationData) throws ServiceException {
        if (registrationData.isDoctor() && !KEY.equals(registrationData.getKeyDoctor())) {
            throw new ServiceException("Wrong key");
        }

        try {
            ResponseUserData data;
            if (!registrationData.isDoctor()) {
                data = userDao.addUserAsPatient(registrationData);
            } else{
                data = userDao.addUserAsDoctor(registrationData);
            }

            return data;
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ResponseUserData authorizeUser(String email, String password, boolean isDoctor) throws ServiceException {
        ResponseUserData responseUserData;
        UserData data = null;
        DoctorData doctorData = null;
        PatientData patientData;
        try {
            data = userDao.getUserByEmail(email);
            if (data.getId() == 0) {
                throw new ServiceException("Not found user with such email!");
            }
            if (!data.getPassword().equals(password)) {
                throw new ServiceException("You enter wrong password!");
            }

            patientData = patientDao.getPatientDataByUserId(data.getId());
            if (isDoctor) {
                doctorData = doctorDao.getDoctorByUserId(data.getId());
            }

            responseUserData = ResponseUserData.fromUserData(data);
            if (isDoctor) {
                responseUserData.setTimeWork(doctorData.getTimeWork());
                responseUserData.setJobType(doctorData.getJobType());
                responseUserData.setDoctor(true);
            }
            responseUserData.setProfession(patientData.getProfession());

            return responseUserData;
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
