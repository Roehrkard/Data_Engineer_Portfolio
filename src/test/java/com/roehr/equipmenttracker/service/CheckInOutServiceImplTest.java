package com.roehr.equipmenttracker.service;

import com.roehr.equipmenttracker.model.Equipment;
import com.roehr.equipmenttracker.model.Transaction;
import com.roehr.equipmenttracker.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckInOutServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private EquipmentService equipmentService;

    @Mock
    private TransactionService transactionService;

    private CheckInOutService checkInOutService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        checkInOutService = new CheckInOutServiceImpl(userService, equipmentService, transactionService);
    }

    @Test
    public void testCheckoutEquipment() {
        // Prepare mock data
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        Equipment equipment = new Equipment();
        equipment.setId(100L);
        equipment.setName("Equipment1");

        // Mock the userService and equipmentService
        when(userService.findById(1L)).thenReturn(user);
        when(equipmentService.findById(100L)).thenReturn(equipment);

        // Prepare the expected transaction
        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setUserId(user.getId());
        expectedTransaction.setUsername(user.getUsername());
        expectedTransaction.setEquipmentId(equipment.getId());
        expectedTransaction.setEquipmentName(equipment.getName());
        expectedTransaction.setQuantity(5); // Set the quantity as needed
        expectedTransaction.setStatus("Checked Out");

        // Using ArgumentCaptor to capture the transaction
        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        when(transactionService.save(transactionCaptor.capture())).thenReturn(expectedTransaction);

        // Perform the checkout
        checkInOutService.checkoutEquipment("john_doe", 100L, 5);

        // Capture and assert the transaction details
        Transaction capturedTransaction = transactionCaptor.getValue();
        assertEquals(expectedTransaction.getUserId(), capturedTransaction.getUserId());
        assertEquals(expectedTransaction.getUsername(), capturedTransaction.getUsername());
        assertEquals(expectedTransaction.getEquipmentId(), capturedTransaction.getEquipmentId());
        assertEquals(expectedTransaction.getEquipmentName(), capturedTransaction.getEquipmentName());
        assertEquals(expectedTransaction.getQuantity(), capturedTransaction.getQuantity());
        assertEquals(expectedTransaction.getStatus(), capturedTransaction.getStatus());
        // Note: Add assertions for other relevant fields if necessary
    }

    @Test
    public void testCheckinEquipment() {
        // Prepare mock data for user and equipment
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        Equipment equipment = new Equipment();
        equipment.setId(100L);
        equipment.setName("Equipment1");

        // Mock the userService and equipmentService
        when(userService.findById(1L)).thenReturn(user);
        when(equipmentService.findById(100L)).thenReturn(equipment);

        // Prepare the expected transaction for check-in
        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setUserId(user.getId());
        expectedTransaction.setUsername(user.getUsername());
        expectedTransaction.setEquipmentId(equipment.getId());
        expectedTransaction.setEquipmentName(equipment.getName());
        expectedTransaction.setQuantity(5); // Set the quantity as needed
        expectedTransaction.setStatus("Checked In");

        // Using ArgumentCaptor to capture the transaction
        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        when(transactionService.save(transactionCaptor.capture())).thenReturn(expectedTransaction);

        // Perform the check-in
        checkInOutService.checkinEquipment(1L, 100L, 5);

        // Capture and assert the transaction details
        Transaction capturedTransaction = transactionCaptor.getValue();
        assertEquals(expectedTransaction.getUserId(), capturedTransaction.getUserId());
        assertEquals(expectedTransaction.getUsername(), capturedTransaction.getUsername());
        assertEquals(expectedTransaction.getEquipmentId(), capturedTransaction.getEquipmentId());
        assertEquals(expectedTransaction.getEquipmentName(), capturedTransaction.getEquipmentName());
        assertEquals(expectedTransaction.getQuantity(), capturedTransaction.getQuantity());
        assertEquals(expectedTransaction.getStatus(), capturedTransaction.getStatus());
        // Note: Add assertions for other relevant fields if necessary
    }
}
