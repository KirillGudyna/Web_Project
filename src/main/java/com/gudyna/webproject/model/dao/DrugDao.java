package com.gudyna.webproject.model.dao;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.entity.DrugData;

import java.util.List;

public interface DrugDao {

    DrugData addDrug(DrugData data) throws DaoException;

    List<DrugData> getDrugByPurposeId(int id) throws DaoException;

    boolean deleteDrug(int id) throws DaoException;
}
