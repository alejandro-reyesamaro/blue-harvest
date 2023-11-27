package com.harvest.application.features.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountResult {
    protected String message;
    protected boolean isSuccess;
}
