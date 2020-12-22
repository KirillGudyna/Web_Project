package com.gudyna.webproject.model.dao.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.dao.PurposeDao;
import com.gudyna.webproject.model.entity.DrugData;
import com.gudyna.webproject.model.entity.MedicalProcedureData;
import com.gudyna.webproject.model.entity.PurposeData;
import com.gudyna.webproject.model.pool.ConnectionPool;
import com.gudyna.webproject.model.util.DaoUtils;
import com.gudyna.webproject.request.form.RequestAddPurposeData;
import com.gudyna.webproject.response.form.ResponsePurposeData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class PurposeDaoImpl implements PurposeDao {

    private static final Logger LOGGER = LogManager.getLogger(PurposeDaoImpl.class);
    private static final String SQL_ADD_PURPOSE = "INSERT INTO purpose (appointment_id, diagnosis) VALUES(?,?)";
    private static final String SQL_ADD_DRUG = "INSERT INTO drug "
            + "(name,amount,term_taking,purpose_id) VALUES (?,?,?,?)";
    private static final String SQL_ADD_MEDICAL_PROCEDURE = "INSERT INTO medical_procedure "
            + "(name,date_start,date_end,purpose_id) VALUES (?,?,?,?)";
    private static final String SQL_DELETE_PURPOSE = "DELETE FROM purpose WHERE id=?";
    private static final String SQL_SELECT_PURPOSE_BY_APPOINTMENT_ID = "SELECT * FROM purpose WHERE appointment_id = ?";
    private static final String COUNT_PURPOSE_BY_APPOINTMENT_ID = "SELECT count(*) FROM purpose WHERE appointment_id = ?";

    private static final PurposeDaoImpl INSTANCE = new PurposeDaoImpl();

    private PurposeDaoImpl() {
    }

    public static PurposeDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponsePurposeData addPurpose(RequestAddPurposeData requestAddPurpose) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            ResponsePurposeData responsePurpose = new ResponsePurposeData();

            PurposeData purpose = savePurpose(connection, requestAddPurpose.getPurpose());
            responsePurpose.setPurposeData(purpose);
            int purposeId = purpose.getId();
            if (requestAddPurpose.getDrug() != null) {
                DrugData drug = saveDrug(connection, requestAddPurpose.getDrug(), purposeId);
                responsePurpose.addDrugData(drug);
            }
            if (requestAddPurpose.getProcedure() != null) {
                MedicalProcedureData procedure = saveProcedure(connection, requestAddPurpose.getProcedure(), purposeId);
                responsePurpose.addProcedure(procedure);
            }
            DaoUtils.commitOrRollbackConnection(connection);

            return responsePurpose;
        } catch (SQLException e) {
            LOGGER.error("Unable to add purpose!", e);
            throw new DaoException("Unable to add purpose!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public Optional<PurposeData> getPurposeByAppointmentId(int appointmentId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_SELECT_PURPOSE_BY_APPOINTMENT_ID, connection, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet resultSet = DaoUtils.executeSelectStatement(Collections.singletonList(appointmentId + ":int"), statement);

            return resultSet.first()
                    ? Optional.of(DaoUtils.initObjectType(new PurposeData(), resultSet))
                    : Optional.empty();
        } catch (SQLException e) {
            LOGGER.error("Unable get purpose by appointment`s id ", e);
            throw new DaoException("Unable get purpose by appointment`s id ", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public boolean existsPurposeByAppointmentId(int appointmentId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(COUNT_PURPOSE_BY_APPOINTMENT_ID, connection);
            ResultSet resultSet = DaoUtils.executeSelectStatement(Collections.singletonList(appointmentId + ":int"), statement);

            return resultSet.getInt(1) != 0;
        } catch (SQLException e) {
            LOGGER.error("Unable get purpose by appointment`s id ", e);
            throw new DaoException("Unable get purpose by appointment`s id ", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public boolean deletePurpose(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_DELETE_PURPOSE, connection);
            int deletedPurpose = DaoUtils.executeDeleteStatement(id, statement);

            return deletedPurpose != 0;
        } catch (SQLException e) {
            LOGGER.error("Unable to delete purpose!", e);
            throw new DaoException("Unable to delete purpose!", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    private PurposeData savePurpose(Connection connection, PurposeData purpose) throws SQLException {
        PreparedStatement statement = DaoUtils.getStatement(SQL_ADD_PURPOSE, connection);
        ResultSet resultSet = DaoUtils.executeStatement(purpose,
                Arrays.asList("AppointmentId", "Diagnosis"), statement);
        purpose.setId(resultSet.getInt(1));

        return purpose;
    }

    private DrugData saveDrug(Connection connection, DrugData drug, int purposeId) throws SQLException {
        //drug.setPurposeId(purposeId);

        PreparedStatement statement = DaoUtils.getStatement(SQL_ADD_DRUG, connection);
        ResultSet resultSet = DaoUtils.executeStatement(drug,
                Arrays.asList("Name", "Amount", "TermTaking", "PurposeId"), statement);
        drug.setId(resultSet.getInt(1));

        return drug;
    }

    private MedicalProcedureData saveProcedure(Connection connection, MedicalProcedureData procedure, int purposeId) throws SQLException {
        procedure.setPurposeId(purposeId);

        PreparedStatement statement = DaoUtils.getStatement(SQL_ADD_MEDICAL_PROCEDURE, connection);
        ResultSet resultSet = DaoUtils.executeStatement(procedure,
                Arrays.asList("Name", "DateStart", "DateEnd", "PurposeId"), statement);
        procedure.setId(resultSet.getInt(1));

        return procedure;
    }
}
