package com.harvest.application.features.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeatureResult {
    protected String message;
    protected boolean isSuccess;
}
