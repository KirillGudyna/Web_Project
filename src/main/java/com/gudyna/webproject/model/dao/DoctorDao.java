package com.gudyna.webproject.model.dao;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.entity.DoctorData;

import java.util.List;

public interface DoctorDao {

    DoctorData getDoctorByUserId(int userId) throws DaoException;

    List<DoctorData> getAllDoctors() throws DaoException;
}
