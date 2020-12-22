package com.gudyna.webproject.response.form;

import com.gudyna.webproject.model.entity.DrugData;
import com.gudyna.webproject.model.entity.MedicalProcedureData;
import com.gudyna.webproject.model.entity.PurposeData;

import java.util.ArrayList;
import java.util.List;

public class ResponsePurposeData {

    private PurposeData purposeData;
    private final List<DrugData> drugsData;
    private final List<MedicalProcedureData> proceduresData;

    public void setPurposeData(PurposeData purposeData) {
        this.purposeData = purposeData;
    }

    public PurposeData getPurposeData() {
        return purposeData;
    }

    public List<DrugData> getDrugsData() {
        return drugsData;
    }

    public List<MedicalProcedureData> getProceduresData() {
        return proceduresData;
    }

    public ResponsePurposeData() {
        drugsData = new ArrayList<>();
        proceduresData = new ArrayList<>();
    }

    public boolean addDrugData(DrugData data) {
        return drugsData.add(data);
    }

    public boolean addProcedure(MedicalProcedureData data) {
        return proceduresData.add(data);
    }
    public boolean addAllDrugData(List<DrugData> drugsData){
        return  this.drugsData.addAll(drugsData);
    }

    public boolean addAllProcedureData(List<MedicalProcedureData> proceduresData){
        return this.proceduresData.addAll(proceduresData);
    }

    @Override
    public String toString() {
        return "ResponsePurposeData{" +
                "purposeData=" + purposeData +
                ", drugsData=" + drugsData +
                ", proceduresData=" + proceduresData +
                '}';
    }
}
