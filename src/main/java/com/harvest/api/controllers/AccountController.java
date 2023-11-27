package com.harvest.api.controllers;

import java.util.List;

import com.harvest.api.controllers.strategies.ICrudResponseStrategy;
import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.AccountFeature;
import com.harvest.application.features.dto.AddAccountResult;
import com.harvest.application.features.dto.GetCostumerAccountsResult;
import com.harvest.application.services.dto.forms.AddAccountForm;
import com.harvest.core.entities.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/account")
public class AccountController {
    
    @Autowired
    protected AccountFeature accountFeatures;

    @Autowired
    protected List<ICrudResponseStrategy<AddAccountResult>> addStrategies;

    @Autowired
    protected List<ICrudResponseStrategy<GetCostumerAccountsResult>> getCostumerAccountStrategies;
    
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable int id) {
        return ResponseEntity.of(accountFeatures.getAccountById(id));
    }

    @GetMapping("/costumer/{costumerId}")
    public ResponseEntity<BaseResponse> getCostumerAccounts(@PathVariable int costumerId) {
        try{
            GetCostumerAccountsResult result = accountFeatures.getCostumerAccounts(costumerId);
            return ControllerHelper.runStrategies(getCostumerAccountStrategies, result);
		} catch (Exception e) {
			return ControllerHelper.responseForUnhandledException(e);
		}
    }

    @PostMapping(value="", consumes="application/json")
    public ResponseEntity<BaseResponse> addAccount(@Valid @RequestBody AddAccountForm body) {
        try{
            AddAccountResult result = accountFeatures.createAccount(body);
            return ControllerHelper.runStrategies(addStrategies, result);
		} catch (Exception e) {
			return ControllerHelper.responseForUnhandledException(e);
		}
    }
}
