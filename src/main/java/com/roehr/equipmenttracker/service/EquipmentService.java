package com.roehr.equipmenttracker.service;

import com.roehr.equipmenttracker.model.Equipment;
import java.util.List;

public interface EquipmentService {
    List<Equipment> getAllEquipment();
    Equipment getEquipmentById(Long id);
    Equipment updateEquipmentQuantity(Long id, int quantity);
    Equipment updateEquipmentStatus(Long id, String status);

    Equipment findById(Long equipmentId);

    Equipment save(Equipment equipment, String status);
}
