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

import java.util.List;

import com.harvest.api.controllers.strategies.ICrudResponseStrategy;
import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.InjectionFeature;
import com.harvest.application.features.dto.AddInjectionResult;
import com.harvest.application.features.dto.GetCostumerInjectionsResult;
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

    @Autowired
    protected InjectionFeature injectionFeature;

    @Autowired
    protected List<ICrudResponseStrategy<AddInjectionResult>> addStrategies;

    @Autowired
    protected List<ICrudResponseStrategy<GetCostumerInjectionsResult>> getStrategies;

    @GetMapping("/{id}")
    public ResponseEntity<Injection> getById(@PathVariable int id) {
        return ResponseEntity.of(injectionService.getInjectionById(id));
    }

    @GetMapping("/costumer/{costumerId}")
    public ResponseEntity<BaseResponse> getCostumerInjections(@PathVariable int costumerId) {
        try{
            GetCostumerInjectionsResult result = injectionFeature.getCostumerInjections(costumerId);
            return ControllerHelper.runStrategies(getStrategies, result);
		} catch (Exception e) {
			return new ResponseEntity<>(new BaseResponse("[Exception] " + e.getMessage()), INTERNAL_SERVER_ERROR);
		}
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> addInjection(@Valid @RequestBody AddInjectionForm body) {
        try{
			AddInjectionResult result = this.injectionFeature.addInjection(body);
			return ControllerHelper.runStrategies(addStrategies, result);
		} catch (Exception e) {
			return new ResponseEntity<>(new BaseResponse("[Exception] " + e.getMessage()), INTERNAL_SERVER_ERROR);
		}
    }
}
