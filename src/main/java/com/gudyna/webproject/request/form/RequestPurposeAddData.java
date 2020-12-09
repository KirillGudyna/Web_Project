package com.gudyna.webproject.request.form;

import com.gudyna.webproject.model.entity.DrugData;
import com.gudyna.webproject.model.entity.MedicalProcedureData;
import com.gudyna.webproject.model.entity.PurposeData;

import java.util.List;

public class RequestPurposeAddData {
    PurposeData purposeData;
    List<DrugData> drugsDat;
    java.util.List<MedicalProcedureData> medicalProceduresData;

    public PurposeData getPurposeData() {
        return purposeData;
    }

    public List<DrugData> getDrugsDat() {
        return drugsDat;
    }

    public List<MedicalProcedureData> getMedicalProceduresData() {
        return medicalProceduresData;
    }
}
