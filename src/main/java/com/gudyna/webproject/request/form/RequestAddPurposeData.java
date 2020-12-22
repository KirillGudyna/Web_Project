package com.gudyna.webproject.request.form;

import com.gudyna.webproject.model.entity.DrugData;
import com.gudyna.webproject.model.entity.MedicalProcedureData;
import com.gudyna.webproject.model.entity.PurposeData;

public class RequestAddPurposeData {
    PurposeData purpose;
    DrugData drug;
    MedicalProcedureData procedure;

    public PurposeData getPurpose() {
        return purpose;
    }

    public void setPurpose(PurposeData purpose) {
        this.purpose = purpose;
    }

    public DrugData getDrug() {
        return drug;
    }

    public void setDrug(DrugData drug) {
        this.drug = drug;
    }

    public MedicalProcedureData getProcedure() {
        return procedure;
    }

    public void setProcedure(MedicalProcedureData procedure) {
        this.procedure = procedure;
    }
}
