package com.example.demo.person;
import java.util.Date;

public class Person {
    private int personID;
    private String lastName; //nume de familie
    private String name;
    private String cnp;
    private Date dateOfBirth;
    private String gender;
    private String phoneNumber;
    private Boolean isActive;

    public Person(){

    }

    public Person(int personID,
                  String lastName,
                  String name,
                  String cnp,
                  Date dateOfBirth,
                  String gender,
                  String phoneNumber,
                  Boolean isActive) {
        this.personID = personID;
        this.lastName = lastName;
        this.name = name;
        this.cnp = cnp;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

    public Person(String lastName,
                  String name,
                  String cnp,
                  Date dateOfBirth,
                  String gender,
                  String phoneNumber,
                  Boolean isActive) {
        this.lastName = lastName;
        this.name = name;
        this.cnp = cnp;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

    public int getPersonID() {
        return personID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public String getCnp() {
        return cnp;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
