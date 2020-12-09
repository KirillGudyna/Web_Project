package com.gudyna.webproject;

import com.google.gson.Gson;
import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.dao.AppointmentDao;
import com.gudyna.webproject.model.dao.impl.*;
import com.gudyna.webproject.model.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws DaoException {
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
        List<AppointmentData> dataList;
        dataList =AppointmentDaoImpl.getInstance().getAppointmentsByDoctorId(12,false);
        System.out.println(dataList);
        // System.out.println(drugDao.addDrug(drugData));
        //System.out.println(UserDaoImpl.getInstance().getUserByEmail("kirill.gud2000"));
    }
}
