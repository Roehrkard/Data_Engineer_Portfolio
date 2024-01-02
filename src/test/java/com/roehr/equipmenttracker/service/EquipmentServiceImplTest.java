package com.roehr.equipmenttracker.service;

import com.roehr.equipmenttracker.model.Equipment;
import com.roehr.equipmenttracker.repository.EquipmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EquipmentServiceImplTest {

    @Mock
    private EquipmentRepository equipmentRepository;

    @InjectMocks
    private EquipmentServiceImpl equipmentService;

    @Test
    public void testUpdateEquipmentQuantity() {
        Long equipmentId = 1L;
        int newQuantity = 10;
        Equipment existingEquipment = new Equipment("Drill", "Available", 8, "A high-power drill.");
        when(equipmentRepository.findById(equipmentId)).thenReturn(Optional.of(existingEquipment));
        when(equipmentRepository.save(any(Equipment.class))).thenReturn(existingEquipment);

        Equipment updatedEquipment = equipmentService.updateEquipmentQuantity(equipmentId, newQuantity);

        assertNotNull(updatedEquipment);
        assertEquals(newQuantity, updatedEquipment.getQty());
    }

    @Test
    public void testUpdateEquipmentStatus() {
        Long equipmentId = 1L;
        String newStatus = "Unavailable";
        Equipment existingEquipment = new Equipment("Drill", "Available", 8, "A high-power drill.");
        when(equipmentRepository.findById(equipmentId)).thenReturn(Optional.of(existingEquipment));
        when(equipmentRepository.save(any(Equipment.class))).thenReturn(existingEquipment);

        Equipment updatedEquipment = equipmentService.updateEquipmentStatus(equipmentId, newStatus);

        assertNotNull(updatedEquipment);
        assertEquals(newStatus, updatedEquipment.getStatus());
    }
}
