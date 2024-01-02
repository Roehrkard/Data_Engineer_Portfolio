package com.roehr.equipmenttracker.dto;

public class LoginDto {
    private String username;
    private String providedPin;

    // Constructors, Getters, and Setters
    public LoginDto() {}

    public LoginDto(String username, String providedPin) {
        this.username = username;
        this.providedPin = providedPin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProvidedPin() {
        return providedPin;
    }

    public void setProvidedPin(String providedPin) {
        this.providedPin = providedPin;
    }
}
