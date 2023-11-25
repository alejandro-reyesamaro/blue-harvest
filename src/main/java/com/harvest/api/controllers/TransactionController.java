package com.harvest.api.controllers;

import java.util.Collection;

import com.harvest.application.services.ITransactionService;
import com.harvest.application.services.dto.forms.AddTransactionForm;
import com.harvest.core.entities.Transaction;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    
    @Autowired
    protected ITransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable int id) {
        return ResponseEntity.of(transactionService.getTransactionById(id));
    }

    @GetMapping("/costumer/{costumerId}")
    public Collection<Transaction> getCostumerTransactions(@PathVariable int costumerId) {
        return transactionService.getCostumerTransactions(costumerId);
    }

    @PostMapping("")
    public ResponseEntity<Transaction> addTransaction(@Valid @RequestBody AddTransactionForm body) {
        try{
			Transaction newTransaction = this.transactionService.addTransaction(body);
			return new ResponseEntity<Transaction>(newTransaction, OK);
		} catch (Exception e) {
            System.out.println(e.toString());
			return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);
		}
    }
}
