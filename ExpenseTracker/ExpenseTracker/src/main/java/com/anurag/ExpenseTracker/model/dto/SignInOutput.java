package com.anurag.ExpenseTracker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SignInOutput {

    private boolean signInStatus;
    private String signInStatusMessage;
}
