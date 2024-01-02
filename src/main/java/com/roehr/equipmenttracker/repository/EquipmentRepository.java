package com.roehr.equipmenttracker.repository;

import com.roehr.equipmenttracker.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    // Additional custom methods if needed
}
