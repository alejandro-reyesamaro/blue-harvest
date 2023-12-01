package com.harvest.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import com.harvest.api.controllers.strategies.ICrudResponseStrategy;
import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.CostumerFeature;
import com.harvest.application.features.dto.AddCostumerResult;
import com.harvest.application.features.dto.GetAllCostumersResult;
import com.harvest.application.services.dto.forms.AddCostumerForm;
import com.harvest.core.entities.Costumer;

@RestController
@RequestMapping("/costumer")
public class CostumerController {

	@Autowired
	protected CostumerFeature costumerFeature;

	@Autowired
    protected List<ICrudResponseStrategy<AddCostumerResult>> addStrategies;

	@Autowired
    protected List<ICrudResponseStrategy<GetAllCostumersResult>> getAllStrategies;

	@GetMapping("/{id}")
	public ResponseEntity<Costumer> getById(@PathVariable int id) {
		return ResponseEntity.of(costumerFeature.getCostumerById(id));
	}

	@GetMapping("")
	public ResponseEntity<BaseResponse> getAll() {
		try{
            GetAllCostumersResult result = costumerFeature.getAllCostumers();
			return ControllerHelper.runStrategies(getAllStrategies, result);
		} catch (Exception e) {
			return ControllerHelper.responseForUnhandledException(e);
		}
	}

	@PostMapping("")
	public ResponseEntity<BaseResponse> addCostumer(@Valid @RequestBody AddCostumerForm body) {
		try{
			AddCostumerResult result = costumerFeature.addCostumer(body);
			return ControllerHelper.runStrategies(addStrategies, result);
		} catch (Exception e) {
			return ControllerHelper.responseForUnhandledException(e);
		}
	}
}
