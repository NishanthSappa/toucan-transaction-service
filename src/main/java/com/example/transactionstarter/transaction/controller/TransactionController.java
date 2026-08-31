package com.example.transactionstarter.transaction.controller;

import com.example.transactionstarter.transaction.entity.Transaction;
import com.example.transactionstarter.transaction.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.example.transactionstarter.transaction.dto.UpdateStatusRequest;

import jakarta.validation.Valid;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction createTransaction(@Valid @RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransactionById(@PathVariable String transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/customer/{customerId}")
    public List<Transaction> getTransactionsByCustomerId(@PathVariable String customerId) {
    return transactionService.getTransactionsByCustomerId(customerId);
    }

    @PutMapping("/{transactionId}/status")
    public Transaction updateTransactionStatus(
        @PathVariable String transactionId,
        @RequestBody UpdateStatusRequest request) {

    return transactionService.updateTransactionStatus(
            transactionId,
            request.getTransactionStatus());
    }
}