package com.harvest.application.features.dto;

import com.harvest.core.entities.Injection;

import lombok.Getter;

@Getter
public class AddInjectionResult extends FeatureResult {
    
    public static final String NEGATIVE_AMOUNT = "Injection amount must be a positive number";
    public static final String NO_COSTUMER_FOUND = "Costumer not found";
    public static final String NO_ACCOUNT_FOUND = "Costumer account not found";
    public static final String CREATED = "Injection created";

    protected Injection injection;

    protected AddInjectionResult(Injection injection, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.injection = injection;
    }

    public static AddInjectionResult negativeInjection() {
        return new AddInjectionResult(null, NEGATIVE_AMOUNT, false);
    }

    public static AddInjectionResult costumerNotFound() {
        return new AddInjectionResult(null, NO_COSTUMER_FOUND, false);
    }

    public static AddInjectionResult costumerAccountNotFound() {
        return new AddInjectionResult(null, NO_ACCOUNT_FOUND, false);
    }

    public static AddInjectionResult injectionCreated(Injection injection) {
        return new AddInjectionResult(injection, CREATED, true);
    }
}
