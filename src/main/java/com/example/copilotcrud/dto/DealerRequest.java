package com.example.copilotcrud.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class DealerRequest {

    @NotBlank(message = "Dealer name is required")
    @Size(max = 120, message = "Dealer name cannot exceed 120 characters")
    private String dealerName;

    @NotBlank(message = "Dealer code is required")
    @Size(max = 30, message = "Dealer code cannot exceed 30 characters")
    private String dealerCode;

    @NotBlank(message = "City is required")
    @Size(max = 60, message = "City cannot exceed 60 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 60, message = "State cannot exceed 60 characters")
    private String state;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[0-9+\\- ]{8,20}$", message = "Contact number must be valid")
    private String contactNumber;

    @NotNull(message = "Service bay count is required")
    @Min(value = 1, message = "Service bay count must be at least 1")
    @Max(value = 100, message = "Service bay count cannot exceed 100")
    private Integer serviceBayCount;

    @NotNull(message = "Onboarding date is required")
    @PastOrPresent(message = "Onboarding date cannot be in the future")
    private LocalDate onboardingDate;

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
