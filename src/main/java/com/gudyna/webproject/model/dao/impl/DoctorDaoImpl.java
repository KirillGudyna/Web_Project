package com.gudyna.webproject.model.dao.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.dao.DoctorDao;
import com.gudyna.webproject.model.entity.DoctorData;
import com.gudyna.webproject.model.pool.ConnectionPool;
import com.gudyna.webproject.model.util.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    private static final String SQL_GET_DOCTOR_BY_USER_ID = "SELECT * FROM doctor WHERE user_id=?";
    private static final String SQL_GET_ALL_DOCTORS = "SELECT * FROM doctor";
    private static final DoctorDaoImpl INSTANCE = new DoctorDaoImpl();

    private DoctorDaoImpl() {
    }

    public static DoctorDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public DoctorData getDoctorByUserId(int userId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_GET_DOCTOR_BY_USER_ID, connection);
            ResultSet resultSet = DaoUtils.executeSelectStatement(Collections.singletonList(userId + ":String"), statement);
            return DaoUtils.initObjectType(new DoctorData(), resultSet);
        } catch (SQLException e) {
            throw new DaoException("Unable find doctor with such parameters!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public List<DoctorData> getAllDoctors() throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_GET_ALL_DOCTORS, connection);
            ResultSet resultSet = DaoUtils.executeSelectStatement(List.of(), statement);
            return DaoUtils.initObjectTypeList(new ArrayList<>(), DoctorData.class, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Unable get all doctors");
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
