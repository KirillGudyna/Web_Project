package com.gudyna.webproject.model.service;

import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.request.form.RequestAddPurposeData;
import com.gudyna.webproject.response.form.ResponsePurposeData;

import java.util.List;

/**
 * interface PurposeService for workimg with purpose
 */
public interface PurposeService {

    /**
     * Add purpose with drug data and procedure
     * @param requestAddPurposeData
     * @return purpose data
     * @throws ServiceException in case of DB errors during adding purpose
     */
    ResponsePurposeData addPurpose(RequestAddPurposeData requestAddPurposeData) throws ServiceException;

    /**
     * Get purpose by patient id
     * @param patientId
     * @return list woth purposes
     * @throws ServiceException when can't found or DB errors
     */
    List<ResponsePurposeData> getPurposeByPatientId(int patientId) throws ServiceException;

    /**
     * Get purpose by appointment id
     * @param appointmentId
     * @return founded purpose
     * @throws ServiceException in case of DB errors during adding user
     */
    ResponsePurposeData getPurposeByAppointmentId(int appointmentId) throws ServiceException;

    /**
     * Delete purpose
     * @param id
     * @return true if puropse deleted and false if not
     * @throws ServiceException in case of DB errors during adding user
     */
    boolean deletePurpose(int id) throws ServiceException;
}
