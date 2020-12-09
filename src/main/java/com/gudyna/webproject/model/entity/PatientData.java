package com.gudyna.webproject.model.entity;

public class PatientData {
    private int id;
    private int userId;
    private String profession;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientData that = (PatientData) o;
        return getId() == that.getId() &&
                getUserId() == that.getUserId() &&
                getProfession().equals(that.getProfession());
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 31 * result + id;
        result += 31 * result + userId;
        result += 31 * result + profession.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PatientData{\n");
        builder.append("id=");
        builder.append(id);
        builder.append("\n");
        builder.append("userId=");
        builder.append(userId);
        builder.append("\n");
        builder.append("profession=");
        builder.append(profession);
        builder.append("\n}");
        return builder.toString();
    }

}
