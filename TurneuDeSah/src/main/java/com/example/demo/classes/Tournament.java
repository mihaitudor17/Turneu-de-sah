package com.example.demo.classes;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @SequenceGenerator(
            name = "tournament_sequence",
            sequenceName = "tournament_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tournament_sequence"
    )
    private Long id;
    private String name;
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    private LocalDate date;
    @DateTimeFormat(pattern= "HH:mm:ss")
    private LocalTime time;
    private double prizeMoney;
    private String phoneNumber;
    private Boolean isActive;
    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="participate",
            joinColumns = {@JoinColumn(name="tournament_id")},
            inverseJoinColumns = {@JoinColumn(name="person_id")}
    )
    private Set<Person> persons=new HashSet<>();

    public Tournament() {
    }

    public Tournament(String name, LocalDate date, LocalTime time, double prizeMoney, String phoneNumber, Boolean isActive) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.prizeMoney = prizeMoney;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

    public Tournament(String name, LocalDate date, LocalTime time, double prizeMoney, String phoneNumber, Boolean isActive, Set<Person> persons) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.prizeMoney = prizeMoney;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.persons = persons;
    }

    public Tournament(Long id, String name, LocalDate date, LocalTime time, double prizeMoney, String phoneNumber, Boolean isActive, Set<Person> persons) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.prizeMoney = prizeMoney;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.persons = persons;
    }

    public Tournament(Long id, String name, LocalDate date, LocalTime time, double prizeMoney, String phoneNumber, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.prizeMoney = prizeMoney;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getPrizeMoney() {
        return prizeMoney;
    }

    public void setPrizeMoney(double prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", prizeMoney=" + prizeMoney +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}

