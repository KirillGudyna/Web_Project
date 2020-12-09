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
import com.gudyna.webproject.model.service.UserService;
import com.gudyna.webproject.response.form.ResponseUserData;

import java.util.ArrayList;
import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final DoctorDao doctorDao = DoctorDaoImpl.getInstance();

    private static final DoctorServiceImpl INSTANCE = new DoctorServiceImpl();

    public static DoctorServiceImpl getInstance(){
        return INSTANCE;
    }

    @Override
    public List<ResponseUserData> getAllDoctors() throws ServiceException {
        try {
            List<ResponseUserData> userDataList = new ArrayList<>();
            ResponseUserData data;
            List<DoctorData> doctorDataList = doctorDao.getAllDoctors();
            for (DoctorData doctorData : doctorDataList) {
                UserData userData = userDao.getUserById(doctorData.getUserId());
                data = ResponseUserData.fromUserData(userData);
                data.setProfession("doctor");
                data.setJobType(doctorData.getJobType());
                data.setTimeWork(doctorData.getTimeWork());
                userDataList.add(data);
            }
            return userDataList;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
