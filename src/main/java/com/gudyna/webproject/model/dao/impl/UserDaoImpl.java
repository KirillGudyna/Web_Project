package com.gudyna.webproject.model.dao.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.dao.UserDao;
import com.gudyna.webproject.model.entity.UserData;
import com.gudyna.webproject.model.pool.ConnectionPool;
import com.gudyna.webproject.model.util.DaoUtils;
import com.gudyna.webproject.request.form.RequestUserRegistrationData;
import com.gudyna.webproject.response.form.ResponseUserData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final String SQL_UPDATE_USER = "UPDATE user_info SET (name, surname, address, date_of_birth, email) VALUES(?,?,?,?,?)" +
            "WHERE id=?";
    private static final String SQL_ADD_USER = " INSERT INTO user_info (name, surname, address, date_of_birth, email, password) VALUES(?,?,?,?,?,?)";
    private static final String SQL_ADD_PATIENT = "INSERT INTO patient (profession, user_id) VALUES(?,?)";
    private static final String SQL_ADD_DOCTOR = "INSERT INTO doctor (user_id, time_work, job_type,max_app_per_day,min_price) VALUES(?,?,?,?,?)";
    private static final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM user_info  WHERE EMAIL=?";
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM user_info  WHERE id=?";

    private static final UserDaoImpl INSTANCE = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseUserData addUserAsPatient(RequestUserRegistrationData registrationData) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            ResponseUserData responseUser;
            UserData user = saveUser(connection, registrationData);
            responseUser = ResponseUserData.fromUserData(user);

            savePatient(connection, registrationData);
            responseUser.setProfession(registrationData.getProfession());

            DaoUtils.commitOrRollbackConnection(connection);

            return responseUser;
        } catch (SQLException e) {
            LOGGER.error("Unable to add user as patient", e);
            throw new DaoException("Unable add user as patient.", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public ResponseUserData addUserAsDoctor(RequestUserRegistrationData registrationData) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            ResponseUserData responseUser;
            UserData user = saveUser(connection, registrationData);
            responseUser = ResponseUserData.fromUserData(user);

            savePatient(connection, registrationData);
            responseUser.setProfession(registrationData.getProfession());

            saveDoctor(connection, registrationData);
            setDoctorDate(registrationData, responseUser);

            DaoUtils.commitOrRollbackConnection(connection);

            return responseUser;
        } catch (SQLException e) {
            LOGGER.error("Unable to add user as doctor!",e);
            throw new DaoException("Unable to add user as doctor!", e);
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
            LOGGER.error("Unable get user by email", e);
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
            ResultSet resultSet = DaoUtils.executeSelectStatement(Collections.singletonList(id + ":int"), statement);

            return DaoUtils.initObjectType(new UserData(), resultSet);
        } catch (SQLException e) {
            LOGGER.error("Unable to get user by id", e);
            throw new DaoException("Unable to get user by id", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public UserData updateUser(UserData userData) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_UPDATE_USER, connection);
            DaoUtils.executeStatement(userData,
                    Arrays.asList("Name", "Surname", "Address", "DateOfBirth", "Email", "Id"),
                    statement);

            return userData;
        } catch (SQLException e) {
            LOGGER.error("Unable to update user", e);
            throw new DaoException("Unable to update user", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    private UserData saveUser(Connection connection, RequestUserRegistrationData registrationData) throws SQLException {
        UserData user = registrationData.toData();
        PreparedStatement statement = DaoUtils.getStatement(SQL_ADD_USER, connection);
        ResultSet resultSet = DaoUtils.executeStatement(registrationData, Arrays.asList("Name", "Surname", "Address", "DateOfBirth", "Email", "Password"), statement);
        user.setId(resultSet.getInt(1));
        registrationData.setUserId(user.getId());

        return user;
    }

    private void savePatient(Connection connection, RequestUserRegistrationData registrationData) throws SQLException {
        PreparedStatement statement = DaoUtils.getStatement(SQL_ADD_PATIENT, connection);
        DaoUtils.executeStatement(registrationData, Arrays.asList("Profession", "UserId"), statement);
    }

    private void saveDoctor(Connection connection, RequestUserRegistrationData registrationData) throws SQLException {
        PreparedStatement statement = DaoUtils.getStatement(SQL_ADD_DOCTOR, connection);
        DaoUtils.executeStatement(registrationData,
                Arrays.asList("UserId", "TimeWork", "JobType", "MaxAppPerDay", "MinPrice"), statement);
    }

    private void setDoctorDate(RequestUserRegistrationData registrationData, ResponseUserData responseUserData) {
        responseUserData.setTimeWork(registrationData.getTimeWork());
        responseUserData.setJobType(registrationData.getJobType());
        responseUserData.setMaxAppPerDay(registrationData.getMaxAppPerDay());
        responseUserData.setMinPrice(registrationData.getMinPrice());
    }
}
