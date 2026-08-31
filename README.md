# Transaction Processing Service

## 1. Problem Understanding

This project implements a small transaction-processing REST service using Java and Spring Boot.

The service manages customer transactions containing:

- Transaction ID
- Customer ID
- Amount
- Currency
- Transaction Type
- Transaction Status

The application implements the four required operations:

1. Create a transaction
2. Get a transaction by Transaction ID
3. Update transaction status
4. Get all transactions for a Customer ID

The application uses Spring Data JPA for persistence and H2 as the embedded database provided by the starter project.

---

## 2. Assumptions

The following assumptions were made while implementing the service:

- Every transaction must have a unique Transaction ID.
- Every transaction must belong to a Customer ID.
- Transaction amount must be greater than zero.
- Currency, Transaction Type, and Transaction Status are required fields.
- A transaction is expected to start in the `PENDING` state.
- Once a transaction reaches `SUCCESS` or `FAILED`, its status is considered final.
- No authentication or authorization is required because it is outside the scope of the assignment.
- H2 is used as the database because it is already configured in the provided starter project.

---

## 3. Validation Rules

The following validation rules are implemented:

| Field | Validation |
|---|---|
| Transaction ID | Required and must be unique |
| Customer ID | Required |
| Amount | Required and must be greater than zero |
| Currency | Required |
| Transaction Type | Required |
| Transaction Status | Required |

Invalid transaction data is rejected with HTTP `400 Bad Request`.

A transaction with an existing Transaction ID is rejected with HTTP `409 Conflict`.

---

## 4. Status Transition Rules

The following status transitions are allowed:

```text
PENDING -> SUCCESS
PENDING -> FAILED