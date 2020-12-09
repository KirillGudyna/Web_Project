package com.gudyna.webproject.model.dao;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.entity.PurposeData;
import com.gudyna.webproject.request.form.RequestPurposeAddData;
import com.gudyna.webproject.response.form.ResponsePurposeData;

public interface PurposeDao {
    ResponsePurposeData addPurpose(RequestPurposeAddData data) throws DaoException;

    boolean deletePurpose(int id) throws DaoException;

    PurposeData getPurposeByAppointmentId(int appointmentId) throws DaoException;
}
