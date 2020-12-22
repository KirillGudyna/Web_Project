package com.gudyna.webproject.model.dao.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.dao.AppointmentDao;
import com.gudyna.webproject.model.entity.AppointmentData;
import com.gudyna.webproject.model.pool.ConnectionPool;
import com.gudyna.webproject.model.util.DaoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {

    private static final Logger LOGGER = LogManager.getLogger(AppointmentDaoImpl.class);
    private static final String SQL_ADD_APPOINTMENT = "INSERT INTO appointment"
            + " (price,date_time, doctor_id,"
            + " patient_id ) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE_APPOINTMENT = "UPDATE appointment SET price = ?, date_time = ?, doctor_id = ?, patient_id = ?, is_closed = ? WHERE id=?";
    private static final String SQL_DELETE_APPOINTMENT = "DELETE FROM doctor_order WHERE id=?";
    private static final String SQL_GET_APPOINTMENT_BY_ID = "SELECT * FROM appointment where id = ?";
    private static final String SQL_GET_APPOINTMENT_BY_DOCTOR_ID = "SELECT * FROM appointment where (doctor_id = ?) AND (is_closed=?)";
    private static final String SQL_GET_APPOINTMENT_BY_PATIENT_ID = "SELECT * FROM appointment where (patient_id = ?)";
    private static final String SQL_GET_APPOINTMENT_BY_DATE = "SELECT * FROM appointment WHERE (date_time = ?) AND (doctor_id = ?)";
    private static final String SQL_UPDATE_APPOINTMENT_BY_PARAMETERS = "UPDATE appointment SET price = ?, date_time = ? WHERE id=?";
    private static final String SQL_UPDATE_APPOINTMENT_BY_IS_CLOSED = "UPDATE appointment SET is_closed = ? WHERE id = ?";

    private static final AppointmentDaoImpl INSTANCE = new AppointmentDaoImpl();

    private AppointmentDaoImpl() {
    }

    public static AppointmentDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public AppointmentData addAppointment(AppointmentData data) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_ADD_APPOINTMENT, connection);
            ResultSet resultSet = DaoUtils.executeStatement(data,
                    Arrays.asList("Price", "DateTime", "DoctorId", "PatientId"),
                    statement);
            data.setId(resultSet.getInt(1));

            return data;
        } catch (SQLException e) {
            LOGGER.error("Unable to add appointment!", e);
            throw new DaoException("Unable to add appointment!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public AppointmentData updateAppointment(AppointmentData appointment) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_UPDATE_APPOINTMENT, connection);
            DaoUtils.executeUpdateStatement(appointment,
                    Arrays.asList("Price", "DateTime", "DoctorId", "PatientId", "IsClosed", "Id"),
                    statement);
            return appointment;
        } catch (SQLException e) {
            LOGGER.error("Unable to update appointment!", e);
            throw new DaoException("Unable to update appointment!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public int updateAppointmentByParameters(AppointmentData appointment) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_UPDATE_APPOINTMENT_BY_PARAMETERS, connection);

            return DaoUtils.executeUpdateStatement(appointment,
                    Arrays.asList("Price", "DateTime", "Id"),
                    statement);
        } catch (SQLException e) {
            LOGGER.error("Unable to update appointment by paramters!", e);
            throw new DaoException("Unable to update appointment by parameter!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public AppointmentData closeAppointment(AppointmentData appointment) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_UPDATE_APPOINTMENT_BY_IS_CLOSED, connection);
            DaoUtils.executeUpdateStatement(appointment,
                    Arrays.asList("IsClosed", "Id"),
                    statement);

            return appointment;
        } catch (SQLException e) {
            LOGGER.error("Unable to close appointment!", e);
            throw new DaoException("Unable to close appointment!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public List<AppointmentData> getAppointmentsByDoctorId(int doctorId, boolean isClosed) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_GET_APPOINTMENT_BY_DOCTOR_ID, connection, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet resultSet = DaoUtils.executeSelectStatement(List.of(doctorId + ":int", isClosed + ":boolean"), statement);

            return resultSet.first()
                    ? DaoUtils.initObjectTypeList(new ArrayList<>(), AppointmentData.class, resultSet)
                    : List.of();
        } catch (SQLException e) {
            LOGGER.error("Unable get appointment by doctor!", e);
            throw new DaoException("Unable get appointment by doctor!!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public AppointmentData getAppointmentById(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_GET_APPOINTMENT_BY_ID, connection);
            ResultSet resultSet = DaoUtils.executeSelectStatement(Collections.singletonList(id + ":int"), statement);

            return DaoUtils.initObjectType(new AppointmentData(), resultSet);
        } catch (SQLException e) {
            LOGGER.error("Unable to get appointment by id!", e);
            throw new DaoException("Unable to get appointment by id!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public List<AppointmentData> getAppointmentsByPatientId(int patientId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_GET_APPOINTMENT_BY_PATIENT_ID, connection, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet resultSet = DaoUtils.executeSelectStatement(Collections.singletonList(patientId + ":int"), statement);

            return resultSet.first()
                    ? DaoUtils.initObjectTypeList(new ArrayList<>(), AppointmentData.class, resultSet)
                    : List.of();
        } catch (SQLException e) {
            LOGGER.error("Unable get appointment by doctor!!", e);
            throw new DaoException("Unable get appointment by doctor!!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public List<AppointmentData> getAppointmentByDateDoctorId(Date date, int doctorId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_GET_APPOINTMENT_BY_DATE, connection,ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet resultSet = DaoUtils.executeSelectStatement(List.of(date + ":Date", doctorId + ":int"), statement);

            return resultSet.first()
                    ? DaoUtils.initObjectTypeList(new ArrayList<>(), AppointmentData.class, resultSet)
                    : List.of();
        } catch (SQLException e) {
            LOGGER.error("Unable get appointment by doctor and date!!", e);
            throw new DaoException("Unable get appointment by doctor and date!!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

    }

    @Override
    public boolean deleteAppointment(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_DELETE_APPOINTMENT, connection);
            int deletedAppointment = DaoUtils.executeDeleteStatement(id, statement);

            return deletedAppointment != 0;
        } catch (SQLException e) {
            LOGGER.error("Unable delete appointment!!", e);
            throw new DaoException("Unable delete appointment!!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }


}
