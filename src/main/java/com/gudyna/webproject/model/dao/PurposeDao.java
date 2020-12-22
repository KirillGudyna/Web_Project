package com.gudyna.webproject.model.dao;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.entity.PurposeData;
import com.gudyna.webproject.request.form.RequestAddPurposeData;
import com.gudyna.webproject.response.form.ResponsePurposeData;

import java.util.Optional;

public interface PurposeDao {

    ResponsePurposeData addPurpose(RequestAddPurposeData purposeData) throws DaoException;

    Optional<PurposeData> getPurposeByAppointmentId(int appointmentId) throws DaoException;

    boolean existsPurposeByAppointmentId(int appointmentId) throws DaoException;

    boolean deletePurpose(int id) throws DaoException;
}
