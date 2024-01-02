package com.roehr.equipmenttracker.service;

import com.roehr.equipmenttracker.model.Equipment;
import com.roehr.equipmenttracker.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    @Override
    public Equipment updateEquipmentQuantity(Long id, int quantity) {
        Equipment equipment = getEquipmentById(id);
        if (equipment != null) {
            equipment.setQty(quantity);
            return equipmentRepository.save(equipment);
        }
        return null;
    }

    @Override
    public Equipment updateEquipmentStatus(Long id, String status) {
        Equipment equipment = getEquipmentById(id);
        if (equipment != null) {
            equipment.setStatus(status);
            return equipmentRepository.save(equipment);
        }
        return null;
    }

    @Override
    public Equipment findById(Long id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    @Override
    public Equipment save(Equipment equipment, String status) {
        equipment.setStatus(status); // Set the status before saving
        return equipmentRepository.save(equipment);
    }
}
