package com.gudyna.webproject.model.service;

import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.entity.DrugData;
import com.gudyna.webproject.model.entity.MedicalProcedureData;
import com.gudyna.webproject.model.entity.PurposeData;
import com.gudyna.webproject.request.form.RequestPurposeAddData;
import com.gudyna.webproject.response.form.ResponsePurposeData;

import java.util.List;

public interface PurposeService {
    ResponsePurposeData addPurpose(RequestPurposeAddData requestPurposeAddData) throws ServiceException;

    List<ResponsePurposeData> getPurposeByPatientId(int patientId) throws ServiceException;

    boolean deletePurpose(int id) throws ServiceException;
}
