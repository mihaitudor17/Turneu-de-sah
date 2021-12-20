package com.example.demo.classes;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Entity
@Table(name = "person")
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
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String gender;
    private String phoneNumber;
    private Integer rank;
    private boolean isActive;
    @ManyToOne
    private User user;

    public Person() {

    }

    public Person(Long id,
                  String lastName,
                  String name,
                  String cnp,
                  LocalDate dateOfBirth,
                  String gender,
                  String phoneNumber,
                  Integer rank,
                  boolean isActive) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.cnp = cnp;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
        this.isActive = isActive;
    }

    public Person(String lastName,
                  String name,
                  String cnp,
                  LocalDate dateOfBirth,
                  String gender,
                  String phoneNumber,
                  Integer rank,
                  boolean isActive) {
        this.lastName = lastName;
        this.name = name;
        this.cnp = cnp;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
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

    public boolean getActive() {
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

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", cnp='" + cnp + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", rank=" + rank +
                ", isActive=" + isActive +
                ", user=" + user +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
