package com.roehr.equipmenttracker.controller;

import com.roehr.equipmenttracker.model.Equipment;
import com.roehr.equipmenttracker.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        return ResponseEntity.ok(equipmentService.getAllEquipment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        if (equipment != null) {
            return ResponseEntity.ok(equipment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/quantity")
    public ResponseEntity<Equipment> updateEquipmentQuantity(@PathVariable Long id, @RequestParam int quantity) {
        Equipment updatedEquipment = equipmentService.updateEquipmentQuantity(id, quantity);
        if (updatedEquipment != null) {
            return ResponseEntity.ok(updatedEquipment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Equipment> toggleEquipmentStatus(@PathVariable Long id) {
        Equipment currentEquipment = equipmentService.findById(id); // Assuming you have a method to find equipment by ID
        if (currentEquipment != null) {
            String newStatus = currentEquipment.getStatus().equals("Available") ? "Unavailable" : "Available";
            Equipment updatedEquipment = equipmentService.updateEquipmentStatus(id, newStatus);
            return ResponseEntity.ok(updatedEquipment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
