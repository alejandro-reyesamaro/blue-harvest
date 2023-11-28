package com.harvest.application.features.dto;

import com.harvest.core.entities.Injection;

import lombok.Getter;

@Getter
public class AddInjectionResult extends FeatureResult {
    
    protected Injection injection;

    protected AddInjectionResult(Injection injection, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.injection = injection;
    }

    public static AddInjectionResult negativeInjection() {
        return new AddInjectionResult(null, "Injection amount must be a positive number", false);
    }

    public static AddInjectionResult costumerNotFound() {
        return new AddInjectionResult(null, "Costumer not found", false);
    }

    public static AddInjectionResult costumerAccountNotFound() {
        return new AddInjectionResult(null, "Costumer account not found", false);
    }

    public static AddInjectionResult injectionCreated(Injection injection) {
        return new AddInjectionResult(injection, "Injection created", true);
    }
}
