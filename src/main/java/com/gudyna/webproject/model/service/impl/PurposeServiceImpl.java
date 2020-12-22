package com.gudyna.webproject.model.service.impl;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.dao.AppointmentDao;
import com.gudyna.webproject.model.dao.DrugDao;
import com.gudyna.webproject.model.dao.MedicalProcedureDao;
import com.gudyna.webproject.model.dao.PurposeDao;
import com.gudyna.webproject.model.dao.impl.AppointmentDaoImpl;
import com.gudyna.webproject.model.dao.impl.DrugDaoImpl;
import com.gudyna.webproject.model.dao.impl.MedicalProcedureDaoImpl;
import com.gudyna.webproject.model.dao.impl.PurposeDaoImpl;
import com.gudyna.webproject.model.entity.AppointmentData;
import com.gudyna.webproject.model.entity.PurposeData;
import com.gudyna.webproject.model.service.PurposeService;
import com.gudyna.webproject.request.form.RequestAddPurposeData;
import com.gudyna.webproject.response.form.ResponsePurposeData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PurposeServiceImpl implements PurposeService {

    private static final Logger LOGGER = LogManager.getLogger(PurposeServiceImpl.class);

    private final DrugDao drugDao = DrugDaoImpl.getInstance();
    private final MedicalProcedureDao procedureDao = MedicalProcedureDaoImpl.getInstance();
    private final PurposeDao purposeDao = PurposeDaoImpl.getInstance();
    private final AppointmentDao appointmentDao = AppointmentDaoImpl.getInstance();

    private static final PurposeServiceImpl INSTANCE = new PurposeServiceImpl();

    private PurposeServiceImpl() {

    }

    public static PurposeServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponsePurposeData addPurpose(RequestAddPurposeData requestAddPurposeData) throws ServiceException {
        try {
            return purposeDao.addPurpose(requestAddPurposeData);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<ResponsePurposeData> getPurposeByPatientId(int patientId) throws ServiceException {
        List<ResponsePurposeData> dataList = new ArrayList<>();
        try {
            List<AppointmentData> appointmentDataList = appointmentDao.getAppointmentsByPatientId(patientId);
            for (AppointmentData appointmentData : appointmentDataList) {
                ResponsePurposeData data = new ResponsePurposeData();
                Optional<PurposeData> purposeData = purposeDao.getPurposeByAppointmentId(appointmentData.getId());
                if (purposeData.isPresent()) {
                    PurposeData purpose = purposeData.get();
                    data.addAllDrugData(drugDao.getDrugByPurposeId(purpose.getId()));
                    data.addAllProcedureData(procedureDao.getProcedureByPurposeId(purpose.getId()));
                    data.setPurposeData(purpose);
                }
                dataList.add(data);
            }

            return dataList;
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ResponsePurposeData getPurposeByAppointmentId(int appointmentId) throws ServiceException {
        ResponsePurposeData data = new ResponsePurposeData();
        try {
            Optional<PurposeData> purposeData = purposeDao.getPurposeByAppointmentId(appointmentId);
            if (purposeData.isPresent()) {
                PurposeData purpose = purposeData.get();
                data.setPurposeData(purpose);
                data.addAllProcedureData(procedureDao.getProcedureByPurposeId(purpose.getId()));
                data.addAllDrugData(drugDao.getDrugByPurposeId(purpose.getId()));
            }

            return data;
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean deletePurpose(int id) throws ServiceException {
        try {
            drugDao.deleteDrug(id);
            procedureDao.deleteProcedure(id);
            purposeDao.deletePurpose(id);
            return true;
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
