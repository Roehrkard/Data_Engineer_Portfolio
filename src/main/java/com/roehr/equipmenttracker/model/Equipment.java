package com.roehr.equipmenttracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private int qty;

    @Column(length = 1024)
    private String description;

    // Constructors
    public Equipment() {
    }

    public Equipment(String name, String status, int qty, String description) {
        this.name = name;
        this.status = status;
        this.qty = qty;
        this.description = description;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }
}
