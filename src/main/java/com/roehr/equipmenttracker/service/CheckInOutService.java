package com.roehr.equipmenttracker.service;

import com.roehr.equipmenttracker.model.Transaction;

public interface CheckInOutService {
    Transaction checkoutEquipment(String username, Long equipmentId, int quantity);
    Transaction checkinEquipment(Long userId, Long equipmentId, int quantity);
}
