package com.gudyna.webproject.model.dao.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.dao.PatientDao;
import com.gudyna.webproject.model.entity.DoctorData;
import com.gudyna.webproject.model.entity.PatientData;
import com.gudyna.webproject.model.pool.ConnectionPool;
import com.gudyna.webproject.model.util.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

public class PatientDaoImpl implements PatientDao {
    private static final String SQL_GET_PATIENT_BY_USER_ID = "SELECT* FROM patient WHERE user_id=?";
    private static final PatientDaoImpl INSTANCE = new PatientDaoImpl();

    private PatientDaoImpl(){

    }
    public static PatientDaoImpl getInstance(){
        return INSTANCE;
    }


    @Override
    public PatientData getPatientDataByUserId(int userId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_GET_PATIENT_BY_USER_ID, connection);
            ResultSet resultSet = DaoUtils.executeSelectStatement(Collections.singletonList(userId + ":String"), statement);
            return DaoUtils.initObjectType(new PatientData(), resultSet);
        } catch (SQLException e) {
            throw new DaoException("Unable get patient by id!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
