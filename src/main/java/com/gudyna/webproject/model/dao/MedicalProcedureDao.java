package com.gudyna.webproject.model.dao;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.entity.MedicalProcedureData;

import java.util.List;

public interface MedicalProcedureDao {

    MedicalProcedureData addMedicalProcedure(MedicalProcedureData data) throws DaoException;

    List<MedicalProcedureData> getProcedureByPurposeId(int purposeId) throws DaoException;

    boolean deleteProcedure(int id) throws DaoException;
}
