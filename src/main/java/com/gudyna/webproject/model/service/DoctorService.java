package com.gudyna.webproject.model.service;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.entity.DoctorData;
import com.gudyna.webproject.response.form.ResponseUserData;

import java.util.List;

/**
 * interface DoctorService for get all doctors
 */
public interface DoctorService {

    DoctorData getDoctorByUserId(int userId) throws ServiceException;

    /**
     * Get all doctors in database
     * @return list with data
     * @throws ServiceException in case of DB errors during adding user
     */
    List<ResponseUserData> getAllDoctors() throws ServiceException;
}
