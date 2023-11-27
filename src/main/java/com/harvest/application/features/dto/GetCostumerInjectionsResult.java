package com.harvest.application.features.dto;

import java.util.Collection;
import java.util.Collections;

import com.harvest.core.entities.Injection;

import lombok.Getter;

@Getter
public class GetCostumerInjectionsResult extends FeatureResult {
    
    protected Collection<Injection> injections;

    protected GetCostumerInjectionsResult(Collection<Injection> injections, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.injections = injections;
    }

    public static GetCostumerInjectionsResult success(Collection<Injection> injections) {
        return new GetCostumerInjectionsResult(injections, injections.size() + " injection(s) found", true);
    }

    public static GetCostumerInjectionsResult notInjectionsFound() {
        return new GetCostumerInjectionsResult(Collections.emptyList(), "No injections found for the given costumer", true);
    }

    public static GetCostumerInjectionsResult costumerNotFound() {
        return new GetCostumerInjectionsResult(Collections.emptyList(), "Costumer not found", false);
    }
}
