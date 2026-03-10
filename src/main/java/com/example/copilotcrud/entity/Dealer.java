package com.example.copilotcrud.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "dealers")
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String dealerName;

    @Column(nullable = false, unique = true, length = 30)
    private String dealerCode;

    @Column(nullable = false, length = 60)
    private String city;

    @Column(nullable = false, length = 60)
    private String state;

    @Column(nullable = false, length = 20)
    private String contactNumber;

    @Column(nullable = false)
    private Integer serviceBayCount;

    @Column(nullable = false)
    private LocalDate onboardingDate;

    public Dealer() {
    }

    public Dealer(Long id, String dealerName, String dealerCode, String city, String state,
                  String contactNumber, Integer serviceBayCount, LocalDate onboardingDate) {
        this.id = id;
        this.dealerName = dealerName;
        this.dealerCode = dealerCode;
        this.city = city;
        this.state = state;
        this.contactNumber = contactNumber;
        this.serviceBayCount = serviceBayCount;
        this.onboardingDate = onboardingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getServiceBayCount() {
        return serviceBayCount;
    }

    public void setServiceBayCount(Integer serviceBayCount) {
        this.serviceBayCount = serviceBayCount;
    }

    public LocalDate getOnboardingDate() {
        return onboardingDate;
    }

    public void setOnboardingDate(LocalDate onboardingDate) {
        this.onboardingDate = onboardingDate;
    }
}
