package com.harvest.api.controllers;

import java.util.Collection;

import com.harvest.application.services.IAccountService;
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

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/account")
public class AccountController {
    
    @Autowired
    protected IAccountService accountService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable int id) {
        return ResponseEntity.of(accountService.getAccountById(id));
    }

    @GetMapping("/costumer/{costumerId}")
    public Collection<Account> getCostumerAccounts(@PathVariable int costumerId) {
        return accountService.getCostumerAccounts(costumerId);
    }

    @PostMapping(value="", consumes="application/json")
    public ResponseEntity<Account> addAccount(@Valid @RequestBody AddAccountForm body) {
        try{
			Account newAccount = this.accountService.createAccount(body);
			return new ResponseEntity<Account>(newAccount, OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);
		}
    }
}
