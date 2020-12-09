package com.gudyna.webproject.model.entity;

public class DrugData {
    private String name;
    private int termTaking;
    private int amount;
    private int id;
    private int purposeId;

    public String getName() {
        return name;
    }

    public int getPurposeId() {
        return purposeId;
    }

    public void setPurposeId(int purposeId) {
        this.purposeId = purposeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTermTaking() {
        return termTaking;
    }

    public void setTermTaking(int termTaking) {
        this.termTaking = termTaking;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrugData drugData = (DrugData) o;
        return getAmount() == drugData.getAmount() &&
                getId() == drugData.getId() &&
                getName().equals(drugData.getName()) &&
                getTermTaking() == drugData.getTermTaking() &&
                getPurposeId() == drugData.getPurposeId();
    }

    @Override
    public int hashCode() {
        int result = amount;
        result = 31 * result + name.hashCode();
        result = 31 * result + id;
        result = 31 * result + termTaking;
        result = 31 * result + purposeId;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DrugData{\n");
        stringBuilder.append("id=");
        stringBuilder.append(id);
        stringBuilder.append("\n");
        stringBuilder.append("name=");
        stringBuilder.append(name);
        stringBuilder.append("\n");
        stringBuilder.append("purposeDate=");
        stringBuilder.append(termTaking);
        stringBuilder.append("\n");
        stringBuilder.append("amount=");
        stringBuilder.append(amount);
        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }
}
