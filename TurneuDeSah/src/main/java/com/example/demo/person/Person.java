package com.example.demo.person;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Entity
@Table
public class Person {
    @Id
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )
    private Long id;
    private String lastName; //nume de familie
    private String name;
    private String cnp;
    private LocalDate dateOfBirth;
    private String gender;
    private String phoneNumber;
    private Boolean isActive;

    public Person() {

    }

    public Person(Long id,
                  String lastName,
                  String name,
                  String cnp,
                  LocalDate dateOfBirth,
                  String gender,
                  String phoneNumber,
                  Boolean isActive) {
        this.id = id;
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
                  LocalDate dateOfBirth,
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

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public String getCnp() {
        return cnp;
    }

    public LocalDate getDateOfBirth() {
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


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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

    @Override
    public String toString() {
        return "Person{" +
                "personID=" + id +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", cnp='" + cnp + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public Integer getAge(){
        return Period.between(dateOfBirth,LocalDate.now()).getYears();
    }
}
