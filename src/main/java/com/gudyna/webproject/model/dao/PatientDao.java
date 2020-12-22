package com.gudyna.webproject.model.dao;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.entity.PatientData;

public interface PatientDao {

    PatientData getPatientDataByUserId(int userId) throws DaoException;

    String getNameByPatientId(int id) throws DaoException;
}
