package com.roehr.equipmenttracker.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

class TransactionTest {

    @Test
    void testTransactionGettersAndSetters() {
        Transaction transaction = new Transaction();

        Date expectedDate = new Date();
        Long expectedUserId = 2L;
        String expectedUsername = "TestUser";
        Long expectedEquipmentId = 3L;
        String expectedEquipmentName = "TestEquipment";
        Integer expectedQty = 5;
        String expectedStatus = "Checked Out";

        transaction.setTransactionDate(expectedDate);
        transaction.setUserId(expectedUserId);
        transaction.setUsername(expectedUsername);
        transaction.setEquipmentId(expectedEquipmentId);
        transaction.setEquipmentName(expectedEquipmentName);
        transaction.setQuantity(expectedQty);
        transaction.setStatus(expectedStatus);

        assertEquals(expectedDate, transaction.getTransactionDate());
        assertEquals(expectedUserId, transaction.getUserId());
        assertEquals(expectedUsername, transaction.getUsername());
        assertEquals(expectedEquipmentId, transaction.getEquipmentId());
        assertEquals(expectedEquipmentName, transaction.getEquipmentName());
        assertEquals(expectedQty, transaction.getQuantity());
        assertEquals(expectedStatus, transaction.getStatus());
    }
}
