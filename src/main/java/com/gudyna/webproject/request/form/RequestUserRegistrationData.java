package com.gudyna.webproject.request.form;

import com.gudyna.webproject.model.entity.UserData;

public class RequestUserRegistrationData {
    private int userId;
    private String name;
    private String surname;
    private String address;
    private String dateOfBirth;
    private String password;
    private String email;
    private boolean isDoctor;
    private String keyDoctor;
    private String timeWork;
    private String jobType;
    private String profession;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean isDoctor) {
        this.isDoctor=isDoctor;
    }

    public String getKeyDoctor() {
        return keyDoctor;
    }

    public void setKeyDoctor(String keyDoctor) {
        this.keyDoctor = keyDoctor;
    }

    public String getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(String timeWork) {
        this.timeWork = timeWork;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public UserData toData() {
        UserData userData = new UserData();
        userData.setEmail(email);
        userData.setName(name);
        userData.setSurname(surname);
        userData.setDateOfBirth(dateOfBirth);
        userData.setAddress(address);
        userData.setPassword(password);
        return userData;
    }
}
