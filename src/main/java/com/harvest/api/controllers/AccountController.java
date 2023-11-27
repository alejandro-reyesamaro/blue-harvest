package com.harvest.api.controllers;

import java.util.List;

import com.harvest.api.controllers.strategies.IAddAccountResponseStrategy;
import com.harvest.api.controllers.strategies.IGetCostumerAccountsResponseStrategy;
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

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/account")
public class AccountController {
    
    @Autowired
    protected AccountFeature accountFeatures;

    @Autowired
    protected List<IAddAccountResponseStrategy> addStrategies;

    @Autowired
    protected List<IGetCostumerAccountsResponseStrategy> getCostumerAccountStrategies;
    
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable int id) {
        return ResponseEntity.of(accountFeatures.getAccountById(id));
    }

    @GetMapping("/costumer/{costumerId}")
    public ResponseEntity<BaseResponse> getCostumerAccounts(@PathVariable int costumerId) {
        try{
            GetCostumerAccountsResult result = accountFeatures.getCostumerAccounts(costumerId);
            for(IGetCostumerAccountsResponseStrategy s : getCostumerAccountStrategies) {
                if(s.itApplies(result)){
                    return s.run(result);
                }
            }
            return new ResponseEntity<>(new BaseResponse("[Error] Bad request"), BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new BaseResponse("[Exception] " + e.getMessage()), INTERNAL_SERVER_ERROR);
		}
    }

    @PostMapping(value="", consumes="application/json")
    public ResponseEntity<BaseResponse> addAccount(@Valid @RequestBody AddAccountForm body) {
        try{
            AddAccountResult result = accountFeatures.createAccount(body);
            for(IAddAccountResponseStrategy s : addStrategies) {
                if(s.itApplies(result)){
                    return s.run(result);
                }
            }
            return new ResponseEntity<>(new BaseResponse("[Error] Bad request"), BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new BaseResponse("[Exception] " + e.getMessage()), INTERNAL_SERVER_ERROR);
		}
    }
}
