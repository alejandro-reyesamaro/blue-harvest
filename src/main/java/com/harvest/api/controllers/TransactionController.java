package com.harvest.api.controllers;

import java.util.Collection;

import com.harvest.application.services.ITransactionService;
import com.harvest.application.services.dto.forms.AddTransactionForm;
import com.harvest.application.services.dto.results.AddEntityResult;
import com.harvest.core.entities.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    
    @Autowired
    protected ITransactionService transactionService;

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable int id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/costumer/{costumerId}")
    public Collection<Transaction> getCostumerTransactions(@PathVariable int costumerId) {
        return transactionService.getCostumerTransactions(costumerId);
    }

    @PutMapping("")
    public ResponseEntity<AddEntityResult> addTransaction(@RequestBody AddTransactionForm body) {
        //!- TODO: Body validation
		AddEntityResult result = this.transactionService.addTransaction(body);
		return new ResponseEntity<AddEntityResult>(result, result.isSuccess() ? OK : BAD_REQUEST);
    }
}