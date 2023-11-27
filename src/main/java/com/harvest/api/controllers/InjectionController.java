package com.harvest.api.controllers;

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

import java.util.Collection;

import com.harvest.application.services.IInjectionService;
import com.harvest.application.services.dto.forms.AddInjectionForm;
import com.harvest.core.entities.Injection;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/injection")
public class InjectionController {
    
    @Autowired
    protected IInjectionService injectionService;

    @GetMapping("/{id}")
    public ResponseEntity<Injection> getById(@PathVariable int id) {
        return ResponseEntity.of(injectionService.getInjectionById(id));
    }

    @GetMapping("/costumer/{costumerId}")
    public Collection<Injection> getCostumerInjections(@PathVariable int costumerId) {
        return injectionService.getCostumerInjections(costumerId);
    }

    @PostMapping("")
    public ResponseEntity<Injection> addInjection(@Valid @RequestBody AddInjectionForm body) {
        try{
			Injection newInjection = this.injectionService.addInjection(body);
			return new ResponseEntity<Injection>(newInjection, OK);
		} catch (Exception e) {
            System.out.println(e.toString());
			return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);
		}
    }
}
