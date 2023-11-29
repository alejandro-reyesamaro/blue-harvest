package com.harvest.application.features.dto;

import java.util.Collection;
import java.util.Collections;

import com.harvest.core.entities.Injection;

import lombok.Getter;

@Getter
public class GetCostumerInjectionsResult extends FeatureResult {
    
    public static final String NO_COSTUMER_FOUND = "Costumer not found";
    public static final String NO_INJECTIONS_FOUND = "No accounts found for the given costumer";
    public static final String COSTUMER_INJECTIONS_SUFFIX = " account(s) found";

    protected Collection<Injection> injections;

    protected GetCostumerInjectionsResult(Collection<Injection> injections, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.injections = injections;
    }

    public static GetCostumerInjectionsResult success(Collection<Injection> injections) {
        return new GetCostumerInjectionsResult(injections, injections.size() + COSTUMER_INJECTIONS_SUFFIX, true);
    }

    public static GetCostumerInjectionsResult notInjectionsFound() {
        return new GetCostumerInjectionsResult(Collections.emptyList(), NO_INJECTIONS_FOUND, true);
    }

    public static GetCostumerInjectionsResult costumerNotFound() {
        return new GetCostumerInjectionsResult(Collections.emptyList(), NO_COSTUMER_FOUND, false);
    }
}
