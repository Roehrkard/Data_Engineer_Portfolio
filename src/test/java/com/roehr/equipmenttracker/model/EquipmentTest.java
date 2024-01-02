package com.roehr.equipmenttracker.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EquipmentTest {

    @Test
    public void testEquipmentGettersAndSetters() {
        String name = "GPS Navigator";
        String status = "Available";
        int qty = 5;
        String description = "Portable GPS device";

        // Testing constructor and getters
        Equipment equipment = new Equipment(name, status, qty, description);
        assertEquals(name, equipment.getName());
        assertEquals(status, equipment.getStatus());
        assertEquals(qty, equipment.getQty());
        assertEquals(description, equipment.getDescription());

        // Testing setters
        String updatedStatus = "Unavailable";
        int updatedQty = 3;
        equipment.setStatus(updatedStatus);
        equipment.setQty(updatedQty);

        assertEquals(updatedStatus, equipment.getStatus());
        assertEquals(updatedQty, equipment.getQty());
    }
}
