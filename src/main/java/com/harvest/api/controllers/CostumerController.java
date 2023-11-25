package com.harvest.api.controllers;

import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import com.harvest.application.services.ICostumerService;
import com.harvest.application.services.dto.forms.AddCostumerForm;
import com.harvest.core.entities.Costumer;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/costumer")
public class CostumerController {

	@Autowired
	protected ICostumerService costumerService;

	@GetMapping("/{id}")
	public ResponseEntity<Costumer> getById(@PathVariable int id) {
		return ResponseEntity.of(costumerService.getCostumerById(id));
	}

	@GetMapping("")
	public Collection<Costumer> getAll() {
		return costumerService.getAllCostumers();
	}

	@PostMapping("")
	public ResponseEntity<Costumer> addCostumer(@Valid @RequestBody AddCostumerForm body) {
		try{
			Costumer newCostumer = this.costumerService.addCostumer(body);
			return new ResponseEntity<Costumer>(newCostumer, OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);
		}
	}
}
