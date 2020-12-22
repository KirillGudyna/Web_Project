package com.gudyna.webproject.model.entity;

public class DoctorData {
    private String timeWork;
    private String jobType;
    private int maxAppPerDay;
    private int minPrice;
    private int userId;

    public DoctorData() {
    }

    public DoctorData(String timeWork, String jobType, int userId) {
        this.timeWork = timeWork;
        this.jobType = jobType;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTimeWork() {
        return timeWork;
    }

    public int getMaxAppPerDay() {
        return maxAppPerDay;
    }

    public void setMaxAppPerDay(int maxAppPerDay) {
        this.maxAppPerDay = maxAppPerDay;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorData that = (DoctorData) o;
        return getTimeWork().equals(that.getTimeWork()) &&
                getJobType().equals(that.getJobType()) &&
                getUserId() == that.getUserId();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 31 * result + jobType.hashCode();
        result += 31 * result + timeWork.hashCode();
        result += 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DoctorData{\n");
        builder.append("timeWork=");
        builder.append(timeWork);
        builder.append("\n");
        builder.append("jobType=");
        builder.append(jobType);
        builder.append("\n");
        builder.append("userId=");
        builder.append(userId);
        builder.append("\n}");
        return builder.toString();
    }
}
