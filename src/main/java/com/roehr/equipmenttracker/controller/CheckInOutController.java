package com.roehr.equipmenttracker.controller;

import com.roehr.equipmenttracker.model.Equipment;
import com.roehr.equipmenttracker.model.Transaction;
import com.roehr.equipmenttracker.service.CheckInOutService;
import com.roehr.equipmenttracker.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class CheckInOutController {

    private static final Logger logger = LoggerFactory.getLogger(CheckInOutController.class);

    @Autowired
    private CheckInOutService checkInOutService;

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping("/checkout")
    public ResponseEntity<Transaction> checkoutEquipment(@RequestBody CheckoutCheckinRequest request) {
        // Log the received request data
        logger.info("Checkout request - Username: {}, Equipment ID: {}, Quantity: {}",
                request.getUsername(), request.getEquipmentId(), request.getQuantity());

        Transaction transaction = checkInOutService.checkoutEquipment(request.getUsername(), request.getEquipmentId(), request.getQuantity());

        Equipment equipment = equipmentService.findById(request.getEquipmentId());

        if (equipment != null) {
            // Calculate the new quantity
            int updatedQty = equipment.getQty() - request.getQuantity();

            // Update the equipment's quantity with the new value
            equipmentService.updateEquipmentQuantity(request.getEquipmentId(), updatedQty);

            if (updatedQty == 0) {
                equipmentService.updateEquipmentStatus(request.getEquipmentId(), "Unavailable");
            }

            // The equipment status and quantity have been updated successfully
            return ResponseEntity.ok(transaction);
        } else {
            // Handle the case where the equipment doesn't exist or couldn't be updated
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/checkin")
    public ResponseEntity<Transaction> checkinEquipment(@RequestBody CheckoutCheckinRequest request) {
        Transaction transaction = checkInOutService.checkinEquipment(request.getUserId(), request.getEquipmentId(), request.getQuantity());

        // Update the equipment status to "Available"
        Equipment equipment = equipmentService.updateEquipmentStatus(request.getEquipmentId(), "Available");

        if (equipment != null) {
            // Calculate the new quantity
            int newQty = equipment.getQty() + request.getQuantity();

            // Update the equipment's quantity with the new value
            equipmentService.updateEquipmentQuantity(request.getEquipmentId(), newQty);

            // The equipment status and quantity have been updated successfully
            return ResponseEntity.ok(transaction);
        } else {
            // Handle the case where the equipment doesn't exist or couldn't be updated
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    // Static inner class for request body
    static class CheckoutCheckinRequest {
        private Long userId;
        private String username;
        private Long equipmentId;
        private int quantity;

        // Getters and Setters
        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getEquipmentId() {
            return equipmentId;
        }

        public void setEquipmentId(Long equipmentId) {
            this.equipmentId = equipmentId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}



//    @PostMapping("/checkout")
//    public ResponseEntity<Transaction> checkoutEquipment(@RequestBody CheckoutCheckinRequest request) {
//        // Log the received request data
//        logger.info("Checkout request - Username: {}, Equipment ID: {}, Quantity: {}",
//                request.getUsername(), request.getEquipmentId(), request.getQuantity());
//
//        Transaction transaction = checkInOutService.checkoutEquipment(request.getUsername(), request.getEquipmentId(), request.getQuantity());
//
//        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
//    }