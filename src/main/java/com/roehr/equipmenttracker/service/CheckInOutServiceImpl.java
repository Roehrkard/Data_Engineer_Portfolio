package com.roehr.equipmenttracker.service;

import com.roehr.equipmenttracker.model.Equipment;
import com.roehr.equipmenttracker.model.Transaction;
import com.roehr.equipmenttracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CheckInOutServiceImpl implements CheckInOutService {

    private static final Logger logger = LoggerFactory.getLogger(CheckInOutServiceImpl.class);

    @Autowired
    private UserService userService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private TransactionService transactionService;

    public CheckInOutServiceImpl(UserService userService, EquipmentService equipmentService, TransactionService transactionService) {
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.transactionService = transactionService;
    }

    @Override
    public Transaction checkoutEquipment(String username, Long equipmentId, int quantity) {
        logger.info("Checking out equipment - Username: {}, EquipmentID: {}, Quantity: {}", username, equipmentId, quantity);
        User user = userService.getUserByUsername(username); // Find user by username
        Equipment equipment = equipmentService.findById(equipmentId);

        Transaction transaction = new Transaction();
        transaction.setUserId(user.getId());
        transaction.setUsername(user.getUsername());
        transaction.setEquipmentId(equipment.getId());
        transaction.setEquipmentName(equipment.getName());
        transaction.setQuantity(quantity);
        transaction.setStatus("Checked Out");
        transaction.setTransactionDate(new Date());
        transactionService.save(transaction);

        return transaction;
    }

    @Override
    public Transaction checkinEquipment(Long userId, Long equipmentId, int quantity) {
        logger.info("Checking in equipment - UserID: {}, EquipmentID: {}, Quantity: {}", userId, equipmentId, quantity);
        User user = userService.findById(userId);
        Equipment equipment = equipmentService.findById(equipmentId);

        Transaction transaction = new Transaction();
        transaction.setUserId(user.getId());
        transaction.setUsername(user.getUsername());
        transaction.setEquipmentId(equipment.getId());
        transaction.setEquipmentName(equipment.getName());
        transaction.setQuantity(quantity);
        logger.info("Set transaction quantity: {}", transaction.getQuantity());  // Logging the quantity set in the transaction
        transaction.setStatus("Checked In");
        transaction.setTransactionDate(new Date());
        transactionService.save(transaction);

        return transaction;
    }
}
