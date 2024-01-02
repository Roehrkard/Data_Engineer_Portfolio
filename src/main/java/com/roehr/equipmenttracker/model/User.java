package com.roehr.equipmenttracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    // The pin is stored as a bcrypt hash
    @Column(nullable = false)
    private String hashedPin;

    // Default constructor for JPA
    public User() {
    }

    // Constructor with parameters
    public User(String username, String hashedPin) {
        this.username = username;
        this.hashedPin = hashedPin;
    }

    // Getters for id, username, and hashedPin
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPin() {
        return hashedPin;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Setter for id
    public void setId(Long id) {
        this.id = id;
    }

}

