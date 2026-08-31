package com.example.transactionstarter.transaction.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Transaction {

    @Id
@NotBlank(message = "Transaction ID is required")
private String transactionId;

@NotBlank(message = "Customer ID is required")
private String customerId;

@NotNull(message = "Amount is required")
@Positive(message = "Amount must be greater than zero")
private Double amount;

@NotBlank(message = "Currency is required")
private String currency;

@NotBlank(message = "Transaction Type is required")
private String transactionType;

@NotBlank(message = "Transaction Status is required")
private String transactionStatus;

    public Transaction() {
    }

    public Transaction(String transactionId, String customerId, Double amount,
                       String currency, String transactionType, String transactionStatus) {
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.amount = amount;
        this.currency = currency;
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}