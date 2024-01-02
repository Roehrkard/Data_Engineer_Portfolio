package com.roehr.equipmenttracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roehr.equipmenttracker.model.Transaction;
import com.roehr.equipmenttracker.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class TransactionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    private TransactionController transactionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionController = new TransactionController(transactionService);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void testCreateTransaction() throws Exception {
        // Prepare a sample transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(new Date());
        // Set other properties as needed

        // Mock the service to return the saved transaction
        when(transactionService.save(any(Transaction.class))).thenReturn(transaction);

        // Perform the POST request
        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(transaction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionDate").exists()); // Verify the response
    }

    @Test
    public void testGetTransactionsByUserId() throws Exception {
        // Prepare a sample list of transactions
        List<Transaction> transactions = Collections.singletonList(new Transaction());

        // Mock the service to return the list of transactions
        when(transactionService.getTransactionsByUserId(1L)).thenReturn(transactions);

        // Perform the GET request
        mockMvc.perform(get("/api/transactions/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray()); // Verify the response as a JSON array
    }

    // Helper method to convert an object to JSON
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
