package com.gudyna.webproject.model.dao.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.dao.AppointmentDao;
import com.gudyna.webproject.model.entity.AppointmentData;
import com.gudyna.webproject.model.pool.ConnectionPool;
import com.gudyna.webproject.model.util.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {
    private static final String SQL_ADD_APPOINTMENT = "INSERT INTO appointment"
            + " (price,date_time, doctor_id,"
            + " patient_id ) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE_APPOINTMENT = "UPDATE appointment SET price = ?, date_time = ?, doctor_id = ?, patient_id = ?, is_closed = ? WHERE id=?";
    private static final String SQL_DELETE_APPOINTMENT = "DELETE FROM doctor_order WHERE id=?";
    private static final String SQL_GET_APPOINTMENT_BY_ID = "SELECT * FROM appointment where id = ?";
    private static final String SQL_GET_APPOINTMENT_BY_DOCTOR_ID = "SELECT * FROM appointment where (doctor_id = ?) AND (is_closed=?)";
    private static final String SQL_GET_APPOINTMENT_BY_PATIENT_ID = "SELECT * FROM appointment where (patient_id = ?) AND (is_closed=?)";

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
                    Arrays.asList("Price", "Date", "DoctorId", "PatientId"),
                    statement);
            data.setId(resultSet.getInt(1));
            DaoUtils.commitConnection(connection);
            return data;
        } catch (SQLException e) {
            throw new DaoException("Unable add appointment!", e);
        }
    }

    @Override
    public AppointmentData updateAppointment(AppointmentData data) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_UPDATE_APPOINTMENT, connection);
            DaoUtils.executeUpdateStatement(data,
                    Arrays.asList("Price", "DateTime", "DoctorId", "PatientId", "IsClosed", "Id"),
                    statement);
            DaoUtils.commitConnection(connection);
            return data;
        } catch (SQLException e) {
            throw new DaoException("Unable update appointment!", e);
        }
    }

    @Override
    public List<AppointmentData> getAppointmentsByDoctorId(int doctorId, boolean isClosed) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_GET_APPOINTMENT_BY_DOCTOR_ID, connection);
            ResultSet resultSet = DaoUtils.executeSelectStatement(List.of(doctorId + ":int", isClosed + ":boolean"), statement);
            return DaoUtils.initObjectTypeList(new ArrayList<>(), AppointmentData.class, resultSet);
        } catch (SQLException e) {
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
            throw new DaoException("Unable to get appointment by id!");
        }
    }

    @Override
    public List<AppointmentData> getAppointmentsByPatientId(int patientId, boolean isClosed) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_GET_APPOINTMENT_BY_PATIENT_ID, connection);
            ResultSet resultSet = DaoUtils.executeSelectStatement(Collections.singletonList(patientId + ":int"), statement);
            return DaoUtils.initObjectTypeList(new ArrayList<>(), AppointmentData.class, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Unable get appointment by doctor!!", e);
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
            DaoUtils.commitConnection(connection);
            return deletedAppointment != 0;
        } catch (SQLException e) {
            throw new DaoException("Unable delete appointment!!", e);
        }
    }


}
