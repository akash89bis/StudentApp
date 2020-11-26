package com.example.studentapp.model;

public class StudentDetailsRequest {

    private String firstName;
    private String lastName;
    private String email;
    private double phoneNumber;
    private String sex;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNum(double phoneNum) {
        this.phoneNumber = phoneNum;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
