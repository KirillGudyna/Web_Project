package com.gudyna.webproject.model.dao.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.dao.PurposeDao;
import com.gudyna.webproject.model.entity.DrugData;
import com.gudyna.webproject.model.entity.MedicalProcedureData;
import com.gudyna.webproject.model.entity.PurposeData;
import com.gudyna.webproject.model.pool.ConnectionPool;
import com.gudyna.webproject.model.util.DaoUtils;
import com.gudyna.webproject.request.form.RequestPurposeAddData;
import com.gudyna.webproject.response.form.ResponsePurposeData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

public class PurposeDaoImpl implements PurposeDao {
    private static final String SQL_ADD_PURPOSE = "INSERT INTO purpose (appointment_id, diagnosis) VALUES(?)";
    private static final String SQL_ADD_DRUG = "INSERT INTO drug "
            + "(name,amount,term_taking,purpose_id) VALUES (?,?,?,?)";
    private static final String SQL_ADD_MEDICAL_PROCEDURE = "INSERT INTO medical_procedure "
            + "(name,date_start,date_end,purpose_id) VALUES (?,?,?,?)";
    private static final String SQL_DELETE_PURPOSE = "DELETE FROM purpose WHERE id=?";
    private static final String SQL_SELECT_PURPOSE_BY_APPOINTMENT_ID = "SELECT * FROM purpose WHERE appointment_id = ?";
    private static final PurposeDaoImpl INSTANCE = new PurposeDaoImpl();

    private PurposeDaoImpl() {
    }

    public static PurposeDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponsePurposeData addPurpose(RequestPurposeAddData data) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ResponsePurposeData responsePurposeData = new ResponsePurposeData();
            PurposeData purposeData = data.getPurposeData();
            PreparedStatement statement = DaoUtils.getStatement(SQL_ADD_PURPOSE, connection);
            ResultSet resultSet = DaoUtils.executeStatement(data,
                    Arrays.asList("AppointmentId", "Diagnosis"), statement);
            purposeData.setId(resultSet.getInt(1));
            responsePurposeData.setPurposeData(purposeData);

            statement = DaoUtils.getStatement(SQL_ADD_DRUG, connection);
            for (DrugData drugData : data.getDrugsDat()) {
                drugData.setPurposeId(purposeData.getId());
                resultSet = DaoUtils.executeStatement(drugData, Arrays.asList("Name", "Amount", "TermTaking", "PurposeId"), statement);
                drugData.setId(resultSet.getInt(1));
            }
            responsePurposeData.addAllDrugData(data.getDrugsDat());

            statement = DaoUtils.getStatement(SQL_ADD_MEDICAL_PROCEDURE, connection);
            for (MedicalProcedureData procedureData : data.getMedicalProceduresData()) {
                procedureData.setPurposeId(purposeData.getId());
                resultSet = DaoUtils.executeStatement(data,
                        Arrays.asList("Name", "DateStart", "DateEnd", "PurposeId"), statement);
                procedureData.setId(resultSet.getInt(1));
            }

            responsePurposeData.addAllProcedureData(data.getMedicalProceduresData());
            DaoUtils.commitConnection(connection);
            return responsePurposeData;
        } catch (SQLException e) {
            throw new DaoException("Unable to add purpose!", e);
        }
    }

    @Override
    public boolean deletePurpose(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_DELETE_PURPOSE, connection);
            int deletedPurpose = DaoUtils.executeDeleteStatement(id, statement);
            DaoUtils.commitConnection(connection);
            return deletedPurpose != 0;
        } catch (SQLException e) {
            throw new DaoException("Unable to delete purpose!", e);
        }
    }

    public PurposeData getPurposeByAppointmentId(int appointmentId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = DaoUtils.getStatement(SQL_SELECT_PURPOSE_BY_APPOINTMENT_ID, connection);
            ResultSet resultSet = DaoUtils.executeSelectStatement(Collections.singletonList(appointmentId + ":int"), statement);
            return DaoUtils.initObjectType(new PurposeData(), resultSet);
        } catch (SQLException e) {
            throw new DaoException("Unable get purpose by appointment`s id ", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
