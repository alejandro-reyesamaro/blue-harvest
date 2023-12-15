package com.harvest.api.controllers;

import java.util.List;

import com.harvest.api.controllers.strategies.ICrudResponseStrategy;
import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.TransactionFeature;
import com.harvest.application.features.dto.AddTransactionResult;
import com.harvest.application.features.dto.GetCostumerTransactionsResult;
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

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    
    @Autowired
    protected TransactionFeature transactionFeature;

    @Autowired
    protected List<ICrudResponseStrategy<AddTransactionResult>> addStrategies;

    @Autowired
    protected List<ICrudResponseStrategy<GetCostumerTransactionsResult>> getStrategies;

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable int id) {
        return ResponseEntity.of(transactionFeature.getTransactionById(id));
    }

    @GetMapping("/costumer/{costumerId}")
    public ResponseEntity<BaseResponse> getCostumerTransactions(@PathVariable int costumerId) {
        GetCostumerTransactionsResult result = transactionFeature.getCostumerTransactions(costumerId);
        return ControllerHelper.runStrategies(getStrategies, result);
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> addTransaction(@Valid @RequestBody AddTransactionForm body) {
        AddTransactionResult result = this.transactionFeature.addTransaction(body);
        return ControllerHelper.runStrategies(addStrategies, result);
    }
}
