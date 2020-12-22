package com.gudyna.webproject.model.service.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.dao.DoctorDao;
import com.gudyna.webproject.model.dao.UserDao;
import com.gudyna.webproject.model.dao.impl.DoctorDaoImpl;
import com.gudyna.webproject.model.dao.impl.UserDaoImpl;
import com.gudyna.webproject.model.entity.DoctorData;
import com.gudyna.webproject.model.entity.UserData;
import com.gudyna.webproject.model.service.DoctorService;
import com.gudyna.webproject.response.form.ResponseUserData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private static final String DOCTOR_PROFESSION = "doctor";
    private static final Logger LOGGER = LogManager.getLogger(DoctorServiceImpl.class);

    private final UserDao userDao = UserDaoImpl.getInstance();
    private final DoctorDao doctorDao = DoctorDaoImpl.getInstance();
    private static final DoctorServiceImpl INSTANCE = new DoctorServiceImpl();

    public static DoctorServiceImpl getInstance(){
        return INSTANCE;
    }

    @Override
    public DoctorData getDoctorByUserId(int userId) throws ServiceException{
        try {
            return doctorDao.getDoctorByUserId(userId);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<ResponseUserData> getAllDoctors() throws ServiceException {
        try {
            List<ResponseUserData> responseUsers = new ArrayList<>();
            List<DoctorData> doctors = doctorDao.getAllDoctors();
            for (DoctorData doctorData : doctors) {
                UserData user = userDao.getUserById(doctorData.getUserId());
                ResponseUserData responseUser = ResponseUserData.fromUserData(user);
                responseUser.setProfession(DOCTOR_PROFESSION);
                responseUser.setJobType(doctorData.getJobType());
                responseUser.setTimeWork(doctorData.getTimeWork());
                responseUser.setMinPrice(doctorData.getMinPrice());
                responseUser.setMaxAppPerDay(doctorData.getMaxAppPerDay());

                responseUsers.add(responseUser);
            }

            return responseUsers;
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
