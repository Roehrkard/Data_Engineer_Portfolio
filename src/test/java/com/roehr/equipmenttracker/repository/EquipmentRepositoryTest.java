package com.roehr.equipmenttracker.repository;

import com.roehr.equipmenttracker.model.Equipment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional  // Enable transaction management for the test
@Rollback  // Roll back transactions after each test
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use a real database instead of an in-memory one
@ActiveProfiles("mysql") // Use MySQL configuration
public class EquipmentRepositoryTest {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Test
    public void testEquipmentSaveAndRetrieve() {
        // Create a new equipment
        Equipment newEquipment = new Equipment("GPS Navigator", "Available", 5, "Portable GPS device");

        // Save the equipment
        Equipment savedEquipment = equipmentRepository.save(newEquipment);

        // Retrieve the equipment
        Equipment retrievedEquipment = equipmentRepository.findById(savedEquipment.getId()).orElse(null);

        // Assert that the retrieved equipment is not null and properties match
        assertNotNull(retrievedEquipment);
        assertEquals("GPS Navigator", retrievedEquipment.getName());
        assertEquals(5, retrievedEquipment.getQty());
        assertEquals("Available", retrievedEquipment.getStatus());
    }

    // Additional test cases as needed
}
