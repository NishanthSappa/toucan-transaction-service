package com.example.transactionstarter.transaction.service;

import com.example.transactionstarter.transaction.entity.Transaction;
import com.example.transactionstarter.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.transactionstarter.transaction.exception.DuplicateTransactionException;
import com.example.transactionstarter.transaction.exception.TransactionNotFoundException;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction) {

    if (transactionRepository.existsById(transaction.getTransactionId())) {
        throw new DuplicateTransactionException("Transaction ID already exists");
        }

        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(String transactionId) {
    return transactionRepository.findById(transactionId)
            .orElseThrow(() ->
                    new TransactionNotFoundException("Transaction not found"));
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByCustomerId(String customerId) {
    return transactionRepository.findByCustomerId(customerId);
    }

    public Transaction updateTransactionStatus(String transactionId, String status) {

    Transaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() ->
                    new TransactionNotFoundException("Transaction not found"));

    if (!transaction.getTransactionStatus().equals("PENDING")) {
        throw new IllegalStateException(
                "Only PENDING transactions can be updated");
    }

    if (!status.equals("SUCCESS") && !status.equals("FAILED")) {
        throw new IllegalArgumentException(
                "Status must be SUCCESS or FAILED");
    }

    transaction.setTransactionStatus(status);

    return transactionRepository.save(transaction);
    }
}