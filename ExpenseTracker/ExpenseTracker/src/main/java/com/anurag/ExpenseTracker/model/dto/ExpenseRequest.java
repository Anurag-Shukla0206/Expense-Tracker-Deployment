package com.anurag.ExpenseTracker.model.dto;

import com.anurag.ExpenseTracker.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ExpenseRequest {

    private String expenseTitle;
    private String expenseDescription;
    private Double expensePrice;

}
