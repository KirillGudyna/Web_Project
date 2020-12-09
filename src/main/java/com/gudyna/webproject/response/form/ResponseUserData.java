package com.gudyna.webproject.response.form;

import com.gudyna.webproject.model.entity.UserData;

public class ResponseUserData {
    private int id;
    private String name;
    private String surname;
    private String address;
    private String dateOfBirth;
    private String email;
    private boolean isDoctor;
    private String jobType;
    private String timeWork;
    private String profession;

    private ResponseUserData(UserData userData) {
        this.id = userData.getId();
        this.name = userData.getName();
        this.surname = userData.getSurname();
        this.address = userData.getAddress();
        this.dateOfBirth = userData.getDateOfBirth();
        this.email = userData.getEmail();
    }

    public static ResponseUserData fromUserData(UserData userData) {
        return new ResponseUserData(userData);
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setTimeWork(String timeWork) {
        this.timeWork = timeWork;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public String getJobType() {
        return jobType;
    }

    public String getTimeWork() {
        return timeWork;
    }

    public String getProfession() {
        return profession;
    }
}
