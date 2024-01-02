package com.roehr.equipmenttracker.service;

import com.roehr.equipmenttracker.model.Transaction;
import com.roehr.equipmenttracker.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    public void testSaveTransaction() {
        Transaction transaction = new Transaction();
        // Set transaction data
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction savedTransaction = transactionService.save(transaction);

        assertNotNull(savedTransaction);
        verify(transactionRepository).save(transaction);
    }

    @Test
    public void testGetTransactionsByUserId() {
        when(transactionRepository.findByUserId(anyLong())).thenReturn(Collections.emptyList());

        List<Transaction> transactions = transactionService.getTransactionsByUserId(1L);

        assertNotNull(transactions);
        assertTrue(transactions.isEmpty());
        verify(transactionRepository).findByUserId(1L);
    }
}
