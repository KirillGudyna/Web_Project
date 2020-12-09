package com.gudyna.webproject.model.dao.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.dao.UserDao;
import com.gudyna.webproject.model.entity.UserData;
import com.gudyna.webproject.model.pool.ConnectionPool;
import com.gudyna.webproject.model.util.DaoUtils;
import com.gudyna.webproject.request.form.RequestUserRegistrationData;
import com.gudyna.webproject.response.form.ResponseUserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

public class UserDaoImpl implements UserDao {
    private static final String SQL_UPDATE_USER = "UPDATE user_info SET (name, surname, address, date_of_birth, email) VALUES(?,?,?,?,?)" +
            "WHERE id=?";
    private static final String SQL_ADD_USER = " INSERT INTO user_info (name, surname, address, date_of_birth, email, password) VALUES(?,?,?,?,?,?)";
    private static final String SQL_ADD_PATIENT = "INSERT INTO patient (profession, user_id) VALUES(?,?)";
    private static final String SQL_ADD_DOCTOR = "INSERT INTO doctor (user_id, time_work, job_type) VALUES(?,?,?)";
    private static final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM user_info  WHERE EMAIL=?";
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM user_info  WHERE id=?";
    private static final UserDaoImpl INSTANCE = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseUserData addUserAsPatient(RequestUserRegistrationData data) throws DaoException {
        try {
            ResponseUserData responseUserData;
            UserData userData = data.toData();
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = DaoUtils.getStatement(SQL_ADD_USER, connection);
            ResultSet resultSet = DaoUtils.executeStatement(data, Arrays.asList("Name", "Surname", "Address", "DateOfBirth", "Email", "Password"), statement);

            userData.setId(resultSet.getInt(1));
            data.setUserId(userData.getId());
            responseUserData = ResponseUserData.fromUserData(userData);
            statement = DaoUtils.getStatement(SQL_ADD_PATIENT, connection);

            DaoUtils.executeStatement(data, Arrays.asList("Profession", "UserId"), statement);

            responseUserData.setProfession(data.getProfession());
            DaoUtils.commitConnection(connection);
            return responseUserData;
        } catch (SQLException e) {
            throw new DaoException("Unable add user as patient.", e);
        }
    }

    @Override
    public ResponseUserData addUserAsDoctor(RequestUserRegistrationData data) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ResponseUserData responseUserData;
            UserData userData = data.toData();
            PreparedStatement statement = DaoUtils.getStatement(SQL_ADD_USER, connection);
            ResultSet resultSet = DaoUtils.executeStatement(data,
                    Arrays.asList("Name", "Surname", "Address", "DateOfBirth", "Email", "Password"), statement);

            userData.setId(resultSet.getInt(1));
            data.setUserId(userData.getId());
            responseUserData = ResponseUserData.fromUserData(userData);
            statement = DaoUtils.getStatement(SQL_ADD_PATIENT, connection);

            resultSet = DaoUtils.executeStatement(data,
                    Arrays.asList("Profession", "UserId"), statement);

            responseUserData.setProfession(data.getProfession());
            statement = DaoUtils.getStatement(SQL_ADD_DOCTOR, connection);

            resultSet = DaoUtils.executeStatement(data,
                    Arrays.asList("UserId", "TimeWork", "JobType"), statement);
            DaoUtils.commitConnection(connection);
            return responseUserData;
        } catch (SQLException e) {
            throw new DaoException("Unable add user as patient.", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public UserData getUserByEmail(String email) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_GET_USER_BY_EMAIL, connection);
            ResultSet resultSet = DaoUtils.executeSelectStatement(Collections.singletonList(email + ":String"), statement);
            return DaoUtils.initObjectType(new UserData(), resultSet);
        } catch (SQLException e) {
            throw new DaoException("Unable get user by email", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public UserData getUserById(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_GET_USER_BY_ID, connection);
            ResultSet resultSet = DaoUtils.executeSelectStatement(Collections.singletonList(id+":int"),statement);
            return DaoUtils.initObjectType(new UserData(),resultSet);
        } catch (SQLException e) {
            throw new DaoException("Unable get user by id", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public UserData updateUser(UserData userData) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_UPDATE_USER, connection);
            ResultSet resultSet = DaoUtils.executeStatement(userData,
                    Arrays.asList("Name", "Surname", "Address", "DateOfBirth", "Email", "Id"),
                    statement);
            DaoUtils.commitConnection(connection);
            return userData;
        } catch (SQLException e) {
            throw new DaoException("Unable update user", e);
        }
    }
}
