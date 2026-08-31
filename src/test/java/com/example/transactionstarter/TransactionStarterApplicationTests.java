package com.example.transactionstarter;

import com.example.transactionstarter.transaction.entity.Transaction;
import com.example.transactionstarter.transaction.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionStarterApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository.deleteAll();
    }

    @Test
    void shouldCreateTransactionSuccessfully() throws Exception {

        Transaction transaction = new Transaction(
                "TXN1001",
                "CUST001",
                5000.0,
                "INR",
                "CREDIT",
                "PENDING"
        );

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldRejectInvalidTransaction() throws Exception {

        Transaction transaction = new Transaction(
                "",
                "",
                -100.0,
                "",
                "",
                ""
        );

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldRejectDuplicateTransactionId() throws Exception {

        Transaction transaction = new Transaction(
                "TXN1002",
                "CUST002",
                1000.0,
                "INR",
                "CREDIT",
                "PENDING"
        );

        String requestBody = objectMapper.writeValueAsString(transaction);

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isConflict());
    }

    @Test
    void shouldReturnNotFoundForNonexistentTransaction() throws Exception {

        mockMvc.perform(get("/transactions/DOES_NOT_EXIST"))
                .andExpect(status().isNotFound());
    }
}