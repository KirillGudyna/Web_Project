package com.gudyna.webproject.model.entity;

public class MedicalProcedureData {
    private String name;
    private String dateStart;
    private String dateEnd;
    private int id;
    private int purposeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurposeId() {
        return purposeId;
    }

    public void setPurposeId(int purposeId) {
        this.purposeId = purposeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalProcedureData that = (MedicalProcedureData) o;
        return getId() == that.getId() &&
                getPurposeId() == that.getPurposeId() &&
                getName().equals(that.getName()) &&
                getDateStart().equals(that.getDateStart()) &&
                getDateEnd().equals(that.getDateEnd());
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + name.hashCode();
        result = 31 * result + id;
        result = 31 * result + dateStart.hashCode();
        result = 31 * result + dateEnd.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MedicalProcedureData{\n");
        builder.append("name=");
        builder.append(name);
        builder.append("\n");
        builder.append("dateStart=");
        builder.append(dateStart);
        builder.append("\n");
        builder.append("dateEnd=");
        builder.append(dateEnd);
        builder.append("\n");
        builder.append("id=");
        builder.append(id);
        builder.append("\n");
        builder.append("purposeId=");
        builder.append(purposeId);
        builder.append("\n}");
        return builder.toString();
    }
}
