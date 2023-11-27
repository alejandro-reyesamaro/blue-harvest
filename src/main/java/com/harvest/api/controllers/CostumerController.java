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

import com.harvest.api.controllers.strategies.ICrudStrategy;
import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.CostumerFeature;
import com.harvest.application.features.dto.AddCostumerResult;
import com.harvest.application.features.dto.GetAllCostumersResult;
import com.harvest.application.services.ICostumerService;
import com.harvest.application.services.dto.forms.AddCostumerForm;
import com.harvest.core.entities.Costumer;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/costumer")
public class CostumerController {

	@Autowired
	protected ICostumerService costumerService;

	@Autowired
	protected CostumerFeature costumerFeature;

	@Autowired
    protected List<ICrudStrategy<AddCostumerResult>> addStrategies;

	@Autowired
    protected List<ICrudStrategy<GetAllCostumersResult>> getAllStrategies;

	@GetMapping("/{id}")
	public ResponseEntity<Costumer> getById(@PathVariable int id) {
		return ResponseEntity.of(costumerService.getCostumerById(id));
	}

	@GetMapping("")
	public ResponseEntity<BaseResponse> getAll() {
		try{
            GetAllCostumersResult result = costumerFeature.getAllCostumers();
			for(ICrudStrategy<GetAllCostumersResult> s : getAllStrategies) {
                if(s.itApplies(result)){
                    return s.run(result);
                }
            }
            return new ResponseEntity<>(new BaseResponse("[Error] Bad request"), BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new BaseResponse("[Exception] " + e.getMessage()), INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<BaseResponse> addCostumer(@Valid @RequestBody AddCostumerForm body) {
		try{
			AddCostumerResult result = costumerFeature.addCostumer(body);
			for(ICrudStrategy<AddCostumerResult> s : addStrategies) {
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
