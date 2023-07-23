package com.anurag.ExpenseTracker.model.dto;

import com.anurag.ExpenseTracker.model.Expense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ExpenseSingleOutput {
    private boolean expenseStatus;
    private String expenseStatusMessage;
    private Expense expenseSingle;
}

