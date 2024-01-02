package com.roehr.equipmenttracker.repository;

import com.roehr.equipmenttracker.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testFindByUserId() {
        // Given: Set up a Transaction
        Transaction transaction = new Transaction();
        transaction.setUserId(1L);
        transaction.setTransactionDate(new Date());
        transaction.setUsername("TestUser");
        transaction.setEquipmentId(123L);
        transaction.setEquipmentName("TestEquipment");
        transaction.setQuantity(5);
        transaction.setStatus("Checked Out");

        entityManager.persist(transaction);
        entityManager.flush();

        // When: Test findByUserId
        List<Transaction> foundTransactions = transactionRepository.findByUserId(1L);

        // Then: Verify the results
        assertFalse(foundTransactions.isEmpty(), "The list of transactions should not be empty");
        assertEquals(1, foundTransactions.size(), "The size of the returned transaction list should be 1");
        assertEquals(transaction.getUserId(), foundTransactions.get(0).getUserId(), "The user ID of the transaction should match");
    }
}
