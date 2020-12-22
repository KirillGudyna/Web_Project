package com.gudyna.webproject.model.dao.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.dao.MedicalProcedureDao;
import com.gudyna.webproject.model.entity.MedicalProcedureData;
import com.gudyna.webproject.model.pool.ConnectionPool;
import com.gudyna.webproject.model.util.DaoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MedicalProcedureDaoImpl implements MedicalProcedureDao {

    private static final Logger LOGGER = LogManager.getLogger(MedicalProcedureDaoImpl.class);
    private static final String SQL_GET_PROCEDURE_BY_PURPOSE_ID = "SELECT* FROM medical_procedure WHERE purpose_id=?";
    private static final String SQL_ADD_MEDICAL_PROCEDURE = "INSERT INTO medical_procedure "
            + "(name,date_start,date_end,purpose_id) VALUES (?,?,?,?)";
    private static final String SQL_DELETE_MEDICAL_PROCEDURE = "DELETE FROM medical_procedure WHERE purpose_id=?";

    private static final MedicalProcedureDaoImpl INSTANCE = new MedicalProcedureDaoImpl();

    private MedicalProcedureDaoImpl() {
    }

    public static MedicalProcedureDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public MedicalProcedureData addMedicalProcedure(MedicalProcedureData data) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_ADD_MEDICAL_PROCEDURE, connection);
            ResultSet resultSet = DaoUtils.executeStatement(data,
                    Arrays.asList("Name", "DateStart", "DateEnd", "PurposeId"), statement);
            data.setId(resultSet.getInt(1));

            return data;
        } catch (SQLException e) {
            LOGGER.error("Unable to add procedure", e);
            throw new DaoException("Unable to add procedure", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public List<MedicalProcedureData> getProcedureByPurposeId(int purposeId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_GET_PROCEDURE_BY_PURPOSE_ID, connection, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet resultSet = DaoUtils.executeSelectStatement(Collections.singletonList(purposeId + ":int"), statement);

            return resultSet.first()
                    ? DaoUtils.initObjectTypeList(new ArrayList<>(), MedicalProcedureData.class, resultSet)
                    : List.of();
        } catch (SQLException e) {
            LOGGER.error("Unable to get procedure!!", e);
            throw new DaoException("Unable to get procedure!!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public boolean deleteProcedure(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_DELETE_MEDICAL_PROCEDURE, connection);
            int deletedProcedureId = DaoUtils.executeDeleteStatement(id, statement);

            return deletedProcedureId != 0;
        } catch (SQLException e) {
            LOGGER.error("Unable to delete procedure!!", e);
            throw new DaoException("Unable to delete procedure!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
