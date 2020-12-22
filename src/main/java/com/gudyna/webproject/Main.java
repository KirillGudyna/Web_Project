package com.gudyna.webproject;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.entity.*;
import com.gudyna.webproject.model.service.impl.PurposeServiceImpl;
import com.gudyna.webproject.request.form.RequestAddPurposeData;

public class Main {
    public static void main(String[] args) throws DaoException, ServiceException {
        /*AppointmentData data = new AppointmentData();
        data.setDate("2020.11.20");
        data.setPrice(2000);
        data.setDoctorId(12);
        data.setPatientId(2);
        PurposeData purposeData = new PurposeData();
       // data = AppointmentDaoImpl.getInstance().addAppointment(data);
        purposeData.setAppointmentId(data.getId());
       // purposeData =PurposeDaoImpl.getInstance().addPurpose(purposeData);
        System.out.println(purposeData);
        List<DrugData> list = new ArrayList<>();
        for(int i = 0;i<3;i++){
            DrugData drugData = new DrugData();
            drugData.setPurposeId(purposeData.getId());
            drugData.setTermTaking(10);
            drugData.setAmount(43);
            drugData.setName("kokain"+i);
            list.add(drugData);
        }
        List<MedicalProcedureData> list1 = new ArrayList<>();
        for(int i = 0;i<2;i++) {
            MedicalProcedureData data1 = new MedicalProcedureData();
            data1.setDateStart("2020.10.2"+i);
            data1.setDateEnd("2020.11.2"+i);
            data1.setName("masaasdg"+i);
            data1.setPurposeId(2);
            list1.add(data1);
        }
        for(DrugData datarrr:list){
         //   DrugDaoImpl.getInstance().addDrug(datarrr);
        }
        for(MedicalProcedureData data1rrr: list1){
            MedicalProcedureDaoImpl.getInstance().addMedicalProcedure(data1rrr);
        }*/
        /*DrugData drugData = new DrugData();
        drugData.setPurposeId(2);
        drugData.setTermTaking(10);
        drugData.setAmount(43);
        drugData.setName("kokainasdasdasd");
        DrugDaoImpl drugDao = DrugDaoImpl.getInstance();
        List<DoctorData> list  = DoctorDaoImpl.getInstance().getAllDoctors();
        for(DoctorData data:list){
            System.out.println(UserDaoImpl.getInstance().getUserById(data.getUserId()));
        }*/
        PurposeData purposeData = new PurposeData();
        purposeData.setAppointmentId(6);
        purposeData.setDiagnosis("lol");
        DrugData drugData = new DrugData();
        drugData.setTermTaking(10);
        drugData.setAmount(43);
        drugData.setName("kokainasdasdasd");
        MedicalProcedureData data1 = new MedicalProcedureData();
        data1.setDateStart("2020.10.2");
        data1.setDateEnd("2020.11.2");
        data1.setName("masaasdg");
        RequestAddPurposeData requestAddPurposeData = new RequestAddPurposeData();
        requestAddPurposeData.setProcedure(data1);
        requestAddPurposeData.setDrug(drugData);
        requestAddPurposeData.setPurpose(purposeData);
        System.out.println(PurposeServiceImpl.getInstance().addPurpose(requestAddPurposeData));

    }
}
