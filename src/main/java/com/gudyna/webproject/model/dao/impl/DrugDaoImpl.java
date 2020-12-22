package com.gudyna.webproject.model.dao.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.dao.DrugDao;
import com.gudyna.webproject.model.entity.DrugData;
import com.gudyna.webproject.model.pool.ConnectionPool;
import com.gudyna.webproject.model.util.DaoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DrugDaoImpl implements DrugDao {

    private static final Logger LOGGER = LogManager.getLogger(DrugDaoImpl.class);
    private static final String SQL_ADD_DRUG = "INSERT INTO drug "
            + "(name,amount,term_taking,purpose_id) VALUES (?,?,?,?)";
    private static final String SQL_DELETE_DRUG = "DELETE FROM drug WHERE purpose_id=?";
    private static final String SQL_SELECT_DRUG_BY_PURPOSE_ID = "SELECT* FROM drug WHERE purpose_id=?";

    private static final DrugDaoImpl INSTANCE = new DrugDaoImpl();

    private DrugDaoImpl() {
    }

    public static DrugDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public DrugData addDrug(DrugData data) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_ADD_DRUG, connection);
            ResultSet resultSet = DaoUtils.executeStatement(data,
                    Arrays.asList("Name", "Amount", "TermTaking", "PurposeId"), statement);
            data.setId(resultSet.getInt(1));

            return data;
        } catch (SQLException e) {
            LOGGER.error("Unable to add data about drug!", e);
            throw new DaoException("Unable to add data about drug!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public boolean deleteDrug(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_DELETE_DRUG, connection);
            int deletedDrugId = DaoUtils.executeDeleteStatement(id, statement);

            return deletedDrugId != 0;
        } catch (SQLException e) {
            LOGGER.error("Unable to delete data about drug!", e);
            throw new DaoException("Unable to delete data about drug!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public List<DrugData> getDrugByPurposeId(int purposeId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_SELECT_DRUG_BY_PURPOSE_ID, connection, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet resultSet = DaoUtils.executeSelectStatement(Collections.singletonList(purposeId + ":int"), statement);

            return resultSet.first()
            ?DaoUtils.initObjectTypeList(new ArrayList<>(), DrugData.class, resultSet)
                    :List.of();
        } catch (SQLException e) {
            LOGGER.error("Unable to delete data about drug!", e);
            throw new DaoException("Unable to delete data about drug!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
