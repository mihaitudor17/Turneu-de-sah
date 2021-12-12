package com.example.demo.classes;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
    private LocalDate date;
    private LocalTime time;
    private double prizeMoney;
    private String phoneNumber;
    private Boolean isActive;

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
}

