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
import com.gudyna.webproject.model.entity.DrugData;
import com.gudyna.webproject.model.entity.MedicalProcedureData;
import com.gudyna.webproject.model.entity.PurposeData;
import com.gudyna.webproject.model.service.PurposeService;
import com.gudyna.webproject.request.form.RequestPurposeAddData;
import com.gudyna.webproject.response.form.ResponsePurposeData;

import java.util.ArrayList;
import java.util.List;

public class PurposeServiceImpl implements PurposeService {
    private final DrugDao drugDao = DrugDaoImpl.getInstance();
    private final MedicalProcedureDao procedureDao = MedicalProcedureDaoImpl.getInstance();
    private final PurposeDao purposeDao = PurposeDaoImpl.getInstance();
    private final AppointmentDao appointmentDao = AppointmentDaoImpl.getInstance();

    private static final PurposeServiceImpl INSTANCE = new PurposeServiceImpl();

    private PurposeServiceImpl() {

    }

    public static PurposeServiceImpl getInstance(){
        return INSTANCE;
    }
    @Override
    public ResponsePurposeData addPurpose(RequestPurposeAddData requestPurposeAddData) throws ServiceException {
        try {
            return purposeDao.addPurpose(requestPurposeAddData);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<ResponsePurposeData> getPurposeByPatientId(int patientId) throws ServiceException {
        List<ResponsePurposeData> dataList = new ArrayList<>();
        try {
            List<AppointmentData> appointmentDataList = appointmentDao.getAppointmentsByPatientId(patientId,false);
            for (AppointmentData appointmentData : appointmentDataList) {
                ResponsePurposeData data = new ResponsePurposeData();
                PurposeData purposeData = purposeDao.getPurposeByAppointmentId(appointmentData.getId());
                data.addAllDrugData(drugDao.getDrugByPurposeId(purposeData.getId()));
                data.addAllProcedureData(procedureDao.getProcedureByPurposeId(purposeData.getId()));
                data.setPurposeData(purposeData);
                dataList.add(data);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return dataList;
    }

    @Override
    public boolean deletePurpose(int id) throws ServiceException {
        try {
            drugDao.deleteDrug(id);
            procedureDao.deleteProcedure(id);
            purposeDao.deletePurpose(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }
}
