package com.anurag.ExpenseTracker.model.dto;

import com.anurag.ExpenseTracker.model.Expense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ExpenseMultiOutput {

    private boolean expenseStatus;
    private String expenseStatusMessage;
    private List<Expense> expenseReport;


}
