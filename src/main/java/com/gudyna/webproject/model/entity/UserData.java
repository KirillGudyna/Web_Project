package com.gudyna.webproject.model.entity;

public class UserData {
    private int id;
    private String name;
    private String surname;
    private String address;
    private String dateOfBirth;
    private String password;
    private String email;

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return getName().equals(userData.getName()) &&
                getSurname().equals(userData.getSurname()) &&
                getAddress().equals(userData.getAddress()) &&
                getDateOfBirth().equals(userData.getDateOfBirth()) &&
                getId()==userData.getId() &&
                getEmail().equals(userData.getEmail());
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 31 * result + name.hashCode();
        result += 31 * result + surname.hashCode();
        result += 31 * result + address.hashCode();
        result += 31 * result + dateOfBirth.hashCode();
        result += 31*result + id;
        result += 31*result+email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserData{\n");
        builder.append("id");
        builder.append(id);
        builder.append("\n");
        builder.append("email=");
        builder.append(email);
        builder.append("\n");
        builder.append("name=");
        builder.append(name);
        builder.append("\n");
        builder.append("surname=");
        builder.append(surname);
        builder.append("\n");
        builder.append("address=");
        builder.append(address);
        builder.append("\n");
        builder.append("dateOfBirth=");
        builder.append(dateOfBirth);
        builder.append("}");
        return builder.toString();
    }
}
