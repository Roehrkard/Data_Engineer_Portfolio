package com.roehr.equipmenttracker.service;

import com.roehr.equipmenttracker.model.Transaction;
import java.util.List;

public interface TransactionService {

    List<Transaction> getTransactionsByUserId(Long userId);

    Transaction save(Transaction transaction);

    List<Transaction> getAllTransactions();

    Transaction findById(Long transactionId);
}
